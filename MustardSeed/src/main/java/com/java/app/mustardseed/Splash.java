package com.java.app.mustardseed;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class Splash extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //GO FULL screen
            getActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }


        setContentView(R.layout.activity_splash);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                /*
                If the phone has an sd card continue to the normal
                home screen. If not, display the activity which
                asks the user to insert a micro sd card.
                 */
                if (SDCardIsMounted()) {
                    i = new Intent(Splash.this, Home.class);
                } else {
                    i = new Intent(Splash.this, InsertSD.class);
                }
                startActivity(i);
                Splash.this.finish();
            }
        }, 3000);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.java.app.mustardseed.AudioHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
            return rootView;
        }
    }

    public static boolean SDCardIsMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
        );
    }
}
