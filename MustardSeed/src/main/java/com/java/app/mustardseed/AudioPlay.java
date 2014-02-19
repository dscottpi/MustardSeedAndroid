package com.java.app.mustardseed;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by danscott on 10/02/2014.
 * Activity which plays the audio file
 * selected in the previous activity.
 */
public class AudioPlay extends Activity {

    MediaPlayer mediaPlayer;
    String file;
    String trackTitle;
    SeekBar seekBar;
    Button playToggle;
    Button forward;
    Button back;
    TextView title;
    TextView time;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);

        /*
        Get the location of the audio file which was passed from the
        previous activity.
         */
        file = getIntent().getStringExtra("FILE");
        trackTitle = getIntent().getStringExtra("TITLE");

        handler = new Handler();

        if(mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        /*
        Finish the activity when the mediaplayer finishes playing the
        audio file.
         */
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
            }
        });

        try {
            mediaPlayer.setDataSource(file);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        playToggle = (Button)findViewById(R.id.play_pause);
        forward = (Button)findViewById(R.id.forward);
        back = (Button)findViewById(R.id.back);
        title = (TextView)findViewById(R.id.title);
        time = (TextView)findViewById(R.id.info);
        title.setText(trackTitle);

    }

    @Override
    protected void onResume() {
        super.onResume();
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(onSeekBarChange);
        /**
         Refreshes position of seekbar every second to
         keep up with the song.
         @see private void playing(){}
         */
        playing();

        playToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playToggle.setText(">");
                    return;
                }

                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    playToggle.setText("||");
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 7000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    /*
        This sets the position of the seekbar relevant
        to the time elapsed in the audio file.
        Refreshes every second.
     */
    private void playing() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int position = mediaPlayer.getCurrentPosition();
                final int minute = ((position / (1000 * 60)) % 60);
                final int seconds = (position / 1000) % 60;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (seconds < 10) {
                            time.setText(minute + ":0" + seconds);
                        } else {
                            time.setText(minute + ":" + seconds);
                        }
                    }
                });
                seekBar.setProgress(position);
                playing();
            }


        }, 1000);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChange =
            new SeekBar.OnSeekBarChangeListener() {

        /*
        Move the position of the audio file if the user drags
        the seekbar.
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int position, boolean fromUser) {

            if(fromUser) {
                mediaPlayer.seekTo(position);
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

}
