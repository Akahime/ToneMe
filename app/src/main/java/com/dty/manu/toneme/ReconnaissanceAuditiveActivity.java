package com.dty.manu.toneme;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Sarah on 03/05/2017.
 */

public class ReconnaissanceAuditiveActivity extends RootActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_auditive);

        ImageButton button_replay = (ImageButton) findViewById(R.id.replayButton);
        button_replay.setImageResource(R.drawable.icon_sound);

        /** Play note **/
        final String randNote = randLetter();
        final MediaPlayer mp = MediaPlayer.create(this, getRawIdentifier(this, randNote + "_" + (rand.nextInt(3)+1)));
        mp.start();

        /** Check if selected note is correct **/
        setClickNoteListener(randNote);

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
                skip(randNote);
            }
        });
    }

}