package com.java.app.mustardseed;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class VideoHome extends Activity {

    Cursor cursor;
    int index;
    Uri video;
    String selection;
    ListView videosListView;
    ArrayList<HashMap<String, String>> videoFiles;
    HashMap<String, String> videoFilesList;
    SimpleAdapter listViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_home);
        videoFiles = new ArrayList<HashMap<String, String>>();

        videosListView = (ListView) findViewById(R.id.videosListView);

        listViewAdapter = new SimpleAdapter(this, videoFiles,
                android.R.layout.simple_list_item_2, new String[]{"Title", "Size"},
                new int[]{android.R.id.text1, android.R.id.text2});

        videosListView.setAdapter(listViewAdapter);

        video = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        videosListView.setAdapter(listViewAdapter);
        videosListView.setOnItemClickListener(onItemClickListener);
        videoFiles.clear();
        listViewAdapter.notifyDataSetChanged();

        String[] projection = {
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.SIZE
        };

        cursor = getContentResolver().query(
                video, projection, selection, null, null
        );

        if (cursor != null) {

            if (cursor.moveToFirst()) {
                do {

                    videoFilesList = new HashMap<String, String>();

                    String title = cursor.getString(
                            cursor.getColumnIndex(MediaStore.Video.Media.TITLE)
                    );

                    String size = cursor.getString(
                            cursor.getColumnIndex(MediaStore.Video.Media.SIZE)
                    ) + "mb";

                    videoFilesList.put("Title", title);
                    videoFilesList.put("Size", size);
                    videoFiles.add(videoFilesList);

                    listViewAdapter.notifyDataSetChanged();

                } while (cursor.moveToNext());
            }

        }
    }

    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view,
                                        int position, long id) {

                    cursor.moveToPosition(position);

                    index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);

                    String file = cursor.getString(index);

                    Intent startView = new Intent(VideoHome.this, VideoView.class);
                    startView.putExtra("FILE", file);
                    startActivity(startView);
                }
            };

}
