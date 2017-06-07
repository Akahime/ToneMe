package com.dty.manu.toneme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Sarah on 07/06/2017.
 */

public class TheorieAccordActivity extends ExerciceActivity {
    byte exoCode = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        /** Set layout **/
        setContentView(R.layout.activity_chord_theory);

        setCurrentScore(exoCode);

        /** Random chord **/
        final Chord randChord = ((ExoApplication) this.getApplication()).chordList[rand.nextInt(14)];

        TextView chordText = (TextView) findViewById(R.id.chord);
        String chordNote = getResources().getString(getStringIdentifier(this, "note_"+randChord.getNameNote()));
        String chordWeight = randChord.getNameWeight() == 'M' ? getResources().getString(R.string.chord_major) : getResources().getString(R.string.chord_minor);
        chordText.setText(chordNote+ " " + chordWeight);
    }
}
