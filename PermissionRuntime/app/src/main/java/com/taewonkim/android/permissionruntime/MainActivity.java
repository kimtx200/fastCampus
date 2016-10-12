package com.taewonkim.android.permissionruntime;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.jar.Manifest;

/*
    // 런타임 권한
 */
public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        // 권한이 필요한 경우와 필요 없는 경우를 분리 해 놓는 로직
        if( Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            createFile();
        }
        else {

        }

        // 1. 유효성 체크 - 권한을 획득하기 전
        // checkSelfPermission();

        // 2. 권한에 대한 부가적인 설명이 필요 할 경우 호출 - 권한 획득을 거부 했을 경우
        // shouldShowRequestPermissionRationale();

        // 3. 권한을 획득하기 위해 호출
        // requestPermissions();

        // 4. 완료 후 최종 결과 값을 처리하는 callback 함수가 자동을 호출 된다.
        // onRequestPermissionsResult();

        // adb 접속 명령어
        // adb -s [디바이스 명] shell

        // adb 디바이스 명 검색
        // db devices

    }

    private void checkPermissions(){
        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            // 쓰기 권한이 없으면 로직 처리
            String permissionArray[] = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionArray, REQUEST_CODE);
        }
        else{

            // 쓰기 권한이 있으면 파일 생성
            createFile();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case REQUEST_CODE :
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    createFile();
                break;

        }
    }

    private void createFile(){

        // External Strorage 의 Root Directory 를 가져 온다.
        String filePath = Environment.getExternalStorageDirectory().getPath();

        Log.i("Root Path ", filePath);

        File file = new File(filePath + File.separator + "temp.txt");

        // File I/O Exception 에 대한 처리
        if(!file.exists()){
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
