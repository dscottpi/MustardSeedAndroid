package com.java.app.mustardseed;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;



/**
 * Created by danscott on 09/02/2014.
 */
public class VideoView extends Activity {

    private android.widget.VideoView video;

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

        setContentView(R.layout.activity_video_view);

        String file = getIntent().getStringExtra("FILE");

        MediaController mc = new MediaController(this);
        mc.setAnchorView(video);
        video = (android.widget.VideoView) findViewById(R.id.videoView);
        video.setVideoPath(file);
        video.setMediaController(mc);
        video.requestFocus();
        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
            }
        });

    }
}
