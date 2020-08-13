package com.example.driblesetting;


import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsSession;

public class CustomTabsIntentBuilder {
    private static CustomTabsIntentBuilder customTabsIntentBuilder;
    private static CustomTabsIntent.Builder builder;

    private CustomTabsIntentBuilder() {
        builder = new CustomTabsIntent.Builder();
    }

    public static CustomTabsIntent.Builder getInstance() {
        if (customTabsIntentBuilder == null) {
            customTabsIntentBuilder = new CustomTabsIntentBuilder();
        }
        return builder;
    }
}
