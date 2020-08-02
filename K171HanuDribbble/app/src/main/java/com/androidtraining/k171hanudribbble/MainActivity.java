package com.androidtraining.k171hanudribbble;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtraining.k171hanudribbble.providers.ServicesProvider;
import com.androidtraining.k171hanudribbble.webservice.DribbbleAPIs;
import com.androidtraining.k171hanudribbble.webservice.DribbbleKeys;
import com.androidtraining.k171hanudribbble.webservice.inputs.AccessTokenInput;
import com.androidtraining.k171hanudribbble.webservice.inputs.ShotsInput;
import com.androidtraining.k171hanudribbble.webservice.models.ShotModel;
import com.androidtraining.k171hanudribbble.webservice.outputs.AccessTokenOutput;
import com.androidtraining.k171hanudribbble.webservice.outputs.BaseOutput;

import bolts.Continuation;
import bolts.Task;

public class MainActivity extends AppCompatActivity {

    private TextView tvAccessToken;
    private Dialog authDialog;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tvAccessToken = findViewById(R.id.tvAccessToken);
    }

    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btnAuthorize:
                authorize();
                break;

            case R.id.btnGetUserShots:
                getUserShorts();
                break;
        }
    }

    private void authorize() {
        authDialog = new Dialog(this, android.R.style.Theme);
        authDialog.setContentView(R.layout.auth_dialog);

        webView = authDialog.findViewById(R.id.wv_oauth);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (url.contains("?code=")) {
                    authDialog.dismiss();

                    Uri uri = Uri.parse(url);
                    String oauthCode = uri.getQueryParameter("code");

                    requestAcessToken(oauthCode);
                } else if (url.contains("error=access_denied")) {
                    authDialog.dismiss();

                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
        }
        else {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(this);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }

        webView.loadUrl(builder.toString());
        authDialog.show();
        authDialog.setTitle("Authorize with Dribbble");
        authDialog.setCancelable(true);
    }

    private void getUserShorts() {
        final ProgressDialog loadingDialog = new ProgressDialog(this);
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loadingDialog.setTitle("Loading");
        loadingDialog.setMessage("Loading. Please wait...");
        loadingDialog.setIndeterminate(true);
        loadingDialog.setCanceledOnTouchOutside(false);

        loadingDialog.show();

        ShotsInput shotsInput = new ShotsInput();
        shotsInput.setPageSize(30);
        shotsInput.setPageIndex(1);

        ServicesProvider.getInstance().getDribbbleClient().getShots(shotsInput).continueWith(new Continuation<BaseOutput<ShotModel[]>, Object>() {
            @Override
            public Object then(Task<BaseOutput<ShotModel[]>> task) throws Exception {
                loadingDialog.dismiss();

                final BaseOutput<ShotModel[]> result = task.getResult();

                if(result.getStatusCode() == BaseOutput.STATUS_CODE_OK){
                    ShotModel[] shots = result.getData();

                    // TODO: Do something with shots here
                }

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    private void requestAcessToken(String oauthCode) {
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

                if(result.getStatusCode() == BaseOutput.STATUS_CODE_OK){
                    String accessToken = result.getData().getAccessToken();
                    ServicesProvider.getInstance().getAuthorizationProvider().setAccessToken(accessToken);

                    tvAccessToken.setText(accessToken);
                } else {
                    Toast.makeText(MainActivity.this, result.getStatusMessage(), Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }
}
