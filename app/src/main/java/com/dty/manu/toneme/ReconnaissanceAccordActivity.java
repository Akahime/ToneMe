package com.dty.manu.toneme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sarah on 07/06/2017.
 */

public class ReconnaissanceAccordActivity extends ExerciceActivity {
    byte exoCode = 2;
    
    char selectedChordNote = ' ';
    char selectedChordWeight = ' ';

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        /** Set layout **/
        setContentView(R.layout.activity_rec_chord);

        setCurrentScore(exoCode);


        /** New chord **/
        final Chord randChord = ((ExoApplication) this.getApplication()).chordList[rand.nextInt(14)];

        ChordView cView = (ChordView) findViewById(R.id.chordView);
        cView.setNoteList(randChord);


        /** Select color **/
        final int selectColor = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);

        /** Check if selected note is correct
        final boolean keyboardPref = sharedPref.getBoolean("pref_notes_keyboard", true);

        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];

            final RelativeLayout button_note = (RelativeLayout) findViewById(getIdIdentifier(this, "note_" + letter));

            //Hide note label if setting preference disabled
            if(! keyboardPref) {
                button_note.getChildAt(0).setBackgroundColor(Color.WHITE);
            }

            //Listener
            button_note.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Unselect if already selected
                    if(selectedChordNote == getButtonText(v).charAt(0)) {
                        selectedChordNote = ' ';
                        if(! keyboardPref) {
                            button_note.getChildAt(0).setBackgroundColor(Color.WHITE);
                        }
                        else {
                            button_note.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.button));;
                        }
                    }
                    else {
                        selectedChordNote = getButtonText(v).charAt(0);
                        button_note.getChildAt(0).setBackgroundColor(selectColor);
                    }

                    // If they are both selected (answer try)
                    if(selectedChordNote != ' ' && selectedChordWeight != ' ') {

                        // If it is correct
                        if(selectedChordNote == randChord.getNameNote() && selectedChordWeight == randChord.getNameWeight()) {
                            correctEndExo();
                        }
                        else {
                            falseEndExo(randChord);
                        }
                    }
                }
            });
        }

        /** Check if has correct Major / Minor
        final Button button_major = (Button) findViewById(R.id.chord_major);
        button_major.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Unselect if already selected
               if(selectedChordWeight == 'M') {
                   selectedChordNote = ' ';
                   if(! keyboardPref) {
                       button_major.setBackgroundColor(Color.WHITE);
                   }
                   else {
                       button_major.setBackground(getResources().getDrawable(R.drawable.button));;
                   }
               }
               else {
                   selectedChordWeight = 'M';
                   button_major.setBackgroundColor(selectColor);
               }

               // If they are both selected (answer try)
               if(selectedChordNote != ' ' && selectedChordWeight != ' ') {

                   // If it is correct
                   if(selectedChordNote == randChord.getNameNote() && selectedChordWeight == randChord.getNameWeight()) {
                       correctEndExo();
                   }
                   else {
                       falseEndExo(randChord);
                   }
               }
           }
        });

        final Button button_minor = (Button) findViewById(R.id.chord_minor);
        button_minor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Unselect if already selected
                if(selectedChordWeight == 'm') {
                    selectedChordNote = ' ';
                    if(! keyboardPref) {
                        button_minor.setBackgroundColor(Color.WHITE);
                    }
                    else {
                        button_minor.setBackground(getResources().getDrawable(R.drawable.button));;
                    }
                }
                else {
                    selectedChordWeight = 'm';
                    button_minor.setBackgroundColor(selectColor);
                }

                // If they are both selected (answer try)
                if(selectedChordNote != ' ' && selectedChordWeight != ' ') {

                    // If it is correct
                    if(selectedChordNote == randChord.getNameNote() && selectedChordWeight == randChord.getNameWeight()) {
                        correctEndExo();
                    }
                    else {
                        falseEndExo(randChord);
                    }
                }
            }
        });

        /** Skip note
        Button button_skip = (Button) findViewById(R.id.skipButton);
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float scoreResult = (float) 0.5;
                if(scoreResult != -1 ) {
                    //Display finished and score
                    Intent intent = new Intent(ReconnaissanceAccordActivity.this, ReconnaissanceAccordEndActivity.class);
                    startActivity(intent);
                }
                else {
                    /** Delay *
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            /** Go to next
                            finish();
                            startActivity(getIntent());
                        }
                    }, 700);
                }
            }
        });
                **/
    }

    private void correctEndExo() {
        ((ExoApplication) getApplication()).setExoResult(exoCode, 1);

        float scoreResult = checkExoFinished(exoCode);
        if(scoreResult != -1 ) {
            //Display finished and score
            Intent intent = new Intent(ReconnaissanceAccordActivity.this, ReconnaissanceAccordEndActivity.class);
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

    private void falseEndExo(final Chord expectedChord) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean skipPref = sharedPref.getBoolean("pref_skip", true);
        final boolean keyboardPref = sharedPref.getBoolean("pref_notes_keyboard", true);

        ((ExoApplication) getApplication()).setExoResult(exoCode, 0);

        final Button button_weight;
        if(selectedChordWeight == 'M') {
            button_weight = (Button) findViewById(R.id.chord_major);
        }
        else {
            button_weight = (Button) findViewById(R.id.chord_minor);
        }

        /** Delay and put back button **/
        if(skipPref){
            skipNote(exoCode, String.valueOf(expectedChord.getNameNote()));
        }
        else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RelativeLayout button_note = (RelativeLayout) findViewById(getIdIdentifier(getBaseContext(), "note_" + selectedChordNote));
                    button_weight.setBackground(getResources().getDrawable(R.drawable.button));

                    /** Go to next **/
                    if(keyboardPref){
                        button_note.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.button));
                    }
                    else {
                        button_note.getChildAt(0).setBackgroundColor(Color.WHITE);
                    }
                }
            }, 700);
        }
    }
}
