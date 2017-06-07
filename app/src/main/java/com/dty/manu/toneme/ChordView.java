package com.dty.manu.toneme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sarah on 07/06/2017.
 */

public class ChordView extends NoteView {

    private ArrayList<Note> noteList = new ArrayList<>();
    private Map<Integer,Integer> specList = new HashMap<>();

    public ChordView (Context context) {
        super(context);
    }
    public ChordView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoteList(Chord c) {
        for(int height : c.getNotes()) {
            Note note = new Note(0,height);
            this.noteList.add(note);
        }

        this.specList = c.getSpec();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        for(Note note : noteList) {
            super.drawNote(canvas, viewWidth, note);
        }
        //for (Map.Entry<Integer,Integer> entry : specList.entrySet()) {
        //    super.drawSpec(canvas, viewWidth, entry.getKey(), entry.getValue());
        //}
    }

}

