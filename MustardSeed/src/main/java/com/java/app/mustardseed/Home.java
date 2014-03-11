package com.java.app.mustardseed;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.java.app.mustardseed.audio.AudioHome;
import com.java.app.mustardseed.pdf.PdfHome;
import com.java.app.mustardseed.video.VideoHome;


public class Home extends Activity {

    private Button audio;
    private Button video;
    private Button text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        text = (Button) findViewById(R.id.text_button);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(Home.this, PdfHome.class)
                );
            }
        });

        audio = (Button) findViewById(R.id.audio_button);
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(Home.this, AudioHome.class)
                );
            }
        });

        video = (Button) findViewById(R.id.video_button);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(Home.this, VideoHome.class)
                );
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_about:

                /*
                Display a dialog telling the user the version of the app.
                 */
                AlertDialog.Builder version =
                        new AlertDialog.Builder(this);

                try {
                String versionName =
                        getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                version.setTitle("Version " +  versionName);
                version.setMessage("The current version is: " + versionName);
                version.show();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
