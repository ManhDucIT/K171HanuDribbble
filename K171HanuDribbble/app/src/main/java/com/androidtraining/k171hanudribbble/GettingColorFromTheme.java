package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class GettingColorFromTheme {

    private Context context;
    private TypedValue typedValue;

    public GettingColorFromTheme(Context context) {
        this.context = context;
        this.typedValue = new TypedValue();
    }

    public int getColorFromTheme(int idColor) {
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(idColor, typedValue, true);
        return typedValue.data;
    }
}
