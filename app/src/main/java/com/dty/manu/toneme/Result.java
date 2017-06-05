package com.dty.manu.toneme;

import java.sql.Timestamp;

/**
 * Created by Sarah on 31/05/2017.
 */

public class Result {
    private long _id;
    private byte exercice;
    private float percent;
    private Timestamp date;

    public Result(float percent, Timestamp date) {
        this.percent = percent;
        this.date = date;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public byte getExercice() {
        return exercice;
    }

    public void setExercice(byte exercice) {
        this.exercice = exercice;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
