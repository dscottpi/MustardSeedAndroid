package com.java.app.mustardseed;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the action bar if the android version is recent enough to contain one.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().hide();
        }

        try {
            // Go full screen.
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } catch (Exception ne) {
            ne.printStackTrace();
        }


        setContentView(R.layout.activity_splash);

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
        // automatically handle clicks on the com.java.app.mustardseed.audio.AudioHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Return true if there is external storage mounted, false otherwise.
     */
    public boolean SDCardIsMounted() {
        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }

}
