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
import java.util.Arrays;
import java.util.List;

public class TopicsAdapter extends ArrayAdapter {
    ArrayList<Topic> topics = new ArrayList<>();
    Context context;
    public TopicsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Topic> objects) {
        super(context, resource, objects);
        this.context = context;
        topics = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Topic topic = topics.get(position);
        TextView textView = convertView.findViewById(R.id.text);
        textView.setText(topic.getName());
        Log.d("Topic", topic.getUrl());
        return convertView;
    }

    @Nullable
    @Override
    public Topic getItem(int position) {
        return topics.get(position);
    }
}
