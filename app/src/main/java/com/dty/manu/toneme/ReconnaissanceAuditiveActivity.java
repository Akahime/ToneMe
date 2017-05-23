package com.dty.manu.toneme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sarah on 03/05/2017.
 */

public class ReconnaissanceAuditiveActivity extends RootActivity{

    ImageView imageNote;
    ImageView imageKeyboard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set layout **/
        setContentView(R.layout.activity_rec_auditive);

        ImageButton button_replay = (ImageButton) findViewById(R.id.replayButton);
        button_replay.setImageResource(R.drawable.icon_sound);

        /** Play note **/
        final String randNote = randLetter();
        final MediaPlayer mp = MediaPlayer.create(this, getRawIdentifier(this, randNote + "_" + (int) (Math.random() * 3 + 1)));
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