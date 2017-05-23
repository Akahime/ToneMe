package com.dty.manu.toneme;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sarah on 23/05/2017.
 */

public class RootActivity extends AppCompatActivity {

    int onStartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onStartCount = 1;
        if (savedInstanceState == null) // 1st time
        {
            this.overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_left);
        } else // already created so reverse animation
        {
            onStartCount = 2;
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_right);

        } else if (onStartCount == 1) {
            onStartCount++;
        }

    }
    
    public static int getRawIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }

    public static int getIdIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "id", context.getPackageName());
    }

    public static String randLetter() {
        String[] chars = {"a","b","c","d","e","f","g"};
        return (chars[(int) (Math.random() * 7)]);
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
                        /** Highlight correct **/
                        ViewGroup vg = (ViewGroup) v;
                        TextView text_note = (TextView) vg.getChildAt(0);
                        text_note.setBackgroundColor(Color.parseColor("#cddc39"));
                        /** Go to next **/
                        finish();
                        startActivity(getIntent());
                    }
                    else {
                        Toast.makeText(getBaseContext(), getApplicationContext().getString(R.string.exercice_fail), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void skip(final String expectedNote) {
        /** Highlight correct **/
        RelativeLayout button_note = (RelativeLayout) findViewById(getIdIdentifier(this, "note_" + expectedNote));
        TextView text_note = (TextView) button_note.getChildAt(0);
        text_note.setBackgroundColor(Color.parseColor("#cddc39"));

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
