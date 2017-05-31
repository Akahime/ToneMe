package com.dty.manu.toneme;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 03/05/2017.
 */

public class ReconnaissanceAuditiveActivity extends ExerciceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_auditive);

        ImageButton button_replay = (ImageButton) findViewById(R.id.replayButton);
        button_replay.setImageResource(R.drawable.icon_sound);

        /** Play note **/
        final String randNote = randLetter();
        List<String> tonesArray = new ArrayList<String>();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean tonePrefLow = sharedPref.getBoolean("pref_tones_low", true);
        final boolean tonePrefMed = sharedPref.getBoolean("pref_tones_medium", true);
        final boolean tonePrefHigh = sharedPref.getBoolean("pref_tones_high", true);

        if(tonePrefLow) {
            tonesArray.add("1") ;
            tonesArray.add("2") ;
        }
        if(tonePrefMed) {
            tonesArray.add("3");
        }
        final MediaPlayer mp = MediaPlayer.create(this, getRawIdentifier(this, randNote + "_" + tonesArray.get(rand.nextInt(tonesArray.size()))));
        mp.start();

        /** Get exo code **/
        final String exoName = ((ExoApplication) this.getApplication()).EXO_REC_AUDITIVE;

        /** Check if selected note is correct **/
        setClickNoteListener(exoName,randNote);

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
                skip(exoName,randNote);
            }
        });
    }

}