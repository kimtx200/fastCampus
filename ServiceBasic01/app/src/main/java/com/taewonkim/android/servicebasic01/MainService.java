package com.taewonkim.android.servicebasic01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {

    private static final String TAG = "MainService";

    public MainService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "======================== on create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "======================== on start");

        for(int i=0; i<100; i++)
            Log.i(TAG, "Main Service ====================="+i);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "======================== on destroy");
    }

    // Bound Service 에 쓰이는 형태
    // 이미 Start 가 되어있는 상태에서 다른 서비스를 bound 할 때 필요함.
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
