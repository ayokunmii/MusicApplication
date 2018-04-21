package com.example.android.musicapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ayoawotunde on 11/04/2018.
 */

public class SharedVariables extends AppCompatActivity {
    public static final String SONG_NAME = "song name";
    public static final String ARTIST = "artist";
    public static final String SONG_ART = "art"  ;
    public static final String PLAYBUTTON = "button";
    public static final String STATUS = "status";
    public static final String SONG = "array";
    public static final String POSITION = "position";

    static TextView songName;
    static TextView artistName;
    static ImageView songArt;
    static boolean noSong;
    public static ListView songList;
    static boolean isPlaying;
    static ImageView playbutton;
    static ImageView fastForward;
    static ImageView rewind;
    static TextView songStatus;
    static int PictureSource;


}
