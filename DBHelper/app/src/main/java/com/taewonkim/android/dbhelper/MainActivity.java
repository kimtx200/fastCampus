package com.taewonkim.android.dbhelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = DBHelper.openDatabase(this);

        Log.i("database","Path = "+db);

        Cursor cursor = db.rawQuery("select * from bbs4",null);

        while(cursor.moveToNext()){
            // 가져 온 select 구문에서 첫번째 속성 값을들 가져온다.
            Log.i("data check","title"+cursor.getString(1));
        }
        cursor.close();
        db.close();

    }
}
