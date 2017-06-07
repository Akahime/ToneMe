package com.dty.manu.toneme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Test db **/
        DatabaseHandler db = new DatabaseHandler(this);
        //Toast.makeText(getBaseContext(), "La bdd a un nombre de rangs de "+db.numberEntries(), Toast.LENGTH_SHORT).show();

        /** Settings **/
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        /** Initialize chords **/
        ((ExoApplication) this.getApplication()).initializeChordList();

        /** Set Content **/
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final LinearLayout button1 = (LinearLayout) findViewById(R.id.exercice_1);
        final LinearLayout button2 = (LinearLayout) findViewById(R.id.exercice_2);
        final LinearLayout button3 = (LinearLayout) findViewById(R.id.exercice_3);
        final LinearLayout button4 = (LinearLayout) findViewById(R.id.exercice_4);

        /** Scores **/
        String bestScore = getResources().getString(R.string.score_best);

        int score1 = (int) (db.getBestResult((byte) 0) * (float) 100);
        TextView scoreText1 =(TextView)findViewById(R.id.score_exercice_1);
        if(score1>0) {
            scoreText1.setText(bestScore+Integer.toString(score1)+"%");
        }

        int score2 = (int) (db.getBestResult((byte) 1) * (float) 100);
        TextView scoreText2 =(TextView)findViewById(R.id.score_exercice_2);
        if(score2>0) {
            scoreText2.setText(bestScore+Integer.toString(score2)+"%");
        }

        int score3 = (int) (db.getBestResult((byte) 2) * (float) 100);
        TextView scoreText3 =(TextView)findViewById(R.id.score_exercice_3);
        if(score3>0) {
            scoreText3.setText(bestScore+Integer.toString(score3)+"%");
        }

        int score4 = (int) (db.getBestResult((byte) 3) * (float) 100);
        TextView scoreText4 =(TextView)findViewById(R.id.score_exercice_4);
        if(score4>0) {
            scoreText4.setText(bestScore+Integer.toString(score4)+"%");
        }


        /** Button listeners **/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReconnaissanceAuditiveActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReconnaissanceNoteActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReconnaissanceAccordActivity.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TheorieAccordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivityForResult(new Intent(this, SettingsActivity.class), SettingsFragment.LANGUAGE_CHANGED);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SettingsFragment.LANGUAGE_CHANGED:
                if (resultCode == SettingsFragment.LANGUAGE_CHANGED) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
        }
    }
}
