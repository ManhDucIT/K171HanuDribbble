package com.androidtraining.k171hanudribbble.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.androidtraining.k171hanudribbble.R;
import com.androidtraining.k171hanudribbble.adapter.OpenSourceInformationAdapter;
import com.androidtraining.k171hanudribbble.models.OpenSourceInformatonItem;

import java.util.ArrayList;
import java.util.List;

public class OpenSourcesVersionAcitivity extends BaseActivity {

    private Toolbar toolbar_open_source_screen;
    private List<OpenSourceInformatonItem> lst = new ArrayList<>();
    private RecyclerView lst_opensource_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_sources);
        reflection();
        bindData();

        setSupportActionBar(toolbar_open_source_screen);
        toolbar_open_source_screen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpenSourcesVersionAcitivity.this, AboutInformationDribbbleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this);
        OpenSourceInformationAdapter adapter = new OpenSourceInformationAdapter(lst, this);
        lst_opensource_version.setAdapter(adapter);
        lst_opensource_version.setLayoutManager(layoutManager);
    }

    private void reflection() {
        lst = new ArrayList<>();
        lst_opensource_version = findViewById(R.id.lst_opensource_version);
        toolbar_open_source_screen = findViewById(R.id.toolbar_open_source_screen);
    }

    private void bindData() {
        lst.add(0,new OpenSourceInformatonItem("ButterKnife",
                "Jake Wharton",
                "Apache License, Version 2.0",
                "https://github.com/JakeWharton/butterknife"));
        lst.add(1,new OpenSourceInformatonItem("Android Tooltip",
                "Sephiroth74",
                "Apache License, Version 2.0",
                "https://github.com/sephiroth74/android-target-tooltip"));
        lst.add(2,new OpenSourceInformatonItem("BigLmageViewer",
                "Piasy",
                "MIT License",
                "https://github.com/Piasy/BigImageViewer"));
        lst.add(3,new OpenSourceInformatonItem("Crashlytics",
                "Dave Benson",
                "try.crashlytics.com/terms/opensource.txt",
                "https://firebase.google.com/products/crashlytics?utm_source=crashlytics_marketing&utm_medium=redirect&utm_campaign=crashlytics_redirect"));
        lst.add(4,new OpenSourceInformatonItem("ButterKnife",
                "Jake Wharton",
                "Apache License, Version 2.0",
                "https://github.com/JakeWharton/butterknife"));
        lst.add(5,new OpenSourceInformatonItem("Android Tooltip",
                "Sephiroth74",
                "Apache License, Version 2.0",
                "https://github.com/sephiroth74/android-target-tooltip"));
        lst.add(6,new OpenSourceInformatonItem("BigLmageViewer",
                "Piasy",
                "MIT License",
                "https://github.com/Piasy/BigImageViewer"));
        lst.add(7,new OpenSourceInformatonItem("Crashlytics",
                "Dave Benson",
                "try.crashlytics.com/terms/opensource.txt",
                "https://firebase.google.com/products/crashlytics?utm_source=crashlytics_marketing&utm_medium=redirect&utm_campaign=crashlytics_redirect"));
    }
}