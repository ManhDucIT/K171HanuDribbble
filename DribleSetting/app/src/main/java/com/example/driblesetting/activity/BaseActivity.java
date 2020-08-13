package com.example.driblesetting.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.driblesetting.R;

public class BaseActivity extends AppCompatActivity
{
    private String currentTheme;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPreferences.getString("application_theme","light");
        if (currentTheme != null) {
            setAppTheme(currentTheme);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String theme = sharedPreferences.getString("application_theme", "light");
        if(!currentTheme.equalsIgnoreCase(theme)) {
            setAppTheme(theme);
            recreate();
        }
    }

    private void setAppTheme(String currentTheme) {
        if (currentTheme.equalsIgnoreCase("light")) {
            setTheme(R.style.AppThemeLight);
        } else if (currentTheme.equalsIgnoreCase("dark")) {
            setTheme(R.style.AppThemeDark);
        }
    }
}
