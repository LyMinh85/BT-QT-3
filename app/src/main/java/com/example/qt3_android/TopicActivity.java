package com.example.qt3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TopicActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Item> items = new ArrayList<>();
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");

        //Lấy dữ liệu từ url của topic
        new ReadRss().execute("https://www.petfoodindustry.com/rss/topic/" + url);
        listView = findViewById(R.id.listViewItem);
        itemAdapter = new ItemAdapter(TopicActivity.this, R.layout.list_item, items);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            // Mở dialog của item
            dialog(i);
        });
    }

    //Class đọc dữ liệu async
    private class ReadRss extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader= new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return content.toString();
        }

        //Gọi hàm khi có dữ liệu
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Parse xml
            XMLFood parser = new XMLFood();
            Document document= parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");

            // Lấy từng node add vô ItemAdapter
            for(int i=0;i<nodeList.getLength();i++) {
                Element element = (Element) nodeList.item(i);
                String title = parser.getValue(element, "title") ;
                String description = parser.getValue(element, "description");
                String link = parser.getValue(element,"link");
                String publicDate = parser.getValue(element,"pubDate");

                NodeList mediaNodes = element.getElementsByTagName("media:content");
                Element mediaNode = (Element) mediaNodes.item(0);
                String mediaUrl = "";
                String mediaContent = "";
                if (mediaNode != null) {
                    mediaUrl = mediaNode.getAttribute("url");
                    mediaContent = parser.getValue(mediaNode, "media:description");
                }

                Item item = new Item(title, description, publicDate, mediaUrl, mediaContent, link);
                itemAdapter.add(item);
                itemAdapter.notifyDataSetChanged();
            }



        }
    }
    private void dialog(int position)
    {
        final Dialog dialog = new Dialog(TopicActivity.this);
        dialog.setContentView(R.layout.dialog_main);
        Button btnMore= dialog.findViewById(R.id.btnmore);
        Button btnClose = dialog.findViewById(R.id.btnclose);

        TextView title= dialog.findViewById(R.id.title);
        TextView description= dialog.findViewById(R.id.descripton);
        TextView date= dialog.findViewById(R.id.date);
        ImageView imageView = dialog.findViewById(R.id.image_view);

        Item item = itemAdapter.getItem(position);
        if (item == null) {
            return;
        }
        title.setText(item.getTitle());
        description.setText(item.getDescription());
        date.setText(item.getPublicDate());

        // class load hình ảnh async
        new LoadImage(imageView).execute(item.getMediaUrl());

        dialog.setCancelable(false);

        btnMore.setOnClickListener(view -> {
            Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
            startActivity(intent);
        });

        btnClose.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
                return null;
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
                imageView.setMaxWidth(500);
            } else {
                imageView.setImageBitmap(null);
            }

        }
    }

}