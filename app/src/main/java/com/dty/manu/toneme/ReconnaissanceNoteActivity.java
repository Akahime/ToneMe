package com.dty.manu.toneme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Sarah on 22/05/2017.
 */

public class ReconnaissanceNoteActivity extends ExerciceActivity {
    //implements SurfaceHolder.Callback

    private static final String TAG = "Svetlin SurfaceView";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_note);

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

        /** Get exo code **/
        final String exoName = ((ExoApplication) this.getApplication()).EXO_REC_NOTE;

        /** Check if selected note is correct **/
        setClickNoteListener(exoName,randNote.getNote());

        /** Skip note **/
        Button button_skip = (Button) findViewById(R.id.skipButton);
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(exoName,randNote.getNote());
            }
        });
    }
}
