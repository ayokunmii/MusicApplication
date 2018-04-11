package com.example.android.musicapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ayoawotunde on 28/03/2018.
 */

public class SongsAdapter extends ArrayAdapter<Songs> {

public SongsAdapter (Context context, ArrayList<Songs>song){
    super(context, 0, song);
}

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }



        //in.putExtra("songIndex", songIndex);

        Songs song_name = getItem(position);
        TextView songName = (TextView) listItemView.findViewById(R.id.song);
        songName.setText(song_name.getmSongName());

        Songs artist_name = getItem(position);
        TextView artistName = (TextView) listItemView.findViewById(R.id.artist);
        artistName.setText(artist_name.getmArtist());

        Songs album_name = getItem(position);
        TextView albumName = (TextView) listItemView.findViewById(R.id.album);
        albumName.setText(album_name.getmAlbum());

        Songs art_name = getItem(position);
        ImageView artName = (ImageView) listItemView.findViewById(R.id.song_icon);
        artName.setImageResource(art_name.getmArt());

        return listItemView;


    }


}

