package com.dty.manu.toneme;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map;

/**
 * Created by Sarah on 07/06/2017.
 */

public class Chord {
    /** Do, Ré .. */
    private char nameNote;

    /** Mineur = m / majeur = M **/
    private char nameWeight;

    private int[] notes;

    /** Si dièse, bémol. premier int : hauteur dans la portée, 2e = -1 si bemol, +1 si diese **/
    private Map<Integer,Integer> spec;

    public char getNameNote() {
        return nameNote;
    }

    public void setNameNote(char nameNote) {
        this.nameNote = nameNote;
    }

    public char getNameWeight() {
        return nameWeight;
    }

    public void setNameWeight(char nameWeight) {
        this.nameWeight = nameWeight;
    }

    public Map<Integer, Integer> getSpec() {
        return spec;
    }

    public void setSpec(Map<Integer, Integer> spec) {
        this.spec = spec;
    }

    /** Getters and Setters **/


    public int[] getNotes() {
        return notes;
    }

    public void setNotes(int[] notes) {
        this.notes = notes;
    }

    public Chord(char nameNote, char nameWeight, int[] notes, Map<Integer, Integer> spec) {
        this.nameNote = nameNote;
        this.nameWeight = nameWeight;
        this.notes = notes;
        this.spec = spec;
    }

    /** Constructor **/


    public boolean checkCord(int[] testNotes, Chord chord) {
        int[] chordNotes = chord.getNotes();
        return false;
    }


}
