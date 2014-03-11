package com.java.app.mustardseed.audio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.java.app.mustardseed.R;

import java.io.IOException;

/**
 * Created by danscott on 10/02/2014.
 * Activity which plays the audio file
 * selected from the list in the parent activity AudioHome.
 *
 * User can play/pause audio file, skip forward, jump back and
 * go to a specific part of the audio file by using the SeekBar provided.
 *
 */
public class AudioPlay extends Activity {

    private MediaPlayer mediaPlayer;
    private String file;
    private String trackTitle;
    private SeekBar seekBar;
    private ImageButton play;
    private ImageButton pause;
    private ImageButton forward;
    private ImageButton rewind;
    private TextView title;
    private TextView time;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);

        /*
        Get the location of the audio file and the title which was passed from the
        previous activity.
         */
        file = getIntent().getStringExtra("FILE");
        trackTitle = getIntent().getStringExtra("TITLE");

        handler = new Handler();

        /**
         * Create new MediaPlayer
         */
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
        play = (ImageButton)findViewById(R.id.imageButton4);
        pause = (ImageButton)findViewById(R.id.imageButton2);
        forward = (ImageButton)findViewById(R.id.imageButton3);
        rewind = (ImageButton)findViewById(R.id.imageButton);
        title = (TextView)findViewById(R.id.title);
        time = (TextView)findViewById(R.id.info);
        title.setText(trackTitle);

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        Set the max size of the seekbar to the length of the audio file.
         */
        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(onSeekBarChange);

        /**
         Refreshes position of seekbar every second to
         keep up with the song.
         @see private void playing(){}
         */
        playing();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    play.setVisibility(View.INVISIBLE);
                    pause.setVisibility(View.VISIBLE);
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    pause.setVisibility(View.INVISIBLE);
                    play.setVisibility(View.VISIBLE);
                }
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 7000);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 7000);
            }
        });

    }

    /*
    When the activity is destroyed, either by the user pressing the back button
    or when the Android OS kills it, stop the mediaplayer and release it. Also, calling
    handler.removeCallbacksAndMessages kills the handler.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    /*
        This sets the position of the seekbar relevant
        to the time elapsed in the audio file and displays the time
        elapsed in minutes and seconds in the time TextView.
        Refreshes every second.
     */
    private void playing() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                Get current position of audio file and convert result to minutes and
                seconds
                 */
                int position = mediaPlayer.getCurrentPosition();
                final int minute = ((position / (1000 * 60)) % 60);
                final int seconds = (position / 1000) % 60;

                /*
                Because this isn't running on the main thread (i.e. ui thread) to change any of the
                UI you have to use this method runOnUiThread().
                 */
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
                // Move the seekbar to the position of the audio file.
                seekBar.setProgress(position);
                // Recall method to keep it looping.
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
