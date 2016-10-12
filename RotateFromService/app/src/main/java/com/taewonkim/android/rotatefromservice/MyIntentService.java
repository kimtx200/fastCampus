package com.taewonkim.android.rotatefromservice;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.math.BigInteger;

public class MyIntentService extends IntentService {

    public static final String PENDING_RESULT = "pending_result";
    public static final String RESULT = "result";
    public static final int RESULT_CODE = 1;
    public static final int REQUEST_CODE = 0;


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int angle = 0;

        for (int i = 0; i <= 10; i++) {
            try {
                angle = 36 * i;
                Intent result = new Intent();
                result.putExtra(RESULT, angle);
                PendingIntent reply = intent.getParcelableExtra(PENDING_RESULT);
                reply.send(this, RESULT_CODE, result);
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("Result", angle + "");
        }
    }
}
