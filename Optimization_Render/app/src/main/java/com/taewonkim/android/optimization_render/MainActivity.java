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
        print100000();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }

    public void print100000(){
        for(int i=0; i<100000; i++)
            Log.i("Performance Test",">>>>>>>>>>>>>>>>>>>>> i = "+i);
    }
}
