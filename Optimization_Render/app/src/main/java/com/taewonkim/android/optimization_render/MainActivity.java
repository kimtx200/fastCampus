package com.taewonkim.android.optimization_render;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.startMethodTracing("TraceResult");

        Thread thread01 = new Thread(){

            @Override
            public void run() {
                super.run();
                print1000("Thread 01");
            }
        };
        thread01.start();

        Thread thread02 = new Thread(){

            @Override
            public void run() {
                super.run();
                print1000("Thread 02");
            }
        };
        thread02.start();

        print1000("main");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }

    public void print1000(String tag){
        for(int i=0; i<1000; i++)
            Log.i("Performance Test",">>>>>>>>>>>>>>>>>>>>> "+ tag +" " + i);
    }
}
