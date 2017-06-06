package com.dty.manu.toneme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sarah on 22/05/2017.
 */

public class ReconnaissanceNoteActivity extends ExerciceActivity {
    //implements SurfaceHolder.Callback
    byte exoCode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_note);

        setCurrentScore(exoCode);

        /** Choose random key **/
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean prefKeySol = sharedPref.getBoolean("pref_key_sol", true);
        final boolean prefKeyFa = sharedPref.getBoolean("pref_key_fa", true);
        int key;
        if(prefKeySol && prefKeyFa || (!prefKeyFa && !prefKeySol)) {
            key = (int) (Math.random() * 2);
        }
        else {
            if(prefKeyFa) {
                key = 1;
            }
            else {
                key = 0;
            }
        }

        ImageView imageKey = (ImageView) findViewById(R.id.key);
        if(key == 0) {
            imageKey.setImageResource(R.drawable.key_sol);
        }
        else {
            imageKey.setImageResource(R.drawable.key_fa);
        }

        /** New note **/
        final Note randNote = new Note(key, 0);

        NoteView noteView = (NoteView) findViewById(R.id.noteView);
        noteView.setNote(randNote);

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
                    float scoreResult = getButtonScore(exoCode,randNote.getNote(), v);
                    if(scoreResult > -2) {
                        if(scoreResult != -1 ) {
                            //Display finished and score
                            Intent intent = new Intent(ReconnaissanceNoteActivity.this, ReconnaissanceNoteEndActivity.class);
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


        /** Skip note **/
        Button button_skip = (Button) findViewById(R.id.skipButton);
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float scoreResult = skip(exoCode,randNote.getNote());
                if(scoreResult != -1 ) {
                    //Display finished and score
                    Intent intent = new Intent(ReconnaissanceNoteActivity.this, ReconnaissanceNoteEndActivity.class);
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
}
