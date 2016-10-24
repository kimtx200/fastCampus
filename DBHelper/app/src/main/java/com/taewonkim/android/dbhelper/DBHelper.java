package com.taewonkim.android.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 태원 on 2016-10-12.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION = 2;

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
        // assets 에 있는 파일을 복사하여 디스크로 옮김
        // 데이터에 대한 변경이 아닌 데이터베이스 구조의 변경이 발생 했을때에는 ...
        //

        String query = "delete from bbs4";
        db.execSQL(query);

        for(int i=0;i<100;i++){
            query = "insert into bbs4(title) values ('changed number is "+i+"')";
            db.execSQL(query);
        }
    }

    // DB_VERSION 이 변경 되었을 때, 자동으로 호출 된다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // version - up 이 되었을 경우, 새로이 onCreate 를 호출해 준다.
        // 이전버전을 가진 유저에겐 기존의 onUpgrade 를 호출 시키고
        // 현재버전을 처음 설치하는 유저에게는 onCreate 를 호출 시키도록 유도 할 수 있다.

        // 단, 하위버전으로 옮기게 되면 이 메소드는 동작하지 않는다.


        onCreate(db);
    }
}
