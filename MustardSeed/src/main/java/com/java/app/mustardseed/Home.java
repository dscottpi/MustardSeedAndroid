package com.java.app.mustardseed;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

public class Home extends ActionBarActivity {
=======
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
>>>>>>> 831ce17d24b66bea47d31099c0d4fb7590ac6c3c

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

<<<<<<< HEAD
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
=======
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

>>>>>>> 831ce17d24b66bea47d31099c0d4fb7590ac6c3c
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
<<<<<<< HEAD
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        Button audio;
        Button video;
        Button text;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);

            text = (Button)rootView.findViewById(R.id.text_button);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //TODO: Implement text activity
                }
            });

            audio = (Button)rootView.findViewById(R.id.audio_button);
            audio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(
                            new Intent(getActivity(), AudioHome.class)
                    );
                }
            });

            video = (Button)rootView.findViewById(R.id.video_button);
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(
                            new Intent(getActivity(), VideoHome.class)
                    );
                }
            });

            return rootView;
        }
    }

=======
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
>>>>>>> 831ce17d24b66bea47d31099c0d4fb7590ac6c3c
}
