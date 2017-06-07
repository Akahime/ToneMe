package com.dty.manu.toneme;
import java.util.*;

/**
 * Created by Sarah on 05/05/2017.
 */

public class Note implements Cloneable{
    //Height : valeur de la note dans la portée. 0 : Ré (clé Sol)
    private int height;
    //Position : position gauche-droite portée

    //Clé. Sol : 0, Fa : 1
    private int key;

    /** Getters and Setters **/
    public int getHeight( ) {
        return height;
    }
    public void setHeight( int h ) {
        this.height = h;
    }

    public int getKey( ) {
        return key;
    }
    public void setKey( int k ) {
        this.key = k;
    }


    /** Contructors **/
    public Note(int k, int h) {
        this.key = k;
        this.height = h;
    }

    public Note(int k) {
        Random rand = new Random();

        this.key = k;
        this.height = rand.nextInt(12)-1;
    }

    /** Methods **/
    public String getNote( ) {
        if(this.key == 0) {
            if(this.height == -1 || this.height == 6) {
                return "c";
            }
            else if(this.height == 0 || this.height == 7) {
                return "d";
            }
            else if(this.height == 1 || this.height == 8) {
                return "e";
            }
            else if(this.height == 2  || this.height == 9) {
                return "f";
            }
            else if(this.height == 3 || this.height == 10) {
                return "g";
            }
            else if(this.height == 4) {
                return "a";
            }
            else if(this.height == 5) {
                return "b";
            }
        }
        else if (this.key == 1) {
            if(this.height == -1 || this.height == 6) {
                return "e";
            }
            else if(this.height == 0 || this.height == 7) {
                return "f";
            }
            else if(this.height == 1 || this.height == 8) {
                return "g";
            }
            else if(this.height == 2  || this.height == 9) {
                return "a";
            }
            else if(this.height == 3 || this.height == 10) {
                return "b";
            }
            else if(this.height == 4) {
                return "c";
            }
            else if(this.height == 5) {
                return "d";
            }
        }
        return "0";
    }

    @Override
    public Note clone() {
        Note cloneNote = null;
        try {
            cloneNote = (Note) super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e);
        }
        return cloneNote;
    }

}
