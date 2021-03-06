package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {
    MediaPlayer mplay;
    /*
    // Creting a audiomanager to register audio focus
     */
    Context mContext;
    AudioManager maudiomanager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

    AudioManager.OnAudioFocusChangeListener afChangelistener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mplay.pause();
                mplay.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mplay.stop();
                releaseMediaPlayer();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mplay.pause();
                mplay.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mplay.start();
            }
        }
    };

    MediaPlayer.OnCompletionListener myOnCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    /**
     * Calling to onStop method to clear the audio resourcce
     */
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an ArrayList of words object and fit the colors
        final ArrayList<Word> colors = new ArrayList<Word>();

        colors.add(new Word("red", "লাল", R.drawable.color_red, R.raw.color_red));
        colors.add(new Word("green", "সবুজ", R.drawable.color_green, R.raw.color_gray));
        colors.add(new Word("blue", "নীল", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        colors.add(new Word("black", "কালো", R.drawable.color_black, R.raw.color_black));
        colors.add(new Word("brown", "বাদামী", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Word("white", "সাদা", R.drawable.color_white, R.raw.color_white));
        colors.add(new Word("gray", "ধূসর", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new Word("violet", "বেগুনী", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        colors.add(new Word("indigo", "বেগনি নীলবর্ণ", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        colors.add(new Word("orange", "কমলা", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter myAdapter = new WordAdapter(this, colors, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast t = Toast.makeText(ColorActivity.this, "playing", Toast.LENGTH_SHORT);
                t.show();
                releaseMediaPlayer();

                int result = maudiomanager.requestAudioFocus(afChangelistener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mplay = MediaPlayer.create(ColorActivity.this, colors.get(pos).getAudioResourceId());
                    mplay.start();
                    mplay.setOnCompletionListener(myOnCompleteListener);
                }
            }
        });

    }

    private void releaseMediaPlayer() {
        if (mplay != null) {
            mplay.release();

            mplay = null;
        }
        maudiomanager.abandonAudioFocus(afChangelistener);
    }

}
