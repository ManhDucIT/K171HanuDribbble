package com.example.driblesetting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driblesetting.R;
import com.example.driblesetting.models.NotificationItem;

import java.util.List;

public class OnDribbleListNotificationsAdapter extends RecyclerView.Adapter {

    private List<NotificationItem> lstoptions;
    private Context context;

    public OnDribbleListNotificationsAdapter(List<NotificationItem> lstoptions, Context context) {
        this.lstoptions = lstoptions;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notification_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotificationItem item = lstoptions.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(item.getItem_name());
        viewHolder.switchCompat.setChecked(item.getItem_checked());
        viewHolder.switchChangeEvent();
    }

    @Override
    public int getItemCount() {
        return lstoptions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        View layout_notifications_option;
        TextView textView;
        SwitchCompat switchCompat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_notifications_option = itemView.findViewById(R.id.layout_notifications_option);
            textView = itemView.findViewById(R.id.tv_notifications_option);
            switchCompat = itemView.findViewById(R.id.notifications_switch);

            layout_notifications_option.setOnClickListener(this);

        }

        public void switchChangeEvent() {
            switchCompat.setOnCheckedChangeListener(this);
        }

        @Override
        public void onClick(View view) {
            convertSwitch(switchCompat);
        }

        public void convertSwitch(SwitchCompat switchCompat) {
            if (switchCompat.isChecked()) {
                switchCompat.setChecked(false);
            } else {
                switchCompat.setChecked(true);
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (getAdapterPosition()) {
                case 0:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "buckets checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "buckets unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "comments checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "comments unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 2:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "comment likes checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "comment likes unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 3:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "followers checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "followers unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 4:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "likes checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "likes unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 5:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "mentions checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "mentions unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 6:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "rebounds checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "rebounds unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 7:
                    if (switchCompat.isChecked()) {
                        Toast.makeText(context, "other checked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "other unchecked", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
