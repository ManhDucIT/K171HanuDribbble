package com.androidtraining.k171hanudribbble.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.androidtraining.k171hanudribbble.CustomTabsIntentBuilder;
import com.androidtraining.k171hanudribbble.GettingColorFromTheme;
import com.androidtraining.k171hanudribbble.R;


public class AboutInformationDribbbleActivity extends BaseActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;

    private View twitter_option;
    private View instagram_option;
    private View facebook_option;
    private View stories_option;
    private View shop_option;
    private View news_option;
    private View open_sources_option;
    private Toolbar toolbar_about_information_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_information_dribbble);
        reflection();

        setSupportActionBar(toolbar_about_information_screen);
        toolbar_about_information_screen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutInformationDribbbleActivity.this, SettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        news_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutInformationDribbbleActivity.this, NewsAcitivity.class);
                startActivity(intent);
            }
        });

        open_sources_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutInformationDribbbleActivity.this, OpenSourcesVersionAcitivity.class);
                startActivity(intent);
            }
        });
        shop_option.setOnClickListener(this);
        stories_option.setOnClickListener(this);
        twitter_option.setOnClickListener(this);
        facebook_option.setOnClickListener(this);
        instagram_option.setOnClickListener(this);
    }

    private void reflection() {
        twitter_option = findViewById(R.id.twitter_option);
        instagram_option = findViewById(R.id.instagram_option);
        facebook_option = findViewById(R.id.facebook_option);
        stories_option = findViewById(R.id.stories_option);
        shop_option = findViewById(R.id.shop_option);
        news_option = findViewById(R.id.news_option);
        open_sources_option = findViewById(R.id.open_sources_option);
        toolbar_about_information_screen = findViewById(R.id.toolbar_about_information_screen);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AboutInformationDribbbleActivity.this);
    }

    @Override
    public void onClick(View view) {
        String url = "";
        switch (view.getId()) {
            case R.id.stories_option:
                url = getResources().getString(R.string.storiesLink);
                break;
            case R.id.shop_option:
                url = getResources().getString(R.string.shopLink);
                break;
            case R.id.twitter_option:
                url = getResources().getString(R.string.twitterLink);
                break;
            case R.id.instagram_option:
                url = getResources().getString(R.string.instagramLink);
                break;
            case R.id.facebook_option:
                url = getResources().getString(R.string.facebookLink);
                break;
        }
        if (!sharedPreferences.getBoolean("is_openWebPageInApp", false)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            AboutInformationDribbbleActivity.this.startActivity(intent);
        } else {
            CustomTabsIntentBuilder
                    .getInstance()
                    .setToolbarColor(new GettingColorFromTheme(AboutInformationDribbbleActivity.this)
                            .getColorFromTheme(R.attr.colorPrimary));
            CustomTabsIntentBuilder.getInstance()
                    .build()
                    .launchUrl(AboutInformationDribbbleActivity.this, Uri.parse(url));
        }
    }
}