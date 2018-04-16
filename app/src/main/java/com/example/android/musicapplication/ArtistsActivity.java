package com.example.android.musicapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import static com.example.android.musicapplication.SharedVariables.ARTIST;
import static com.example.android.musicapplication.SharedVariables.PLAYBUTTON;

import static com.example.android.musicapplication.SharedVariables.SONG_ART;
import static com.example.android.musicapplication.SharedVariables.SONG_NAME;
import static com.example.android.musicapplication.SharedVariables.STATUS;
import static com.example.android.musicapplication.SharedVariables.SONG;
import static com.example.android.musicapplication.SharedVariables.fastForward;
import static com.example.android.musicapplication.SharedVariables.isPlaying;
import static com.example.android.musicapplication.SharedVariables.playbutton;
import static com.example.android.musicapplication.SharedVariables.rewind;
import static com.example.android.musicapplication.SharedVariables.songArt;
import static com.example.android.musicapplication.SharedVariables.songName;
import static com.example.android.musicapplication.SharedVariables.artistName;
import static com.example.android.musicapplication.SharedVariables.songStatus;
import static com.example.android.musicapplication.SharedVariables.noSong;
import static com.example.android.musicapplication.SharedVariables.songList;


public class ArtistsActivity extends AppCompatActivity {
    //Initializing variables
    ArrayList<Songs> song;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        //setting page icon to change colour to indicate it's the current page
        ImageView artists = (ImageView) findViewById(R.id.artists_page);
        artists.setColorFilter(Color.argb(255, 255, 255, 255));

        //declaring variables
        songName = (TextView) findViewById(R.id.song_name);
        artistName = (TextView) findViewById(R.id.artist_name);
        songArt = (ImageView) findViewById(R.id.song_art);
        playbutton = (ImageView) findViewById(R.id.play_button);
        songStatus = (TextView) findViewById(R.id.status);
        fastForward = (ImageView) findViewById(R.id.fast_forward_button);
        rewind = (ImageView) findViewById(R.id.rewind_button);




        //creating an array for the buttons since they have to do certain things together
        final ImageView[] buttons = {findViewById(R.id.play_button), findViewById(R.id.rewind_button),
                findViewById(R.id.fast_forward_button)};

        // to ensure player bar is inactive if theres no song playing
        noSong = true;


        //populating arraylist with our songs!
        song = new ArrayList<Songs>();

        song.add(new Songs(getString(R.string.artist1_song1), getString(R.string.artist1_album_1_2),
                getString(R.string.artist1) , R.drawable.anti_album));
        song.add(new Songs(getString(R.string.artist1_song2), getString(R.string.artist1_album_1_2),
                getString(R.string.artist1), R.drawable.anti_album));

        song.add(new Songs(getString(R.string.artist1_song3), getString(R.string.artist1_album_3),
                getString(R.string.artist1), R.drawable.rated_r_album));

        song.add(new Songs(getString(R.string.artist1_song4), getString(R.string.artist1_album_4),
                getString(R.string.artist1), R.drawable.talk_that_talk_album));

        song.add(new Songs(getString(R.string.artist2_song1), getString(R.string.artist2_album_1_4),
                getString(R.string.artist2), R.drawable.eight_ounces_album));
        song.add(new Songs(getString(R.string.artist2_song4), getString(R.string.artist2_album_1_4),
                getString(R.string.artist2), R.drawable.eight_ounces_album));

        song.add(new Songs(getString(R.string.artist2_song2), getString(R.string.artist2_album_2_3),
                getString(R.string.artist2), R.drawable.emotionally_unavail_album));
        song.add(new Songs(getString(R.string.artist2_song3), getString(R.string.artist2_album_2_3),
                getString(R.string.artist2), R.drawable.emotionally_unavail_album));

        song.add(new Songs(getString(R.string.artist3_song1),getString(R.string.artist3_album_1_2),
                getString(R.string.artist3), R.drawable.four_album));
        song.add(new Songs(getString(R.string.artist3_song2),getString(R.string.artist3_album_1_2),
                getString(R.string.artist3), R.drawable.four_album));

        song.add(new Songs(getString(R.string.artist3_song3),getString(R.string.artist3_album_3),
                getString(R.string.artist3), R.drawable.b_day_album));

        song.add(new Songs(getString(R.string.artist3_song4),getString(R.string.artist3_album_4),
                getString(R.string.artist3), R.drawable.beyonce_album));

        song.add(new Songs(getString(R.string.artist4_song1), getString(R.string.artist4_album_1),
                getString(R.string.artist4), R.drawable.r_e_d_album));

        song.add(new Songs(getString(R.string.artist4_song2), getString(R.string.artist4_album_2),
                getString(R.string.artist4), R.drawable.sugarcane_album));

        song.add(new Songs(getString(R.string.artist4_song3), getString(R.string.artist4_album_3_4),
                getString(R.string.artist4), R.drawable.once_upon_a_time_album));
        song.add(new Songs(getString(R.string.artist4_song4), getString(R.string.artist4_album_3_4),
                getString(R.string.artist4), R.drawable.once_upon_a_time_album));

