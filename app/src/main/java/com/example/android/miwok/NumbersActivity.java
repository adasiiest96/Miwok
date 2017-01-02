package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer mplay;

    MediaPlayer.OnCompletionListener myOnCompleteListener =  new MediaPlayer.OnCompletionListener() {
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

        //Create an Word  ArrayList of Eng Words

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "এক",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two", "দুই",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three", "তিন",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four", "চার",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five", "পাঁচ",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six", "ছয়",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven", "সাত",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "আট",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine", "নয়",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "দশ",R.drawable.number_ten,R.raw.number_nine));

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//        for(int index =0 ;index < words.size();index++) {
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(index));
//            rootView.addView(wordView);
//
//        }

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter =
                new WordAdapter(this, words,R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast t =Toast.makeText(NumbersActivity.this,"playing",Toast.LENGTH_SHORT);
                t.show();
                releaseMediaPlayer();
                mplay = MediaPlayer.create(NumbersActivity.this,words.get(pos).getAudioResourceId());
                mplay.start();
                mplay.setOnCompletionListener(myOnCompleteListener);
            }
        });
    }

    /**
     * Clean up the media player by calling release() method on mplay
     */
    private void releaseMediaPlayer(){
        if(mplay != null){
            mplay.release();

            mplay = null;
        }
    }

}
