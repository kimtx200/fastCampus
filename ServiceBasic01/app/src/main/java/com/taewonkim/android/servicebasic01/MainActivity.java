package com.taewonkim.android.servicebasic01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // 서비스 실행
    public void startService(View v){
        Intent intent = new Intent(this, MainService.class);
        startService(intent);

        Log.i(TAG, "Main =====================");
    }

    // 서비스 종료
    public void stopService(View v){
        Intent intent = new Intent(this, MainService.class);
        stopService(intent);
    }

    // Intent Service Start
    public void startIntentService(View v){
        Intent intent = new Intent(this, SubService.class);
        startService(intent);

        Log.i(TAG, "SUB =====================");
    }

    // Intent Service Stop
    public void stopIntentService(View v){
        Intent intent = new Intent(this, SubService.class);
        stopService(intent);
    }
}

