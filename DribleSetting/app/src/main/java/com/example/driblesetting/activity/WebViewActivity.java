package com.example.driblesetting.activity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

import com.example.driblesetting.CustomTabsIntentBuilder;
import com.example.driblesetting.GettingColorFromTheme;
import com.example.driblesetting.R;
import com.example.driblesetting.providers.ServicesProvider;
import com.example.driblesetting.webservice.DribbbleAPIs;
import com.example.driblesetting.webservice.DribbbleKeys;
import com.example.driblesetting.webservice.inputs.AccessTokenInput;
import com.example.driblesetting.webservice.outputs.AccessTokenOutput;
import com.example.driblesetting.webservice.outputs.BaseOutput;

import bolts.Continuation;
import bolts.Task;

public class WebViewActivity extends AppCompatActivity {

    private Dialog activity_login_authorize;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_authorize);

        authorize();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private void authorize() {
        activity_login_authorize = new Dialog(this, android.R.style.Theme);
        activity_login_authorize.setContentView(R.layout.activity_login_authorize);

        webView = activity_login_authorize.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (url.contains("?code=")) {
                    activity_login_authorize.dismiss();
                    Uri uri = Uri.parse(url);
                    String oauthCode = uri.getQueryParameter("code");
                    requestAccessToken(oauthCode);
                } else if (url.contains("error=access_denied")) {
                    activity_login_authorize.dismiss();
                    Toast.makeText(WebViewActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        StringBuilder builder = new StringBuilder();
        builder.append(DribbbleAPIs.AUTHORIZATION_URL);
        builder.append("?client_id=");
        builder.append(DribbbleKeys.CLIENT_ID);
        builder.append("&scope=");
        builder.append(DribbbleKeys.SCOPE);

        webView.clearCache(true);
        webView.clearHistory();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(this);
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }

        webView.loadUrl(builder.toString());
        activity_login_authorize.show();
        activity_login_authorize.setTitle("Authorize with Dribbble");
        activity_login_authorize.setCancelable(true);
    }


    private void requestAccessToken(String oauthCode) {
        final ProgressDialog loadingDialog = new ProgressDialog(this);
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loadingDialog.setTitle("Loading");
        loadingDialog.setMessage("Loading. Please wait...");
        loadingDialog.setIndeterminate(true);
        loadingDialog.setCanceledOnTouchOutside(false);

        loadingDialog.show();

        AccessTokenInput input = new AccessTokenInput();
        input.setClientId(DribbbleKeys.CLIENT_ID);
        input.setClientSecret(DribbbleKeys.CLIENT_SECRET);
        input.setCode(oauthCode);

        ServicesProvider.getInstance().getOauth2Client().getAccessToken(input).continueWith(new Continuation<BaseOutput<AccessTokenOutput>, Object>() {
            @Override
            public Object then(Task<BaseOutput<AccessTokenOutput>> task) throws Exception {
                loadingDialog.dismiss();

                final BaseOutput<AccessTokenOutput> result = task.getResult();

                if (result.getStatusCode() == BaseOutput.STATUS_CODE_OK) {
                    String accessToken = result.getData().getAccessToken();
                    ServicesProvider.getInstance().getAuthorizationProvider().setAccessToken(accessToken);
                    Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(WebViewActivity.this, result.getStatusMessage(), Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }
}
