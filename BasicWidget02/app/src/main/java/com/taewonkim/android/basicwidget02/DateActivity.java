package com.taewonkim.android.basicwidget02;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DateActivity extends AppCompatActivity {

    Chronometer c_timer;
    Button btnStart, btnStop, btnPause, btnLap;
    LinearLayout lapLinear;

    long pauseTime = 0;

    boolean pauseFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        c_timer = (Chronometer) findViewById(R.id.c_timer);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnLap = (Button) findViewById(R.id.btnLap);

        btnStart.setOnClickListener(clickListener);
        btnStop.setOnClickListener(clickListener);
        btnPause.setOnClickListener(clickListener);
        btnLap.setOnClickListener(clickListener);

        lapLinear = (LinearLayout)findViewById(R.id.lapLayout);

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStart:
                    c_timer.setBase(SystemClock.elapsedRealtime());
                    c_timer.start();
                    break;

                case R.id.btnStop:
                    c_timer.stop();
                    break;

                case R.id.btnPause:
                    if (pauseFlag) {
                        long now = SystemClock.elapsedRealtime();
                        long gap = now - pauseTime;
                        c_timer.setBase(c_timer.getBase()+gap);
                        c_timer.start();
                        pauseFlag = false;

                    }

                    else{
                        pauseTime = SystemClock.elapsedRealtime();
                        c_timer.stop();
                        pauseFlag = true;
                    }
                    break;

                case R.id.btnLap:
                    TextView temp = new TextView(DateActivity.this);
                    long checkTime = SystemClock.elapsedRealtime();

                    temp.setText(checkTime+"");
                    lapLinear.addView(temp);
            }
        }
    };
}
