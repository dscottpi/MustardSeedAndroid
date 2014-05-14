package com.java.app.mustardseed.video;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

import com.java.app.mustardseed.R;


/**
 * Created by danscott on 09/02/2014.
 */
public class VideoView extends Activity {

    private android.widget.VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Theoretically this should hide the title bar on older versions
        however it doesn't seem to work. It doesn't crash the app
        so just leave it here for now.
         */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        try {
            /* Hide the action bar if the version is above Honeycomb.
            DO NOT REMOVE the if statement. App will crash on older
            versions.
             */
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                getActionBar().hide();
            }
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }

        setContentView(R.layout.activity_video_view);

        /*
        get the location of the file which was passed from the last activity.
         */
        String file = getIntent().getStringExtra("FILE");

        MediaController mc = new MediaController(this);
        mc.setAnchorView(video);
        video = (android.widget.VideoView) findViewById(R.id.videoView);
        video.setVideoPath(file);
        // The mediacontroller gives play, pause, forward and back control to video.
        video.setMediaController(mc);
        video.requestFocus();
        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Return to previous actvity when the video finishes.
                finish();
            }
        });

    }
}
