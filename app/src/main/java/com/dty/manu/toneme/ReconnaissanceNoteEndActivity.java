package com.dty.manu.toneme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sarah on 05/06/2017.
 */

public class ReconnaissanceNoteEndActivity extends Activity {
    final byte exoCode = 1;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set Content **/
        setContentView(R.layout.activity_rec_note_end);

        /** Set score **/
        DatabaseHandler db = new DatabaseHandler(this);

        TextView scoreText =(TextView)findViewById(R.id.score);
        String scoreIntro = scoreText.getText().toString();
        int score = (int) (db.getLastResult(exoCode) * (float) 100);
        Log.d("test", "score : "+score);
        scoreText.setText(scoreIntro+" "+Integer.toString(score)+"%");

        /** Button listeners **/
        final Button buttonTest = (Button) findViewById(R.id.new_test);
        final Button buttonBack = (Button) findViewById(R.id.menu);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReconnaissanceNoteEndActivity.this, ReconnaissanceNoteActivity.class);
                startActivity(intent);
            }
        });

        /** Button listeners **/
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReconnaissanceNoteEndActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}