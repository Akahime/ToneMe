package com.dty.manu.toneme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 03/05/2017.
 */

public class ReconnaissanceAuditiveActivity extends ExerciceActivity {
    MediaPlayer mp = new MediaPlayer();
    final byte exoCode = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_auditive);

        ImageButton button_replay = (ImageButton) findViewById(R.id.replayButton);
        button_replay.setImageResource(R.drawable.icon_sound);

        setCurrentScore(exoCode);

        /** Select note to play **/
        final String randNote = randLetter();
        List<String> tonesArray = new ArrayList<String>();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean tonePrefLow = sharedPref.getBoolean("pref_tones_low", true);
        final boolean tonePrefMed = sharedPref.getBoolean("pref_tones_medium", true);
        final boolean tonePrefHigh = sharedPref.getBoolean("pref_tones_high", true);

        if(tonePrefLow) {
            tonesArray.add("1");
            tonesArray.add("2");
        }
        if(tonePrefMed) {
            tonesArray.add("3");
            tonesArray.add("4");
            tonesArray.add("5");
        }
        if(tonePrefHigh) {
            tonesArray.add("6");
            tonesArray.add("7");
        }

        /** Play sound **/
        stop();
        mp = MediaPlayer.create(this, getRawIdentifier(this, randNote + "_" + tonesArray.get(rand.nextInt(tonesArray.size()))));
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }
        });
        mp.start();


        /** Check if selected note is correct **/
        final boolean keyboardPref = sharedPref.getBoolean("pref_notes_keyboard", true);

        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];

            RelativeLayout button_note = (RelativeLayout) findViewById(getIdIdentifier(this, "note_" + letter));

            //Hide note label if setting preference disabled
            if(! keyboardPref) {
                button_note.getChildAt(0).setBackgroundColor(Color.WHITE);
            }

            //Listener
            button_note.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float scoreResult = getButtonScore(exoCode,randNote, v);
                    if(scoreResult > -2) {
                        if(scoreResult != -1 ) {
                            //Display finished and score
                            Intent intent = new Intent(ReconnaissanceAuditiveActivity.this, ReconnaissanceAuditiveEndActivity.class);
                            startActivity(intent);
                        }
                        else {
                            /** Delay **/
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /** Go to next **/
                                    finish();
                                    startActivity(getIntent());
                                }
                            }, 700);
                        }
                    }
                }
            });
        }

        /** Replay note **/
        button_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        /** Skip note **/
        Button button_skip = (Button) findViewById(R.id.skipButton);
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float scoreResult = skip(exoCode,randNote);
                if(scoreResult != -1 ) {
                    //Display finished and score
                    Intent intent = new Intent(ReconnaissanceAuditiveActivity.this, ReconnaissanceAuditiveEndActivity.class);
                    startActivity(intent);
                }
                else {
                    /** Delay **/
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            /** Go to next **/
                            finish();
                            startActivity(getIntent());
                        }
                    }, 700);
                }
            }
        });
    }

    public void stop() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
}