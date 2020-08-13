package com.androidtraining.k171hanudribbble;


import androidx.browser.customtabs.CustomTabsIntent;

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
