package com.example.driblesetting.models;

public class NotificationItem {
    private String item_name;
    private boolean item_checked;

    public NotificationItem(String item_name, boolean item_checked) {
        this.item_checked = item_checked;
        this.item_name = item_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public boolean getItem_checked() {
        return item_checked;
    }

    public void setItem_checked(boolean item_checked) {
        this.item_checked = item_checked;
    }
}
