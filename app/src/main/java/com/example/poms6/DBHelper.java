package com.example.poms6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="resultDb";
    public static final String TABLE_RESULT="results";
    public static final String KEY_ID="_id";
    public static final String KEY_PROGRAMS="program";
    public static final String KEY_RESULT="result";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String request="create table "+TABLE_RESULT+"("+KEY_ID+" integer primary key , "+KEY_PROGRAMS+" text , "+ KEY_RESULT+" text"+")";
        db.execSQL(request);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_RESULT);
        onCreate(db);
    }
}
