package com.dty.manu.toneme;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sarah on 31/05/2017.
 */

public class ExoApplication extends Application {
    private byte CODE_EXO_REC_AUDITIVE = 0;
    private byte CODE_EXO_REC_NOTE = 1;
    private byte CODE_EXO_REC_CORD = 2;
    private List<Integer> exoRecAuditive = new ArrayList<>();
    private List<Integer> exoRecNote = new ArrayList<>();
    private List<Integer> exoRecCord= new ArrayList<>();
    private List<Integer> exoCordTheory= new ArrayList<>();
    public Chord[] chordList = new Chord[14];

    public List<Integer> getExo(byte exoCode) {
        if(exoCode == 0) {
            return exoRecAuditive;
        }
        if(exoCode == 1) {
            return exoRecNote;
        }
        if(exoCode == 2) {
            return exoRecCord;
        }
        else {
            return exoCordTheory;
        }
    }

    public int getExoSize(byte exoCode) {
        if(exoCode == 0) {
            return exoRecAuditive.size();
        }
        if(exoCode == 1) {
            return exoRecNote.size();
        }
        if(exoCode == 2) {
            return exoRecCord.size();
        }
        if(exoCode == 3) {
            return exoCordTheory.size();
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
        if(exoCode == 2) {
            return sum(exoRecCord);
        }
        if(exoCode == 3) {
            return sum(exoCordTheory);
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
        if(exoCode == 2) {
            this.exoRecCord = new ArrayList<>();
            Log.d("test", "Reset rec cord size : "+exoRecCord.size());
        }
        if(exoCode == 3) {
            this.exoCordTheory= new ArrayList<>();
            Log.d("test", "Reset cord theory size : "+exoCordTheory.size());
        }
    }

    public void setExoResult(byte exoCode, int res) {
        if(exoCode == 0) {
            this.exoRecAuditive.add(res);
        }
        if(exoCode == 1) {
            this.exoRecNote.add(res);
        }
        if(exoCode == 2) {
            this.exoRecCord.add(res);
        }
        if(exoCode == 3) {
            this.exoCordTheory.add(res);
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

    /** Inititate Chords **/
    public void initializeChordList() {
        Map emptySpec = new HashMap<Integer, Integer>();
        
        //Do
        int[] listDo = {-1, 1, 3, 6};
        Chord doM = new Chord('c','M', listDo, emptySpec);
        chordList[0] = doM;

        Map specDom = new HashMap<Integer, Integer>();
        specDom.put(1,-1);
        Chord dom = new Chord('c','m', listDo, specDom);
        chordList[1] = dom;

        //Re
        int[] listRe = {0,2,4};
        Map specReM = new HashMap<Integer, Integer>();
        specReM.put(2,1);
        Chord reM = new Chord('d','M', listRe, specReM);
        chordList[2] = reM;
        
        Chord rem = new Chord('d','m', listRe, emptySpec);
        chordList[3] = rem;

        //Mi
        int[] listMi = {1,3,5};
        Map specMiM = new HashMap<Integer, Integer>();
        specMiM.put(3,1);
        Chord miM = new Chord('e','M', listMi, specMiM);
        chordList[4] = miM;
        
        Chord mim = new Chord('e','m', listMi, emptySpec);
        chordList[5] = mim;

        //Fa
        int[] listFa = {2,4,6};
        Chord faM = new Chord('f','M', listFa, emptySpec);
        chordList[6] = faM;

        Map specFam = new HashMap<Integer, Integer>();
        specFam.put(4,-1);
        Chord fam = new Chord('f','m', listFa, specFam);
        chordList[7] = fam;

        //Sol
        int[] listSol = {3,5,7};
        Chord solM = new Chord('g','M', listSol, emptySpec);
        chordList[8] = solM;

        Map specSolm = new HashMap<Integer, Integer>();
        specSolm.put(3,-1);
        Chord solm = new Chord('g','m', listSol, specSolm);
        chordList[9] = solm;

        //La
        int[] listLa = {4,6,8};
        Map specLaM = new HashMap<Integer, Integer>();
        specLaM.put(6,1);
        Chord laM = new Chord('a','M', listLa, specLaM);
        chordList[10] = laM;

        Chord lam = new Chord('a','m', listLa, emptySpec);
        chordList[11] = lam;

        //Si
        int[] listSi = {5,7,9};
        Map specSiM = new HashMap<Integer, Integer>();
        specSiM.put(7,1);
        specSiM.put(9,1);
        Chord siM = new Chord('b','M', listSi, specSiM);
        chordList[12] = siM;

        Map specSim = new HashMap<Integer, Integer>();
        specSim.put(9,1);
        Chord sim = new Chord('b','m', listSi, specSim);
        chordList[13] = sim;
    }

}
