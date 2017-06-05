package com.dty.manu.toneme;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 31/05/2017.
 */

public class ExoApplication extends Application {
    private byte CODE_EXO_REC_AUDITIVE = 0;
    private byte CODE_EXO_REC_NOTE = 1;
    private List<Integer> exoRecAuditive = new ArrayList<>();
    private List<Integer> exoRecNote = new ArrayList<>();

    public List<Integer> getExo(byte exoCode) {
        if(exoCode == 0) {
            return exoRecAuditive;
        }
        else {
            return exoRecNote;
        }
    }

    public int getExoSize(byte exoCode) {
        if(exoCode == 0) {
            return exoRecAuditive.size();
        }
        if(exoCode == 1) {
            return exoRecNote.size();
        }
        else {
            return -1;
        }
    }

    public int getExoResult(byte exoCode) {
        if(exoCode == 0) {
            return sum(exoRecAuditive);
        }
        if(exoCode == 1) {
            return sum(exoRecNote);
        }
        else {
            return -1;
        }
    }

    public void resetExo(byte exoCode) {
        if(exoCode == 0) {
            this.exoRecAuditive = new ArrayList<>();
            Log.d("test", "Reset rec auditive size : "+exoRecAuditive.size());
        }
        if(exoCode == 1) {
            this.exoRecNote = new ArrayList<>();
            Log.d("test", "Reset rec note size : "+exoRecNote.size());
        }
    }

    public void setExoResult(byte exoCode, int res) {
        if(exoCode == 0) {
            this.exoRecAuditive.add(res);
        }
        if(exoCode == 1) {
            this.exoRecNote.add(res);
        }
    }
    public static int sum(List<Integer> list){
        if(list==null || list.size()<1)
            return 0;

        int sum = 0;
        for(Integer i: list)
            sum = sum+i;

        return sum;
    }

}
