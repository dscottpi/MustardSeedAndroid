package com.java.app.mustardseed;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by danscott on 06/02/2014.
 * Activity which displays all audio files
 * available on the micro sd.
 */
public class AudioHome extends Activity {

    private MediaPlayer mediaPlayer;
    private String selection;
    private ListView listView;
    private ArrayList<HashMap<String, String>> audioFiles;
    private HashMap<String, String> audioFilesList;
    private Cursor c;
    private int index;
    private Uri audio;
    private SimpleAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_audio_home);
        /*
        Uri for external audio content.
         */
        audio = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        audioFiles = new ArrayList<HashMap<String, String>>();

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        listView = (ListView) findViewById(R.id.listView);


        adapter = new SimpleAdapter(this, audioFiles,
                android.R.layout.simple_list_item_2, new String[] {"Title", "Duration"},
                new int[] {android.R.id.text1, android.R.id.text2});

    }

    @Override
    protected void onResume() {
        super.onResume();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);

        audioFiles.clear();
        adapter.notifyDataSetChanged();

        /*
        String[] projection is the parameters which the audio file
        must contain. Chance projection to null if you want to return
        every single audio file labelled on the micro sd.
         */
        String[] projection = { MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION };

        /*
        Query the audio uri with the parameters set by projection and selection.
        (selection is null at the moment).
         */
        c = getContentResolver().query(audio, projection, selection, null, null);

        if(c != null) {
            if(c.moveToFirst()) {
                do {
                    audioFilesList = new HashMap<String, String>();
                    String title =
                            c.getString
                                    (c.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String duration =
                            c.getString
                                    (c.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    System.out.println(title + " : " + duration);

                    audioFilesList.put("Title", title);
                    audioFilesList.put("Duration", duration);

                    audioFiles.add(audioFilesList);
                    adapter.notifyDataSetChanged();

                } while(c.moveToNext());

            }
        }

    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view,
                                int position, long id) {
            // Move the cursor to the position that the item is in in the listview.
            c.moveToPosition(position);

            // index is the column which contains the actual audio data.
            index = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);

            // Get the selected song.
            HashMap<String, String> trackTitle = audioFiles.get(position);
            String title = trackTitle.get("Title");
            System.out.println(title);

            String file = c.getString(index);

            /*
            Start a new activity which plays the audio file selected from the
            listview. Pass the location of the audio file as well as the title.
             */
            Intent playAudio = new Intent(AudioHome.this, AudioPlay.class);
            playAudio.putExtra("FILE", file);
            playAudio.putExtra("TITLE", title);
            startActivity(playAudio);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.release();

    }
}