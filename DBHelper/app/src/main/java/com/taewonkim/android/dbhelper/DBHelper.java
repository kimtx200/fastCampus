package com.taewonkim.android.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 태원 on 2016-10-12.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION = 1;

    private static DBHelper dbHelper = null;

    private DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    public static SQLiteDatabase openDatabase(Context context){
        if(dbHelper == null)
            dbHelper = new DBHelper(context);

        return dbHelper.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scheme = "create table bbs4 (no integer primary key autoincrement not null"
                        + " ,title text default null)";
        db.execSQL(scheme);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
