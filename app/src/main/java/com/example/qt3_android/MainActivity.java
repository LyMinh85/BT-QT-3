package com.example.qt3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    TopicsAdapter topicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Topic mặc định
        ArrayList<Topic> topics = new ArrayList<>(Arrays.asList(
                new Topic(292, "Proteins"),
                new Topic(293, "Amino Acids"),
                new Topic(294, "Grains and Starches"),
                new Topic(295, "Fibers and Legumes"),
                new Topic(296, "Vitamins"),
                new Topic(297, "Minerals"),
                new Topic(298, "Nutraceuticals")
        ));

        ListView listTopicView = findViewById(R.id.listTopicView);
        topicsAdapter = new TopicsAdapter(MainActivity.this, android.R.layout.simple_list_item_1, topics);
        listTopicView.setAdapter(topicsAdapter);

        // Mở Topic khi bấm vô item
        listTopicView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, TopicActivity.class);
            Topic topic = topicsAdapter.getItem(i);
            if (topic == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("url", topic.getUrl());
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
