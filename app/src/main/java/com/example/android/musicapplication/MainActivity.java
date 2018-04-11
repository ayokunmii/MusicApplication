package com.example.android.musicapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.musicapplication.AlbumsActivity.ARTIST;
import static com.example.android.musicapplication.AlbumsActivity.PLAYBUTTON;
import static com.example.android.musicapplication.AlbumsActivity.SONG;
import static com.example.android.musicapplication.AlbumsActivity.SONG_ART;
import static com.example.android.musicapplication.AlbumsActivity.SONG_NAME;
import static com.example.android.musicapplication.AlbumsActivity.STATUS;


public class MainActivity extends AppCompatActivity {
    static TextView songName;
    static TextView artistName;
    static ImageView songArt;
    static boolean noSong;
    public static ListView songList;
    static boolean isPlaying;
    static ArrayList<Songs> song;
    static ImageView playbutton;
    static ImageView fastForward;
    static ImageView rewind;
    ImageView playButton;
     static TextView songStatus;
    ImageView AlbumArt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView nameOfSong = (TextView) findViewById(R.id.song_name);
        final TextView nameOfArtist = (TextView) findViewById(R.id.artist_name);
        AlbumArt = (ImageView) findViewById(R.id.song_art);
        songStatus = (TextView) findViewById(R.id.song_status);
        songArt = (ImageView) findViewById(R.id.song_art);
        playButton = (ImageView) findViewById(R.id.play_button);

       Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            Bitmap bmp = (Bitmap) bundle.getParcelable(SONG_ART);

            songArt.setImageBitmap(bmp);
            songArt.setTag(bmp);

        }


        if (songName != null && artistName != null && playbutton.getTag() != null  ){

            nameOfSong.setText(songName.getText());
            nameOfArtist.setText(artistName.getText());
            songStatus.setText(R.string.now_playing);
           playButton.setImageResource((Integer) playbutton.getTag());
        }


        //playButton.setImageResource(R.drawable.play_button);

        isPlaying = false;
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if (!isPlaying && songName != null ) {
                       playButton.setImageResource(R.drawable.pause_button);
                      // playButton.setTag(R.drawable.pause_button);
                       isPlaying = true;
                   } else {
                       playButton.setImageResource(R.drawable.play_button);
                       //playButton.setTag(R.drawable.play_button);
                       isPlaying = false;
                   }




            }
        });


        final ImageView artists = (ImageView) findViewById(R.id.artists_page);
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artistsIntent = new Intent(MainActivity.this, ArtistsActivity.class);
                if (songName != null && artistName != null){

                songArt.buildDrawingCache();
                Bitmap image= songArt.getDrawingCache();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SONG_ART, image);


                artistsIntent.putExtra(SONG_NAME, nameOfSong.getText().toString());
                artistsIntent.putExtra(ARTIST, nameOfArtist.getText().toString());
                artistsIntent.putExtra(STATUS, songStatus.getText().toString());
                artistsIntent.putExtra(PLAYBUTTON, isPlaying);
                artistsIntent.putExtra(SONG_ART, bundle);




                artistsIntent.putExtras(bundle);
                }
                startActivity(artistsIntent);
            }
        });

        final ImageView albums = (ImageView) findViewById(R.id.albums_page);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(MainActivity.this, AlbumsActivity.class);
                if (songName != null && artistName != null){
                    songArt.buildDrawingCache();

                Bitmap image= songArt.getDrawingCache();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SONG_ART, image);
               // bundle.putSerializable(SONG, (Serializable) song);

                Class<? extends ArrayList> songs = song.getClass();
                   // List<Bird> birds = birdModel.getBird();
                    bundle.putParcelableArrayList(SONG, song);



                albumsIntent.putExtra(SONG_NAME, nameOfSong.getText().toString());
                albumsIntent.putExtra(ARTIST, nameOfArtist.getText().toString());
                albumsIntent.putExtra(STATUS, songStatus.getText().toString());
                albumsIntent.putExtra(PLAYBUTTON, isPlaying);
                albumsIntent.putExtra(SONG_ART, bundle);

                albumsIntent.putExtra(SONG,song);



                albumsIntent.putExtras(bundle);
                }
                startActivity(albumsIntent);
            }
        });

        final ImageView songs = (ImageView) findViewById(R.id.songs_page);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(MainActivity.this,SongsActivity.class);
                if (songName != null && artistName != null){
                    songArt.buildDrawingCache();

                Bitmap image= songArt.getDrawingCache();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SONG_ART, image);


                songsIntent.putExtra(SONG_NAME, nameOfSong.getText().toString());
                songsIntent.putExtra(ARTIST, nameOfArtist.getText().toString());
                songsIntent.putExtra(STATUS, songStatus.getText().toString());
                songsIntent.putExtra(PLAYBUTTON, isPlaying);
                songsIntent.putExtra(SONG_ART, bundle);


                songsIntent.putExtras(bundle);
                }
                startActivity(songsIntent);
            }
        });

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





    }


}
