package com.taewonkim.android.servicebasic01;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SubService extends IntentService {

    public static final String TAG = "SubService";

    public SubService() {
        super("SubService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "======================== onHandleIntent");

        for (int i = 0; i < 100; i++) {
            /*try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            Log.i(TAG, "Sub Service =====================" + i);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "======================== onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "======================== onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i(TAG, "Sub Service =====================" + i);

        }
        return super.onStartCommand(intent, flags, startId);
    }
}
