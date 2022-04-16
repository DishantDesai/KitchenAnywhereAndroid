package com.kitchen_anywhere.kitchen_anywhere.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kitchen_anywhere.kitchen_anywhere.R;

public class settingListAdapter extends ArrayAdapter {
    private String[] settingOptions;
    private int[] settingOptionIcons;
    private Activity context;
    public settingListAdapter(Activity context, String[] settingOptions, int[] settingOptionIcons) {
        super(context, R.layout.chef_setting_list_view, settingOptions);
        this.context = context;
        this.settingOptions = settingOptions;
        this.settingOptionIcons = settingOptionIcons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.chef_setting_list_view, null, true);
        TextView settingLabel = (TextView) row.findViewById(R.id.setting_label);
        ImageView settingIcon = (ImageView) row.findViewById(R.id.setting_icon);
        settingLabel.setText(settingOptions[position]);
        settingIcon.setImageResource(settingOptionIcons[position]);
        return row;
    }
}
