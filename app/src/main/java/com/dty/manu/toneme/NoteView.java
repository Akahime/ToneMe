package com.dty.manu.toneme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Sarah on 24/05/2017.
 */

public class NoteView extends View {

    private Note note;

    public NoteView (Context context) {
        super(context);
    }
    public NoteView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNote(Note note){
        this.note = note;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        drawNote(canvas, viewWidth);
    }

    private void drawNote(Canvas canvas, int viewWidth) {
        //Ecart entre 2 notes: 30
        //Base (height = 0) : 410
        if(this.note == null) {
            Log.d("ERROR", "No note given");
        }
        else {
            //Log.d("INFO", "Note : "+this.note.getNote()+", height : "+this.note.getHeight());
            float x = viewWidth/2-50;
            float y = 410 - note.getHeight()*30;
            RectF rect = new RectF(x,y,x+60,y+50);
            Paint paint = makePaint(Color.BLACK);
            canvas.drawOval(rect, paint);
        }
    }

    private Paint makePaint(int color) {
        Paint p = new Paint();
        p.setColor(color);
        return(p);
    }
}
