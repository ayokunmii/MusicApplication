package com.example.android.musicapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



import static com.example.android.musicapplication.MainActivity.artistName;
import static com.example.android.musicapplication.MainActivity.fastForward;
import static com.example.android.musicapplication.MainActivity.isPlaying;
import static com.example.android.musicapplication.MainActivity.noSong;
import static com.example.android.musicapplication.MainActivity.playbutton;
//import static com.example.android.musicapplication.MainActivity.song;
import static com.example.android.musicapplication.MainActivity.songArt;
import static com.example.android.musicapplication.MainActivity.songList;
import static com.example.android.musicapplication.MainActivity.songName;
import static com.example.android.musicapplication.MainActivity.songStatus;
import static java.security.AccessController.getContext;


public class AlbumsActivity extends AppCompatActivity  {

    private SongsAdapter itemsAdapter;
    public static final String SONG_NAME = "song name";
    public static final String ARTIST = "artist";
    public static final String SONG_ART = "art"  ;
    public static final String PLAYBUTTON = "button";
    public static final String STATUS = "status";
    public static final String SONG = "array";

    //TextView status;

    ArrayList<Songs> song;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        ImageView albums = (ImageView) findViewById(R.id.albums_page);
        albums.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint

        songName = (TextView) findViewById(R.id.song_name);
        artistName = (TextView) findViewById(R.id.artist_name);
        songArt = (ImageView) findViewById(R.id.song_art);
        songStatus = (TextView) findViewById(R.id.status);
        fastForward = (ImageView) findViewById(R.id.fast_forward_button);
        song=MainActivity.song;


       playbutton = (ImageView) findViewById(R.id.play_button);
        //playbutton.setImageResource((Integer) playbutton.getTag());
        //ImageView rewind = (ImageView) findViewById(R.id.rewind_button);
        final ImageView[] buttons = {findViewById(R.id.play_button), findViewById(R.id.rewind_button), findViewById(R.id.fast_forward_button)};
        // to ensure player bar is inactive if theres no song playing
        noSong = true;



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //Bundle bundle1 = intent.getBundleExtra(SONG);


        if (bundle != null){
            noSong = false;
            String s = bundle.getString(SONG_NAME);
            String a = bundle.getString(ARTIST);
            String b = bundle.getString(STATUS);
            Bitmap bmp = (Bitmap) bundle.getParcelable(SONG_ART);
            //song = bundle.getParcelableArrayList(SONG);
            song = this.getIntent().getExtras().getParcelableArrayList(SONG);


            //song = (ArrayList<Songs>) bundle1.getSerializable(SONG);


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
                    isPlaying = true;
                    for (int i = 0; i<buttons.length; i++){
                playbutton.setImageResource(R.drawable.pause_button);
                playbutton.setTag(R.drawable.pause_button);
                buttons[i].setColorFilter(Color.argb(255, 255, 255, 255));  // White Tint
                         songName.setTextColor(getResources().getColor(R.color.white));
                    }

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






Collections.sort(song, new Comparator<Songs>() {
    @Override
    public int compare(Songs songs, Songs t1) {

        return songs.getmAlbum().compareTo(t1.getmAlbum());


    }


});




       itemsAdapter = new SongsAdapter(this, song);
       songList = (ListView) findViewById(R.id.songs_list);
        songList.setAdapter(itemsAdapter);

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                songName.setText(String.valueOf(song.get(i).getmSongName()));
                artistName.setText(String.valueOf(song.get(i).getmArtist()));
                songArt.setImageResource(song.get(i).getmArt());
                songArt.setTag(song.get(i).getmArt());
                playbutton.setImageResource(R.drawable.pause_button);
                playbutton.setTag(R.drawable.pause_button);
                songStatus.setText(R.string.now_playing);


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
                Toast.makeText(AlbumsActivity.this, "position " + position, Toast.LENGTH_SHORT);
                if (!noSong){

                    for (int k = position; k < song.size() ; k = position +1){
                        Toast.makeText(AlbumsActivity.this, "size " + song.size() + " value of k" + k, Toast.LENGTH_SHORT);
                        songName.setText(String.valueOf(song.get(k).getmSongName()));
                        artistName.setText(String.valueOf(song.get(k).getmArtist()));
                        songArt.setImageResource(song.get(k).getmArt());
                        songArt.setTag(song.get(k).getmArt());
                    }
                }
            }
        });

        View playerBar = findViewById(R.id.player_bar);
        playerBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent homePage = new Intent(AlbumsActivity.this, MainActivity.class);
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

                Intent artistsIntent = new Intent(AlbumsActivity.this, ArtistsActivity.class);

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


        final ImageView songs = (ImageView) findViewById(R.id.songs_page);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(AlbumsActivity.this,SongsActivity.class);
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
            }
        });


    }


}
