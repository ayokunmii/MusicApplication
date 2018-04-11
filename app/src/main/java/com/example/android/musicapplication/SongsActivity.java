package com.example.android.musicapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;

import static com.example.android.musicapplication.AlbumsActivity.ARTIST;
import static com.example.android.musicapplication.AlbumsActivity.PLAYBUTTON;
import static com.example.android.musicapplication.AlbumsActivity.SONG_ART;
import static com.example.android.musicapplication.AlbumsActivity.SONG_NAME;
import static com.example.android.musicapplication.AlbumsActivity.STATUS;
import static com.example.android.musicapplication.MainActivity.artistName;
import static com.example.android.musicapplication.MainActivity.isPlaying;
import static com.example.android.musicapplication.MainActivity.noSong;
import static com.example.android.musicapplication.MainActivity.playbutton;
import static com.example.android.musicapplication.MainActivity.song;
import static com.example.android.musicapplication.MainActivity.songArt;
import static com.example.android.musicapplication.MainActivity.songList;
import static com.example.android.musicapplication.MainActivity.songName;
import static com.example.android.musicapplication.MainActivity.songStatus;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        ImageView songs = (ImageView) findViewById(R.id.songs_page);
                songs.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint

        //Class<player> playerClass = player.class;
        //playerClass.getMethods();

        songName = (TextView) findViewById(R.id.song_name);
        artistName = (TextView) findViewById(R.id.artist_name);
        songArt = (ImageView) findViewById(R.id.song_art);
        songStatus = (TextView) findViewById(R.id.status);
        playbutton = (ImageView) findViewById(R.id.play_button);

        //playbutton.setImageResource((Integer) playbutton.getTag());
        //ImageView rewind = (ImageView) findViewById(R.id.rewind_button);
        final ImageView[] buttons = {findViewById(R.id.play_button), findViewById(R.id.rewind_button), findViewById(R.id.fast_forward_button)};
        // to ensure player bar is inactive if theres no song playing
        noSong = true;



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null){
            noSong = false;
            String s = bundle.getString(SONG_NAME);
            String a = bundle.getString(ARTIST);
            String b = bundle.getString(STATUS);
            Bitmap bmp = (Bitmap) bundle.getParcelable(SONG_ART);

            songName.setText(String.valueOf(s));
            artistName.setText(String.valueOf(a));
            songStatus.setText(String.valueOf(b));
            songArt.setImageBitmap(bmp);
            songArt.setTag(bmp);




            if (isPlaying && !noSong) {
                for (int i = 0; i<buttons.length; i++){
                    playbutton.setImageResource(R.drawable.pause_button);
                    playbutton.setTag(R.drawable.pause_button);
                    buttons[i].setColorFilter(Color.argb(255, 255, 255, 255));  // White Tint
                    songName.setTextColor(getResources().getColor(R.color.white));
                }

                isPlaying = true;
            } else {
                for (int i = 0; i<buttons.length; i++){
                    playbutton.setImageResource(R.drawable.play_button);
                    playbutton.setTag(R.drawable.play_button);
                    //   playbutton.setTag(R.drawable.play_button);
                    buttons[i].clearColorFilter();
                    songName.setTextColor(getResources().getColor(R.color.black)); }
                isPlaying = false;
            }
        }


        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlaying && !noSong) {
                    noSong = false;
                    for (int i = 0; i<buttons.length; i++){
                        playbutton.setImageResource(R.drawable.pause_button);
                        playbutton.setTag(R.drawable.pause_button);
                        buttons[i].setColorFilter(Color.argb(255, 255, 255, 255));  // White Tint
                        songName.setTextColor(getResources().getColor(R.color.white));
                    }

                    isPlaying = true;
                } else {
                    for (int i = 0; i<buttons.length; i++){
                        playbutton.setImageResource(R.drawable.play_button);
                        playbutton.setTag(R.drawable.play_button);
                        buttons[i].clearColorFilter();
                        songName.setTextColor(getResources().getColor(R.color.black)); }
                    isPlaying = false;
                }




            }
        });

        SongsAdapter  itemsAdapter = new SongsAdapter(this, song);
        songList = (ListView) findViewById(R.id.songs_list);
        songList.setAdapter(itemsAdapter);

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                songName.setText(String.valueOf(song.get(i).getmSongName()));
                artistName.setText(String.valueOf(song.get(i).getmArtist()));
                songArt.setImageResource(song.get(i).getmArt());
                songArt.setTag(song.get(i).getmArt());
                playbutton.setImageResource(R.drawable.pause_button);
                playbutton.setTag(R.drawable.pause_button);
                TextView status = (TextView) findViewById(R.id.status);
                status.setText(R.string.now_playing);

                for (int j = 0; j<buttons.length; j++){
                    isPlaying = true;
                    noSong = false;
                    buttons[j].setColorFilter(Color.argb(255, 255, 255, 255));  // White Tint
                    songName.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });


        Collections.sort(song, new Comparator<Songs>() {
            @Override
            public int compare(Songs songs, Songs t1) {

                return songs.getmSongName().compareTo(t1.getmSongName());

            }

        });


        View playerBar = findViewById(R.id.player_bar);
        playerBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePage = new Intent(SongsActivity.this, MainActivity.class);
                songArt.buildDrawingCache();
                Bitmap image= songArt.getDrawingCache();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SONG_ART, image);

                homePage.putExtra(SONG_ART,bundle);

                homePage.putExtras(bundle);
                startActivity(homePage);
            }
        });

        final ImageView artists = (ImageView) findViewById(R.id.artists_page);
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artistsIntent = new Intent(SongsActivity.this, ArtistsActivity.class);
                songArt.buildDrawingCache();
                Bitmap image= songArt.getDrawingCache();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SONG_ART, image);


                artistsIntent.putExtra(SONG_NAME, songName.getText().toString());
                artistsIntent.putExtra(ARTIST, artistName.getText().toString());
                artistsIntent.putExtra(STATUS, songStatus.getText().toString());
                artistsIntent.putExtra(PLAYBUTTON, isPlaying);
                artistsIntent.putExtra(SONG_ART, bundle);


                artistsIntent.putExtras(bundle);

                startActivity(artistsIntent);

            }
        });

        final ImageView albums = (ImageView) findViewById(R.id.albums_page);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(SongsActivity.this, AlbumsActivity.class);
                songArt.buildDrawingCache();
                Bitmap image= songArt.getDrawingCache();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SONG_ART, image);


                albumsIntent.putExtra(SONG_NAME, songName.getText().toString());
                albumsIntent.putExtra(ARTIST, artistName.getText().toString());
                albumsIntent.putExtra(STATUS, songStatus.getText().toString());
                albumsIntent.putExtra(PLAYBUTTON, isPlaying);
                albumsIntent.putExtra(SONG_ART, bundle);


                albumsIntent.putExtras(bundle);
                startActivity(albumsIntent);
            }
        });


    }
}
