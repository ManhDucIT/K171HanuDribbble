package com.example.driblesetting.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driblesetting.CustomTabsIntentBuilder;
import com.example.driblesetting.GettingColorFromTheme;
import com.example.driblesetting.handlerEvent.IOpenSourceItemClickEvent;
import com.example.driblesetting.R;
import com.example.driblesetting.models.OpenSourceInformatonItem;

import java.util.List;

public class OpenSourceInformationAdapter extends RecyclerView.Adapter {

    private SharedPreferences sharedPreferences;
    private List<OpenSourceInformatonItem> lst_item;
    private Context context;

    public OpenSourceInformationAdapter(List<OpenSourceInformatonItem> lst_item, Context context) {
        this.lst_item = lst_item;
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.opensource_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final OpenSourceInformatonItem item = lst_item.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.os_title.setText(item.getOs_title());
        viewHolder.os_author.setText(item.getOs_author());
        viewHolder.os_version.setText(item.getOs_version());
        viewHolder.setItemClickListener(new IOpenSourceItemClickEvent() {
            @Override
            public void onItemClick(View view, int position) {
                Resources.Theme theme = context.getTheme();
                theme.resolveAttribute(R.attr.colorPrimary, new TypedValue(), true);
                String url = item.getOs_link();
                if(!sharedPreferences.getBoolean("is_openWebPageInApp", false)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(intent);
                } else {
                    CustomTabsIntentBuilder.getInstance().setToolbarColor(new GettingColorFromTheme(context).getColorFromTheme(R.attr.colorPrimary));
                    CustomTabsIntentBuilder.getInstance().build().launchUrl(context, Uri.parse(url));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lst_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public IOpenSourceItemClickEvent itemClickListener;
        public TextView os_title;
        public TextView os_author;
        public TextView os_version;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            os_title = itemView.findViewById(R.id.os_title);
            os_author = itemView.findViewById(R.id.os_author);
            os_version = itemView.findViewById(R.id.os_version);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(IOpenSourceItemClickEvent iOpenSourceItemClickEvent) {
            this.itemClickListener = iOpenSourceItemClickEvent;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
