package com.dty.manu.toneme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 31/05/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "resultsManager";
    // Results table name
    private static final String TABLE_RESULTS = "results";
    // Results Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_PERCENT = "result";
    private static final String KEY_DATE = "date";

    // The static string to create the database.
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RESULTS + "(" + KEY_ID
            + " integer primary key autoincrement, "
            + KEY_PERCENT + " float, "
            + KEY_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP) ";

    /**
     * @param context
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DatabaseHandler", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");
        // Drop the old database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        // Create the new one
        onCreate(db);
        // or do a smartest stuff
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RESULTS);
    }

    public long numberEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("DATABASE","Number");
        return DatabaseUtils.queryNumEntries(db, TABLE_RESULTS);
    }

    public long addResult(float percent) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Insert the values for each column
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PERCENT, percent);

        // Insert the line in the database
        long rowId = db.insert(TABLE_RESULTS, null, contentValues);

        // Test to see if the insertion was ok
        if (rowId == -1) {
            Log.d("DATABASE","Error when creating the result");
        } else {
            Log.d("DATABASE","Result created and stored in database with id : "+ rowId);
        }
        return rowId;
    }

    public long updateResult(long updateRowId, float percent) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Insert the values for each column
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PERCENT, percent);

        // update the database
        long rowId = db.update(TABLE_RESULTS, contentValues, KEY_ID + "=" + updateRowId, null);

        // Test to see if the insertion was ok
        if (rowId == -1) {
            Log.d("DATABASE","Error when updating the result");
        } else {
            Log.d("DATABASE","Result updated with id : "+ rowId);
        }
        return rowId;
    }

    public List<Result> getAllResults() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Result> resultList = new ArrayList<Result>();

        Cursor cursor = db.query(TABLE_RESULTS, null, null, null, null, null, null);
        int count = 1;

        try {

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Result result = new Result(0,new Timestamp(System.currentTimeMillis()));
                    result.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                    //result.setPercent(cursor.getString(cursor.getColumnIndex(KEY_PERCENT)));
                    //result.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                    resultList.add(result);
                    Log.d("DATABASE","Count : "+count);
                    count += 1;
                } while (cursor.moveToNext());
            }

        } finally {
            try { cursor.close(); } catch (Exception ignore) {}
        }

        return resultList;
    }
}
