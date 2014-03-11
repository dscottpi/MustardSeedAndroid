package com.java.app.mustardseed.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.java.app.mustardseed.util.Constants;
import com.java.app.mustardseed.util.ListAdapterProvider;
import com.java.app.mustardseed.R;


/**
 * Created by danscott on 08/02/2014
 *
 * Activity which displays all available videos on SD Card in a ListView.
 *
 * On selection of a video from the ListView, the Activity will start the VideoView
 * activity which will display the video.
 *
 */
public class VideoHome extends Activity {

    private ListView videosListView;
    private SimpleAdapter listViewAdapter;

    private ListAdapterProvider adapterProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_home);

        videosListView = (ListView) findViewById(R.id.videosListView);

        /*
        Create the ListAdapterProvider, passing the URI for external video content, the String
        Array which contains the query projection, a content resolver and the context.
         */
        adapterProvider = new ListAdapterProvider(Constants.VIDEO_URI, Constants.VIDEO_PROJECTION,
                getContentResolver(), this);


        /*
        Create the SimpleAdapter, passing the arguments you want to appear in the List Item within
        the ListView. i.e. The title of the video and the length of the video.
         */
        listViewAdapter = adapterProvider.getAdapter(
                Constants.VIDEO_TITLE, Constants.VIDEO_DURATION);

        videosListView.setAdapter(listViewAdapter);

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

        /*
        Create an OnItemClickListener for handling list item clicks in the listview.
        You pass the intent which you want to start when an item is clicked, as well as a string
        which tells the cursor used what to look for and the current activity.
         */
        videosListView.setOnItemClickListener(
                adapterProvider.getOnItemClickListener(
                        new Intent(VideoHome.this, VideoView.class), Constants.VIDEO_DATA, this
        ));
    }
}