        song.add(new Songs(getString(R.string.artist5_song1),getString(R.string.artist5_album_1_3),
                getString(R.string.artist5), R.drawable.sail_out_album));
        song.add(new Songs(getString(R.string.artist5_song3),getString(R.string.artist5_album_1_3),
                getString(R.string.artist5), R.drawable.sail_out_album));

        song.add(new Songs(getString(R.string.artist5_song2),getString(R.string.artist5_album_2),
                getString(R.string.artist5), R.drawable.trip_album));

        song.add(new Songs(getString(R.string.artist5_song4),getString(R.string.artist5_album_4),
                getString(R.string.artist5), R.drawable.maniac_album));

        //method used to sort out songs based on the current page in this case - artists
        //Would it be possible to do this twice? So first based on the current page then in alphabetical order??
        //I HAVE NO CLUEEEEE

        Collections.sort(song, new Comparator<Songs>() {
            @Override
            public int compare(Songs songs, Songs t1) {

                return songs.getmArtist().compareTo(t1.getmArtist());

            }

        });

        //using customAdapter as our listView contains more than 1 TextView :
        SongsAdapter itemsAdapter = new SongsAdapter(this, song);
        songList = (ListView) findViewById(R.id.songs_list);
        songList.setAdapter(itemsAdapter);


        //getting intent from prev. activity
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();


            if (bundle != null){
                //setting this to false to make playerBar active
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


// setting player bar buttons to respond to changes within the activity
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
                    // setting player bar buttons to respond to changes within the activity
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
        });






        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                //setting variables to reflect the item that was clicked from the listview
                songName.setText(String.valueOf(song.get(i).getmSongName()));
                artistName.setText(String.valueOf(song.get(i).getmArtist()));
                songArt.setImageResource(song.get(i).getmArt());
                songArt.setTag(song.get(i).getmArt());
                playbutton.setImageResource(R.drawable.pause_button);
                playbutton.setTag(R.drawable.pause_button);
                songStatus.setText(R.string.now_playing);

                // setting player bar buttons to respond to changes within the activity
                for (int j = 0; j<buttons.length; j++){
                    isPlaying = true;
                    noSong = false;
                    buttons[j].setColorFilter(Color.argb(255, 255, 255, 255));  // White Tint
                    songName.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        fastForward.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if (position < (song.size()-1) && songName != null && artistName != null &&
                        playbutton.getTag() != null &&  !noSong) {
                    position ++;
                    Log.i("AlbumsActivity", "This is our position: " + position + " isplaying " + isPlaying
                            +" noSong " +noSong + " songNme " +songName);
                    songName.setText(String.valueOf(song.get(position).getmSongName()));
                    artistName.setText(String.valueOf(song.get(position).getmArtist()));
                    songArt.setImageResource(song.get(position).getmArt());
                    songArt.setTag(song.get(position).getmArt());

                }
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0 && songName != null && artistName != null &&
                        playbutton.getTag() != null &&  !noSong){
                    position --;
                    Toast.makeText(ArtistsActivity.this, "position " + position, Toast.LENGTH_SHORT);
                    songName.setText(String.valueOf(song.get(position).getmSongName()));
                    artistName.setText(String.valueOf(song.get(position).getmArtist()));
                    songArt.setImageResource(song.get(position).getmArt());
                    songArt.setTag(song.get(position).getmArt());
                }
            }
        });



        //INTENTS

final View playerBar = findViewById(R.id.player_bar);
playerBar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent homePage = new Intent(ArtistsActivity.this, MainActivity.class);

        songArt.buildDrawingCache();
        Bitmap image= songArt.getDrawingCache();
        Bundle bundle = new Bundle();
        bundle.putParcelable(SONG_ART, image);

        homePage.putExtra(SONG_ART,bundle);

        homePage.putExtras(bundle);

        startActivity(homePage);

        Toast.makeText(ArtistsActivity.this, "Song Name & artist's is " + songName.getText().toString() +
                artistName.getText().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
});
        final ImageView albums = (ImageView) findViewById(R.id.albums_page);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(ArtistsActivity.this, AlbumsActivity.class);
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
                finish();
            }
        });

        final ImageView songs = (ImageView) findViewById(R.id.songs_page);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(ArtistsActivity.this,SongsActivity.class);
                songArt.buildDrawingCache();
                Bitmap image= songArt.getDrawingCache();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SONG_ART, image);


                songsIntent.putExtra(SONG_NAME, songName.getText().toString());
                songsIntent.putExtra(ARTIST, artistName.getText().toString());
                songsIntent.putExtra(STATUS, songStatus.getText().toString());
                songsIntent.putExtra(PLAYBUTTON, isPlaying);
                songsIntent.putExtra(SONG_ART, bundle);


                songsIntent.putExtras(bundle);
                startActivity(songsIntent);
                finish();
            }
        });



    }



}

