package com.taewonkim.android.sqlite_dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 태원 on 2016-10-12.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION =1;

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scheme = "create table bbs4(no integer not null autoincrement primary key"
                        + ", title text nat null)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
