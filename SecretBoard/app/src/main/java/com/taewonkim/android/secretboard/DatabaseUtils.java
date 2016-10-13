package com.taewonkim.android.secretboard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.taewonkim.android.secretboard.com.taewon.android.secretboard.domain.Memo;

import java.util.ArrayList;

/**
 * Created by 태원 on 2016-10-12.
 */
public class DatabaseUtils extends SQLiteOpenHelper {

    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION = 2;

    // 사용 될 것으로 예상되는 쿼리를 먼저 생성 해 둔다.
    // CRUD query 생성

    public void dbInsert(Memo memo) {

        SQLiteDatabase db = getWritableDatabase();

        String query = " insert into memo(title, name, contents, nDate, image) " +
                "values('" + memo.title + "', '" + memo.name + "', '" + memo.contents + "', " + memo.nDate + ",'" + memo.image + "')";
        db.execSQL(query);
        db.close();
    }

    public Memo dbSelectSingleTarget(int no) {
        Memo memo = new Memo();

        return memo;
    }

    public ArrayList<Memo> dbSelectAll() {
        ArrayList<Memo> database = new ArrayList<>();

        // 1. DB 를 읽기 모드로 생성
        SQLiteDatabase db = getReadableDatabase();
        // 2. select 쿼리를 작성
        String query = " select * from memo";
        // 3. 쿼리를 실행 한 후 Cursor 에 담아
        Cursor cursor = db.rawQuery(query, null);
        // 4. 커서에 담긴 내용을 while 문을 돌면서 꺼내도록 한다.
        while (cursor.moveToNext()) {
            // 5.1 한줄씩 메모 객체에 담아 준다
            Memo memo = new Memo();

            int index = cursor.getColumnIndex("no");
            memo.no = cursor.getInt(index);

            index = cursor.getColumnIndex("title");
            memo.title = cursor.getString(index);

            index = cursor.getColumnIndex("name");
            memo.name = cursor.getString(index);

            index = cursor.getColumnIndex("contents");
            memo.contents = cursor.getString(index);

            index = cursor.getColumnIndex("nDate");
            memo.nDate = cursor.getInt(index);

            index = cursor.getColumnIndex("image");
            memo.image = cursor.getString(index);

            // 5.2 ArrayList 에 add 해 준다.

            database.add(memo);

        }

        return database;

    }

    public void update(Memo memo) {

    }

    public void delete(Memo memo) {

    }


    public DatabaseUtils(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        // DB_VERSION 1 - 최초 등록 2016-10-13 11:30
//        // sheme 역할 - create table query 생성
//
//        String scheme_01 = "create table memo(no integer primary key autoincrement not null" +
//                ", title text not null" +
//                ", name text not null" +
//                ", context text not null" +
//                ", nDate integer no null)";
//
//        sqLiteDatabase.execSQL(scheme_01);

        // db version 2 - upgrade 2016-10-13 14:08
        String scheme_02 = "create table memo(no integer primary key autoincrement not null" +
                ", title text not null" +
                ", name text not null" +
                ", contents text not null" +
                ", nDate integer not null" +
                ", image text)";

        sqLiteDatabase.execSQL(scheme_02);
    }

    ArrayList<Memo> backupDatabase = new ArrayList<>();

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // 이전 데이터베이스 버전에 따라 처리방식이 달라진다.

        if (oldVersion == 1) {
            String query = "select no, contents, nDate from memo ordey by no";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Memo memo = new Memo();
                int index = cursor.getColumnIndex("no");
                memo.no = cursor.getInt(index);

                index = cursor.getColumnIndex("title");
                memo.title = cursor.getString(index);

                index = cursor.getColumnIndex("name");
                memo.name = cursor.getString(index);

                index = cursor.getColumnIndex("contents");
                memo.contents = cursor.getString(index);

                index = cursor.getColumnIndex("nDate");
                memo.nDate = cursor.getInt(index);

                backupDatabase.add(memo);
            }

            String scheme_01_drop = "drop table memo";
            sqLiteDatabase.execSQL(scheme_01_drop);

        } else if (oldVersion == 2) {



        }

        onCreate(sqLiteDatabase);

    }

    // 현재 시간을 밀리세컨드 단위로 리턴해 준다.
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }
}
