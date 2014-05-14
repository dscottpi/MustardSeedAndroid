package com.java.app.mustardseed.util;

import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by danscott on 27/02/2014.
 * Class which contains all static constants used within the
 * app
 */
public class Constants {

    // AUDIO constants.

    /**
     * Uri for external Audio Content
     */
    public static final Uri AUDIO_URI =
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    /**
     * Provides the title of the audio file.
     */
    public static final String AUDIO_TITLE =
            MediaStore.Audio.Media.TITLE;

    /**
     * Provides the duration of the audio file in
     * milliseconds.
     */
    public static final String AUDIO_DURATION =
            MediaStore.Audio.Media.DURATION;

    /**
     * Provides the actual audio data.
     */
    public static final String AUDIO_DATA =
            MediaStore.Audio.Media.DATA;

    public static final String[] AUDIO_PROJECTION = {
            AUDIO_DATA, AUDIO_DURATION, AUDIO_TITLE
    };

    // Video constants

    /**
     * Uri for external video content
     */
    public static final Uri VIDEO_URI =
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

    /**
     * Provides title of video file.
     */
    public static final String VIDEO_TITLE =
            MediaStore.Video.Media.TITLE;

    /**
     * Provides duration of video of video file
     * in milliseconds
     */
    public static final String VIDEO_DURATION =
            MediaStore.Video.Media.DURATION;

    /**
     * Provides the actual video data.
     */
    public static final String VIDEO_DATA =
            MediaStore.Video.Media.DATA;

    public static final String[] VIDEO_PROJECTION = {
            VIDEO_DATA, VIDEO_DURATION, VIDEO_TITLE
    };

}
