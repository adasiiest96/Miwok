package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
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
        final ArrayList<Word> family = new ArrayList<Word>();

        family.add(new Word("father", "পিতা", R.drawable.family_father, R.raw.family_father));
        family.add(new Word("mother", "মা", R.drawable.family_mother, R.raw.family_mother));
        family.add(new Word("son", "পুত্র", R.drawable.family_son, R.raw.family_son));
        family.add(new Word("daughter", "কন্যা", R.drawable.family_daughter, R.raw.family_daughter));
        family.add(new Word("older brother", "বড় দাদা", R.drawable.family_older_brother, R.raw.family_older_brother));
        family.add(new Word("younger brother", "ছোট ভাই", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        family.add(new Word("older sister", "বড় দিদি", R.drawable.family_older_sister, R.raw.family_older_sister));
        family.add(new Word("younger sister", "ছোট বোন", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        family.add(new Word("grandmother", "দিদা", R.drawable.family_grandmother, R.raw.family_grandmother));
        family.add(new Word("grandfather", "দাদু", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter myAdapter = new WordAdapter(this, family, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast t = Toast.makeText(FamilyActivity.this, "playing", Toast.LENGTH_SHORT);
                t.show();
                releaseMediaPlayer();

                int result = maudiomanager.requestAudioFocus(afChangelistener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mplay = MediaPlayer.create(FamilyActivity.this, family.get(pos).getAudioResourceId());
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
