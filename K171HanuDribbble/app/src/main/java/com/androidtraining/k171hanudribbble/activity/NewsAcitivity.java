package com.androidtraining.k171hanudribbble.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidtraining.k171hanudribbble.R;


public class NewsAcitivity extends BaseActivity {

    private ImageView close_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_version);

        reflection();
        close_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsAcitivity.this, AboutInformationDribbbleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void reflection() {
        close_icon = findViewById(R.id.close_icon);
    }
}