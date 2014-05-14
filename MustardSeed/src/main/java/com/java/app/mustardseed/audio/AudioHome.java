package com.java.app.mustardseed.audio;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.java.app.mustardseed.util.Constants;
import com.java.app.mustardseed.util.ListAdapterProvider;
import com.java.app.mustardseed.R;

/**
 * Created by danscott on 06/02/2014.
 * Activity which displays all audio files
 * available on the micro sd.
 */
public class AudioHome extends Activity {

    private ListView listView;
    private SimpleAdapter adapter;

    private ListAdapterProvider adapterProvider;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_audio_home);

        listView = (ListView) findViewById(R.id.listView);

        adapterProvider = new ListAdapterProvider(
                Constants.AUDIO_URI, Constants.AUDIO_PROJECTION,
                getContentResolver(), this
        );

        adapter = adapterProvider.getAdapter(Constants.AUDIO_TITLE, Constants.AUDIO_DURATION);
        System.out.println("Adapter Created!");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.audio_home_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
       // SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
         //       .getActionView();
        //searchView.setSearchableInfo(searchManager
          //      .getSearchableInfo(getComponentName()));



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapterProvider.getOnItemClickListener(
                new Intent(AudioHome.this, AudioPlay.class), Constants.AUDIO_DATA, this
        ));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}