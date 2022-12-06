package com.example.qt3_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter {
    ArrayList<Item> items;
    Context context;
    public ItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> items) {
        super(context, resource, items);

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Item item = items.get(position);
        TextView textView = convertView.findViewById(R.id.text);
        textView.setText(item.getTitle());
        return convertView;
    }

    public void add(@Nullable Item item) {
        items.add(item);
    }

    @Nullable
    @Override
    public Item getItem(int position) {
        return items.get(position);
    }
}
