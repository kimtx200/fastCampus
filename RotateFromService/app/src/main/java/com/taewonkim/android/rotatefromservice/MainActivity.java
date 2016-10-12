package com.taewonkim.android.rotatefromservice;

import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    public static final String PENDING_RESULT = "pending_result";
    public static final String RESULT = "result";
    private static final int REQUEST_CODE = 0;

    Button btnRotate;
    int angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRotate = (Button) findViewById(R.id.btnRotate);
    }

    public void run(View v) {

        PendingIntent pending = createPendingResult(REQUEST_CODE, new Intent(), 0);
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra(PENDING_RESULT, pending);
        startService(intent);

    }

    protected void onActivityResult(int req, int res, Intent data) {
        if (req == REQUEST_CODE) {
            int angle = 0;

            angle = data.getIntExtra(RESULT, angle);
            ObjectAnimator ani = ObjectAnimator.ofFloat(btnRotate, "rotation", angle);
            ani.start();
        }
        super.onActivityResult(req, res, data);
    }

}
