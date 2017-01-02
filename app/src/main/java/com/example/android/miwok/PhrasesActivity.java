package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer mplay;

    MediaPlayer.OnCompletionListener myOnCompleteListener =  new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an ArrayList of words object and fit the colors
        final ArrayList<Word> phrases = new ArrayList<Word>();

        phrases.add(new Word("Where are you going?", "আপনি কোথায় যাচ্ছেন?",R.raw.phrase_where_are_you_going));
        phrases.add(new Word("What is your name?", "আপনার নাম কি?",R.raw.phrase_what_is_your_name));
        phrases.add(new Word("My name is...", "আমার নাম...",R.raw.phrase_my_name_is));
        phrases.add(new Word("How are you feeling?", "আপনি কি ভালো আছেন?",R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("I’m feeling good.", "আমি ভালো আছি",R.raw.phrase_im_feeling_good));
        phrases.add(new Word("Are you coming?", "তুমি কি আসছ?",R.raw.phrase_are_you_coming));
        phrases.add(new Word("Yes, I’m coming.", "হ্যাঁ, আমি আসছি",R.raw.phrase_yes_im_coming));
        phrases.add(new Word("Let’s go.", "চলো যাই",R.raw.phrase_lets_go));
        phrases.add(new Word("Come here.", "এখানে এসো",R.raw.phrase_come_here));
        phrases.add(new Word("I eat rice.", "আমি ভাত খাই",R.raw.phrase_come_here));

        WordAdapter myAdapter = new WordAdapter(this, phrases,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast t =Toast.makeText(PhrasesActivity.this,"playing",Toast.LENGTH_SHORT);
                t.show();
                releaseMediaPlayer();
                mplay = MediaPlayer.create(PhrasesActivity.this,phrases.get(pos).getAudioResourceId());
                mplay.start();
                mplay.setOnCompletionListener(myOnCompleteListener);
            }
        });

    }

    private void releaseMediaPlayer(){
        if(mplay != null){
            mplay.release();

            mplay = null;
        }
    }
}
