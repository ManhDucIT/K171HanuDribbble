package com.example.driblesetting.activity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.driblesetting.R;
import com.example.driblesetting.adapter.OnPhoneListNotificationAdapter;
import com.example.driblesetting.models.NotificationItem;

import java.util.ArrayList;
import java.util.List;

public class OnPhoneNotificationActivity extends BaseActivity {
    private List<NotificationItem> listOptions = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView lst_option;

    private Toolbar toolbar_onPhone_notification_screen;
    private View from_everyone;
    private View from_playersonly;
    private RadioButton from_everyone_radio_button;
    private RadioButton from_playersonly_radio_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onphone_notification);
        bindData();
        reflection();

        setSupportActionBar(toolbar_onPhone_notification_screen);
        toolbar_onPhone_notification_screen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnPhoneNotificationActivity.this, SettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        from_everyone_radio_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                from_playersonly_radio_button.setChecked(from_everyone_radio_button.isChecked() ? false : true);
            }
        });
        from_playersonly_radio_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                from_everyone_radio_button.setChecked(from_playersonly_radio_button.isChecked() ? false : true);
            }
        });

        from_everyone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!from_everyone_radio_button.isChecked()) {
                    from_everyone_radio_button.setChecked(true);
                }
            }
        });

        from_playersonly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!from_playersonly_radio_button.isChecked()) {
                    from_playersonly_radio_button.setChecked(true);
                }
            }
        });


    }

    private void reflection() {

        from_everyone = findViewById(R.id.from_everyone);
        from_playersonly = findViewById(R.id.from_playersonly);
        from_everyone_radio_button = findViewById(R.id.from_everyone_radio_button);
        from_playersonly_radio_button = findViewById(R.id.from_playersonly_radio_button);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new OnPhoneListNotificationAdapter(listOptions, this);
        lst_option = findViewById(R.id.lst_notifications);
        lst_option.setAdapter(adapter);
        lst_option.setLayoutManager(layoutManager);

        toolbar_onPhone_notification_screen = findViewById(R.id.toolbar_onPhone_notification_screen);
    }

    private void bindData() {
        listOptions.add(0, new NotificationItem("Buckets", true));
        listOptions.add(1, new NotificationItem("Comments", false));
        listOptions.add(2, new NotificationItem("Commment Likes", true));
        listOptions.add(3, new NotificationItem("Followers", false));
        listOptions.add(4, new NotificationItem("Likes", true));
        listOptions.add(5, new NotificationItem("Mentions", false));
        listOptions.add(6, new NotificationItem("Rebounds", true));
        listOptions.add(7, new NotificationItem("Other", false));
    }

}