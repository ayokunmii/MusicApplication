package com.example.android.musicapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;


import java.io.Serializable;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.example.android.musicapplication.MainActivity.artistName;
import static com.example.android.musicapplication.MainActivity.isPlaying;
import static com.example.android.musicapplication.MainActivity.noSong;
import static com.example.android.musicapplication.MainActivity.song;
import static com.example.android.musicapplication.MainActivity.songArt;
import static com.example.android.musicapplication.MainActivity.songList;
import static com.example.android.musicapplication.MainActivity.songName;

/**
 * Created by ayoawotunde on 28/03/2018.
 */

public class Songs implements Parcelable{
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


    protected Songs(Parcel in) {
        mSongName = in.readString();
        mArtist = in.readString();
        mAlbum = in.readString();
        mArt = in.readInt();
    }

    public static final Creator<Songs> CREATOR = new Creator<Songs>() {
        @Override
        public Songs createFromParcel(Parcel in) {
            return new Songs(in);
        }

        @Override
        public Songs[] newArray(int size) {
            return new Songs[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mSongName);
        parcel.writeString(mArtist);
        parcel.writeString(mAlbum);
        parcel.writeInt(mArt);
    }


}


