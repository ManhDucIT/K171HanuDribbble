package com.example.driblesetting.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.example.driblesetting.R;
import com.example.driblesetting.webservice.models.UserModel;
import com.squareup.picasso.Picasso;

import java.io.File;

public class SettingActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private UserModel userModel;
    private Bundle bundle;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private ImageView profile_avatar;

    private TextView profile_description;
    private TextView profile_name;
    private TextView tv_openWebPageInApp;
    private View profile;
    private TextView sign_out;
    private View use_night_mode;
    private View data_saving_mode;
    private View gif_autoplay;
    private View clear_cache;
    private View on_dribbble;
    private View push_notifications;
    private View open_web_inApp;
    private View clear_search_history;
    private View more_about;
    private View more_share;
    private View more_rate;
    private View more_report;
    private View more_contact;
    private SwitchCompat use_night_mode_switch;
    private SwitchCompat data_saving_mode_switch;
    private SwitchCompat gif_autoplay_switch;
    private SwitchCompat open_web_switch;

    private Toolbar toolbar_setting_screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        reflection();

        bundle = intent.getExtras();
        userModel = (UserModel) bundle.getSerializable("user");
        profile_name.setText(userModel.getName());
        profile_description.setText("@" + userModel.getLogin());
        Picasso.get().load(userModel.getAvatar_url()).into(profile_avatar);


        String theme = sharedPreferences.getString("application_theme", "light");
        if (theme.equalsIgnoreCase("light")) {
            use_night_mode_switch.setChecked(false);
        } else {
            use_night_mode_switch.setChecked(true);
        }

        if (sharedPreferences.getBoolean("is_openWebPageInApp", false)) {
            tv_openWebPageInApp.setText("Links will open in Dribbble");
            open_web_switch.setChecked(true);
        }

        profile.setOnClickListener(this);
        profile.setOnClickListener(this);
        sign_out.setOnClickListener(this);
        use_night_mode.setOnClickListener(this);
        data_saving_mode.setOnClickListener(this);
        gif_autoplay.setOnClickListener(this);
        clear_cache.setOnClickListener(this);
        on_dribbble.setOnClickListener(this);
        push_notifications.setOnClickListener(this);
        open_web_inApp.setOnClickListener(this);
        clear_search_history.setOnClickListener(this);
        more_about.setOnClickListener(this);
        more_share.setOnClickListener(this);
        more_rate.setOnClickListener(this);
        more_report.setOnClickListener(this);
        more_contact.setOnClickListener(this);
        use_night_mode_switch.setOnCheckedChangeListener(this);
        data_saving_mode_switch.setOnCheckedChangeListener(this);
        gif_autoplay_switch.setOnCheckedChangeListener(this);
        open_web_switch.setOnCheckedChangeListener(this);


        //toolbar
        setSupportActionBar(toolbar_setting_screen);
        toolbar_setting_screen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private void reflection() {
        profile = findViewById(R.id.profile);
        sign_out = findViewById(R.id.sign_out);
        use_night_mode = findViewById(R.id.use_night_mode);
        data_saving_mode = findViewById(R.id.data_saving_mode);
        gif_autoplay = findViewById(R.id.gif_autoplay);
        clear_cache = findViewById(R.id.clear_cache);
        on_dribbble = findViewById(R.id.on_dribbble);
        push_notifications = findViewById(R.id.push_notifications);
        open_web_inApp = findViewById(R.id.open_web_inApp);
        clear_search_history = findViewById(R.id.clear_search_history);
        more_about = findViewById(R.id.more_about);
        more_share = findViewById(R.id.more_share);
        more_rate = findViewById(R.id.more_rate);
        more_report = findViewById(R.id.more_report);
        more_contact = findViewById(R.id.more_contact);
        tv_openWebPageInApp = findViewById(R.id.tv_openWebPageInApp);
        profile_name = findViewById(R.id.profile_name);
        profile_description = findViewById(R.id.profile_description);

        //switch
        use_night_mode_switch = findViewById(R.id.use_night_mode_switch);
        data_saving_mode_switch = findViewById(R.id.data_saving_mode_switch);
        gif_autoplay_switch = findViewById(R.id.gif_autoplay_switch);
        open_web_switch = findViewById(R.id.open_web_switch);
        profile_avatar = findViewById(R.id.profile_avatar);

        toolbar_setting_screen = findViewById(R.id.toolbar_setting_screen);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        intent = getIntent();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_out:
                signOutEvent();
                break;
            case R.id.use_night_mode:
                use_night_mode_switch.setChecked((use_night_mode_switch.isChecked()) ? false : true);
                break;
            case R.id.data_saving_mode:
                data_saving_mode_switch.setChecked((data_saving_mode_switch.isChecked()) ? false : true);
                break;
            case R.id.gif_autoplay:
                gif_autoplay_switch.setChecked((gif_autoplay_switch.isChecked()) ? false : true);
                break;
            case R.id.clear_cache:
                clearCacheEvent(view);
                break;
            case R.id.on_dribbble:
                onDribbleNotificationEvent();
                break;
            case R.id.push_notifications:
                onPhoneNotificationEvent();
                break;
            case R.id.open_web_inApp:
                open_web_switch.setChecked((open_web_switch.isChecked() == true) ? false : true);
                break;
            case R.id.clear_search_history:
                Toast.makeText(this, "clear_search_history", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_about:
                moreInformationAboutDribbbleEvent();
                break;
            case R.id.more_contact:
                contactEvent();
                break;
            case R.id.more_rate:
                Toast.makeText(this, "more_rate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_report:
                reportEvent();
                break;
            case R.id.more_share:
                shareAppEvent(view);
                break;
        }
    }

    // it should be divide to another thread
    private void clearCacheEvent(View view) {
        if (deleteCache(getCacheDir())) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean deleteCache(File dir) {
        if (dir != null && dir.isDirectory()) {
            File[] lstFiles = dir.listFiles();
            for (File file : lstFiles) {
                if (!deleteCache(file)) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    private void shareAppEvent(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Sharing Dribbble to your friends");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void moreInformationAboutDribbbleEvent() {
        Intent intent = new Intent(this, AboutInformationDribbbleActivity.class);
        startActivity(intent);
    }

    private void onPhoneNotificationEvent() {
        Intent intent = new Intent(this, OnPhoneNotificationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.use_night_mode_switch:
                useNightModeSwitchEvent(compoundButton, b);
                break;
            case R.id.data_saving_mode_switch:
                dataSavingModeSwitchEvent(compoundButton, b);
                break;
            case R.id.gif_autoplay_switch:
                gifAutoPlaySwitchEvent(compoundButton, b);
                break;
            case R.id.open_web_switch:
                openWebSwitchEvent(compoundButton, b);
                break;
        }
    }

    private void useNightModeSwitchEvent(CompoundButton compoundButton, boolean b) {
        if (b) {
            editor.putString("application_theme", "dark");
            editor.apply();
            setTheme(R.style.AppThemeDark);
            recreate();
        } else {
            editor.putString("application_theme", "light");
            editor.apply();
            setTheme(R.style.AppThemeLight);
            recreate();
        }
    }

    private void dataSavingModeSwitchEvent(CompoundButton compoundButton, boolean b) {
        if (b) {
            Toast.makeText(this, "Data Mode changed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Mode default", Toast.LENGTH_SHORT).show();
        }
    }

    private void gifAutoPlaySwitchEvent(CompoundButton compoundButton, boolean b) {
        if (b) {
            new AlertDialog.Builder(this)
                    .setTitle("Warning")
                    .setMessage(getText(R.string.gif_auto_play_warning))
                    .setPositiveButton("GOT IT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
            Toast.makeText(this, "Gif auto changed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gif auto default", Toast.LENGTH_SHORT).show();
        }
    }

    private void openWebSwitchEvent(CompoundButton compoundButton, boolean b) {
        if (b) {
            sharedPreferences.edit().putBoolean("is_openWebPageInApp", true).commit();
            tv_openWebPageInApp.setText("Links will open in Dribbble");
        } else {
            sharedPreferences.edit().putBoolean("is_openWebPageInApp", false).commit();
            tv_openWebPageInApp.setText("Links will send you to the browser");
        }
    }

    private void signOutEvent() {
        new AlertDialog.Builder(this)
                .setTitle("Sign out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("GOT IT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //sign out and delete all actity in back stack
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    private void reportEvent() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", getString(R.string.report_email), null));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.report_title));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.report_message));
        startActivity(intent);
    }

    private void contactEvent() {
        reportEvent();
    }

    private void onDribbleNotificationEvent() {
        Intent intent = new Intent(this, OnDribbbleNotificationActivity.class);
        startActivity(intent);
    }
}