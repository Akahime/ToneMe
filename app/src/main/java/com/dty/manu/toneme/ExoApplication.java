package com.dty.manu.toneme;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 31/05/2017.
 */

public class ExoApplication extends Application {
    public String EXO_REC_AUDITIVE = "exoRecAuditive";
    public String EXO_REC_NOTE = "exoRecNote";
    private List<Integer> exoRecAuditive;
    private List<Integer> exoRecNote;

    public List<Integer> getExo(String exoName) {
        if(exoName.equals(this.EXO_REC_AUDITIVE)) {
            return exoRecAuditive;
        }
        else {
            return exoRecNote;
        }
    }

    public int getExoSize(String exoName) {
        if(exoName.equals(this.EXO_REC_AUDITIVE)) {
            return exoRecAuditive.size();
        }
        if(exoName.equals(this.EXO_REC_NOTE)) {
            return exoRecNote.size();
        }
        else {
            return -1;
        }
    }

    public int getExoResult(String exoName) {
        if(exoName.equals(this.EXO_REC_AUDITIVE)) {
            return sum(exoRecAuditive);
        }
        if(exoName.equals(this.EXO_REC_NOTE)) {
            return sum(exoRecNote);
        }
        else {
            return -1;
        }
    }

    public void resetExo(String exoName) {
        if(exoName.equals(this.EXO_REC_AUDITIVE)) {
            this.exoRecAuditive = new ArrayList<>();
        }
        if(exoName.equals(this.EXO_REC_NOTE)) {
            this.exoRecAuditive = new ArrayList<>();
        }
    }

    public void setExoResult(String exoName, int res) {
        if(exoName.equals(this.EXO_REC_AUDITIVE)) {
            this.exoRecAuditive.add(res);
        }
        if(exoName.equals(this.EXO_REC_NOTE)) {
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
