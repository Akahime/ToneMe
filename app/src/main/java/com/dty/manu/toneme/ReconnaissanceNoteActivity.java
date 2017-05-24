package com.dty.manu.toneme;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Sarah on 22/05/2017.
 */

public class ReconnaissanceNoteActivity extends RootActivity {
    //implements SurfaceHolder.Callback

    private static final String TAG = "Svetlin SurfaceView";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_note);

        /** Choose random key **/
        int key = (int) (Math.random() * 2);

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
        setClickNoteListener(randNote.getNote());

        /** Skip note **/
        Button button_skip = (Button) findViewById(R.id.skipButton);
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(randNote.getNote());
            }
        });
    }
}
