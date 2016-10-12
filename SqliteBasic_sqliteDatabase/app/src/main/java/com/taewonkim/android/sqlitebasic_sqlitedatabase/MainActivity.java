package com.taewonkim.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    InputStream is;
    BufferedInputStream bis;
    FileOutputStream fos;
    BufferedOutputStream bos;

    TextView result;
    Button open;
    Button insert;
    Button update;
    Button delete;
    Button select;

    SQLiteDatabase db;

    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = "sqlite.db";
        assetToDisk(fileName);

        init();

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // 데이터베이스를 연결하는 API      // ↘ DB 파일의 pull path
                db = SQLiteDatabase.openDatabase(getFullPath(fileName), null, 0);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( db != null ){
                    // select 문 이외의 모든 쿼리를 실행 해 준다.
                    db.execSQL("insert into bbs2(title, name, contents) values('글 제목', '태원', '피곤피곤');");

                    // select 문의 경우 rawQuery 를 사용한다.
                    // db.rawQuery();
                }
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( db != null){
                    Cursor cursor = db.rawQuery("select * from bbs2 order by no", null);
                    while(cursor.moveToNext()){
                        int index = cursor.getColumnIndex("no"); // 컬럼 명에 해당하는 순서를 가져 온다.
                        String no = cursor.getString(index);

                        index = cursor.getColumnIndex("name");
                        String name = cursor.getString(index);

                        index = cursor.getColumnIndex("title");
                        String title = cursor.getString(index);

                        index = cursor.getColumnIndex("nData");
                        String nDate = cursor.getString(index);

                        String temp = result.getText().toString();
                        result.setText(temp + "\nNo : "+no+" title : "+title+ " name : "+name + " Date : "+nDate);
                    }
                }
            }
        });

    }

    private void init(){

        File file = new File(getFullPath(fileName));
        if(!file.exists())
            assetToDisk(fileName);

        result = (TextView) findViewById(R.id.txtResult);
        open = (Button) findViewById(R.id.btnOpen);
        insert = (Button) findViewById(R.id.btnInsert);
        update = (Button) findViewById(R.id.btnUpdate);
        delete = (Button)findViewById(R.id.btnDelete);
        select = (Button)findViewById(R.id.btnSelect);

    }

    public String getFullPath(String fileName){

        return getFilesDir().getAbsolutePath() + File.separator + fileName;

    }

    public void assetToDisk(String fileName) {
        try {

            // assets 폴더에 있는 DB 파일을 내부 dir 로 복사 해주어야 한다.
            // 1. assets 에 담아 둔 파일을 폰 내부의 assets 영역으로 복사한다. - asset manager 를 통해 가능함
            AssetManager manager = getAssets();

            is = manager.open(fileName);
            bis = new BufferedInputStream(is);

            String internalPath = getFilesDir().getAbsolutePath();
            // 폰 상의 internal or External 상에 targetFile 이 있는지 없는지를 체크
            Log.i(TAG, "========= internal path" + internalPath);

            // phone 의 root 디렉토리에 저장 되면 파일을 읽고 쓸 수가 없으므로
            // 새로운 경로를 지정 해 주어야 한다.
            String targetFile = getFullPath(fileName);
            Log.i(TAG, "========= target path" + targetFile);

            File file = new File(targetFile);
            if (!file.exists()) {
                file.createNewFile();
            }

            // 3. output  stream 을 생성해서 파일 내용을 쓴다.
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            // buffer 크기를 지정하기 위한 flag 값 - buffer 의 끝을 지정하는 값으로 -1 을 설정
            int read = -1;
            // 한번에 읽어 올 버퍼의 크기를 지정
            byte buffer[] = new byte[1024];

            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                //
                bos.write(buffer, 0, read);
            }
            // 남아있는 데이터를 buffer 에서 써준다.
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
                if (fos != null)
                    fos.close();
                if (bis != null)
                    bis.close();
                if (is != null)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
