package com.androidtraining.Listener;

import android.widget.ImageView;
import android.widget.TextView;

public interface INoteItemListener {
    void onItemClick(ImageView view, TextView textView, int position);
}
