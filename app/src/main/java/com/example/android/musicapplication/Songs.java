package com.example.android.musicapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;


import static android.support.v4.content.ContextCompat.startActivity;


/**
 * Created by ayoawotunde on 28/03/2018.
 */

public class Songs {
    private String mSongName;
    private String mArtist;
    private String mAlbum;
    private int mArt;


    public Songs (String songName,  String album, String artist, int art){
        mSongName = songName;
        mArtist = artist;
        mAlbum = album;
        mArt = art;
    }


    public String getmSongName() {
        return mSongName;
    }

    public String getmArtist() {
        return mArtist;
    }

    public String getmAlbum() {
        return mAlbum;
    }

    public int getmArt() {
        return mArt;
    }


}


