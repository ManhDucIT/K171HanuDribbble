//package com.example.driblesetting;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.res.Resources;
//import android.net.Uri;
//import android.util.TypedValue;
//
//import androidx.browser.customtabs.CustomTabsClient;
//import androidx.browser.customtabs.CustomTabsIntent;
//import androidx.browser.customtabs.CustomTabsServiceConnection;
//import androidx.browser.customtabs.CustomTabsSession;
//
//public class CustomTabsService extends CustomTabsServiceConnection {
//
//    private static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
//    private Context context;
//    private TypedValue typedValue;
//    private String url;
//    private int color;
//    private CustomTabsClient customTabsClient;
//
//    public CustomTabsService(Context context, String url, int color) {
//        this.context = context;
//        this.url = url;
//        this.typedValue = new TypedValue();
//        this.color = color;
//    }
//
//    @Override
//    public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
//        client.warmup(0L);
//        CustomTabsSession customTabsSession = client.newSession(null);
//        customTabsClient = client;
//
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
//        //Optional: Configure the color of the address bar
//        builder.setToolbarColor(getColorFromTheme(getColorFromTheme(color)))
//        CustomTabsIntent customTabsIntent = builder.build();
//        customTabsIntent.launchUrl(context, Uri.parse(url));
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName componentName) {
//
//    }
//
//    public int getColorFromTheme(int idColor) {
//        Resources.Theme theme = context.getTheme();
//        theme.resolveAttribute(idColor, typedValue, true);
//        return typedValue.data;
//    }
//
//    public void bindCustomTabsClient() {
//        customTabsClient.bindCustomTabsService(this, CUSTOM_TAB_PACKAGE_NAME);
//    }
//
//}
