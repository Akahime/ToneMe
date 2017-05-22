package com.dty.manu.toneme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String randNote = randLetter() + "_" + 1;
        final MediaPlayer mp = MediaPlayer.create(this, getRawIdentifier(this, randNote));
        mp.start();

        //getClickedNote();

    }

    public static int getRawIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }

    public static String randLetter() {
        String[] chars = {"a","b","c","d","e","f","g"};
        return (chars[(int) (Math.random() * 10)]);
    }

    public void getClickedNote() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        for(String letter : letters) {
            final Button button_note = (Button) findViewById(getRawIdentifier(this, "note_"+letter));

            button_note.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonText = ((Button)v).getText().toString();
                    Toast.makeText(getBaseContext(), buttonText, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}