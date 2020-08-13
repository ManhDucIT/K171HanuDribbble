package com.androidtraining.k171hanudribbble.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.androidtraining.k171hanudribbble.CustomTabsIntentBuilder;
import com.androidtraining.k171hanudribbble.GettingColorFromTheme;
import com.androidtraining.k171hanudribbble.R;
import com.androidtraining.k171hanudribbble.activity.MainActivity;
import com.androidtraining.k171hanudribbble.activity.SettingActivity;
import com.androidtraining.k171hanudribbble.webservice.models.UserModel;

public class MoreFragment extends Fragment implements View.OnClickListener {

    private UserModel userModel;
    private boolean is_openWebPageInApp;
    private View mode_option;
    private View setting_option;
    private View jobs_option;
    private View report_option;
    private View shop_option;
    private SwitchCompat mode_right_switch;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_more_layout, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        reflection();
        if (sharedPreferences.getString("application_theme", "light").equalsIgnoreCase("light")) {
            mode_right_switch.setChecked(false);
        } else {
            mode_right_switch.setChecked(true);
        }



        mode_option.setOnClickListener(this);

        setting_option.setOnClickListener(this);

        jobs_option.setOnClickListener(this);

        shop_option.setOnClickListener(this);

        report_option.setOnClickListener(this);

        mode_right_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                if (b) {
                    editor.putString("application_theme", "dark");
                    editor.apply();
                    getContext().setTheme(R.style.AppThemeDark);
                } else {
                    editor.putString("application_theme", "light");
                    editor.apply();
                    getContext().setTheme(R.style.AppThemeLight);
                }
                fragmentTransaction.detach(MoreFragment.this).attach(MoreFragment.this).commit();
                getActivity().recreate();
            }
        });

    }

    private void reflection() {
        mode_option = getView().findViewById(R.id.mode_option);
        setting_option = getView().findViewById(R.id.setting_option);
        shop_option = getView().findViewById(R.id.shop_option);
        report_option = getView().findViewById(R.id.report_option);
        jobs_option = getView().findViewById(R.id.jobs_option);
        mode_right_switch = getView().findViewById(R.id.mode_right_switch);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
        is_openWebPageInApp = sharedPreferences.getBoolean("is_openWebPageInApp", false);
        userModel = MainActivity.user;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mode_option:
                modeOptionEvent(view);
                break;
            case R.id.setting_option:
                settingOptionEvent(view);
                break;
            case R.id.jobs_option:
                jobOptionEvent(view);
                break;
            case R.id.shop_option:
                shopOptionEvent(view);
                break;
            case R.id.report_option:
                reportOptionEvent(view);
                break;
        }
    }

    private void reportOptionEvent(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", getString(R.string.report_email), null));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.report_title));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.report_message));
        startActivity(intent);
    }

    private void shopOptionEvent(View view) {
        String url = getResources().getString(R.string.shopLink);
        if(!is_openWebPageInApp) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else {
            CustomTabsIntentBuilder.getInstance().build().launchUrl(getContext(), Uri.parse(url));
        }
    }

    private void jobOptionEvent(View view) {
        String url = getResources().getString(R.string.jobLink);
        if(!is_openWebPageInApp) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else {
            CustomTabsIntentBuilder.getInstance().setToolbarColor(new GettingColorFromTheme(getContext()).getColorFromTheme(R.attr.colorPrimary));
            CustomTabsIntentBuilder.getInstance().build().launchUrl(getContext(), Uri.parse(url));
        }
    }

    private void settingOptionEvent(View view) {
        Intent intent = new Intent(getContext(), SettingActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", userModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void modeOptionEvent(View view) {
        mode_right_switch.setChecked(mode_right_switch.isChecked() ? false : true);
    }
}
