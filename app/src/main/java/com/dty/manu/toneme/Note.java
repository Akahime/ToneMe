package com.dty.manu.toneme;
import java.util.*;

/**
 * Created by Sarah on 05/05/2017.
 */

public class Note {
    //Height : valeur de la note dans la portée. 0 : Ré (clé Sol)
    private int height;
    //Position : position gauche-droite portée
    private float position;
    //Length: noire: 1, blanche: 2
    private float length;
    //Clé. Sol : 0, Fa : 1
    private int key;

    public int getHeight( ) {
        return height;
    }
    public void setHeight( int h ) {
        this.height = h;
    }

    public float getPosition( ) {
        return position;
    }
    public void setPosition( float p ) {
        this.position = p;
    }

    public float getLength( ) {
        return length;
    }
    public void setLength( float l ) {
        this.length = l;
    }

    public int getKey( ) {
        return key;
    }
    public void setKey( int k ) {
        this.key = k;
    }

    public Note(int k, int h, float p, float l) {
        this.key = k;
        this.height = h;
        this.position = p;
        this.length = l;
    }

    public Note(int k, float p) {
        Random rand = new Random();

        this.key = k;
        this.height = rand.nextInt(12)-1;
        this.position = p+1;
        this.length = 1;
    }

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

}
