package com.dty.manu.toneme;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Sarah on 03/05/2017.
 */

public class ReconnaissanceAuditiveActivity extends Activity{

    ImageView imageNote;
    ImageView imageKeyboard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_auditive);

        imageNote = (ImageView) findViewById(R.id.notePlaceholder);
        imageNote.setImageResource(R.drawable.musicnote_placeholder);

        imageKeyboard = (ImageView) findViewById(R.id.pianoKeyboard);
        imageKeyboard.setImageResource(R.drawable.piano_keys);

        /** Play note **/
        String randNote = randLetter();
        final MediaPlayer mp = MediaPlayer.create(this, getRawIdentifier(this, randNote + "_" + 1));
        mp.start();
        Toast.makeText(getBaseContext(), randNote, Toast.LENGTH_LONG).show();

        setClickNoteListener(randNote);
    }

    public static int getRawIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }

    public static int getIdIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "id", context.getPackageName());
    }

    public static String randLetter() {
        String[] chars = {"a","b","c","d","e","f","g"};
        return (chars[(int) (Math.random() * 10)]);
    }

    public void setClickNoteListener(final String expectedNote) {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];
            RelativeLayout button_note = (RelativeLayout) findViewById(getIdIdentifier(this, "note_" + letter));

            button_note.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String noteText = v.getResources().getResourceName(v.getId());
                    noteText = noteText.substring(noteText.length()-1);
                    if(expectedNote.equals(noteText)) {
                        Toast.makeText(getBaseContext(), "Bravo tu as réussi !!!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


}