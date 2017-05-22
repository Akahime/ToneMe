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
        this.height = rand.nextInt(15)-3;
        this.position = p+1;
        this.length = 1;
    }

    public char getNote( ) {
        if(this.key == 0) {
            if(this.height == -1 || this.height == 6) {
                return 'C';
            }
            else if(this.height == 0 || this.height == 7) {
                return 'D';
            }
            else if(this.height == 1 || this.height == 8) {
                return 'E';
            }
            else if(this.height == 2  || this.height == 9) {
                return 'F';
            }
            else if(this.height == 3 || this.height == 10) {
                return 'G';
            }
            else if(this.height == -3 || this.height == 4 || this.height == 11) {
                return 'A';
            }
            else if(this.height == -2 || this.height == 5 || this.height == 12) {
                return 'B';
            }
        }
        else if (this.key == 1) {
            if(this.height == -1 || this.height == 6) {
                return 'F';
            }
            else if(this.height == 0 || this.height == 7) {
                return 'G';
            }
            else if(this.height == 1 || this.height == 8) {
                return 'A';
            }
            else if(this.height == 2  || this.height == 9) {
                return 'B';
            }
            else if(this.height == 3 || this.height == 10) {
                return 'C';
            }
            else if(this.height == -3 || this.height == 4 || this.height == 11) {
                return 'D';
            }
            else if(this.height == -2 || this.height == 5 || this.height == 12) {
                return 'E';
            }
        }
        return '0';
    }

}
