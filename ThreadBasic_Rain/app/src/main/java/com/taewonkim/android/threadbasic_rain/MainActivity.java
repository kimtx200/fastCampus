package com.taewonkim.android.threadbasic_rain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static boolean running = true;
    public static int device_width = 0;
    public static int device_height = 0;

    Button start;
    Button stop;
    FrameLayout ground;
    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        device_width = metrics.widthPixels; // 화면 가로 픽셀 수
        device_height = metrics.heightPixels;   // 화면 세로 픽셀 수

        start = (Button) findViewById(R.id.btnStart);
        stop = (Button) findViewById(R.id.btnStop);
        ground = (FrameLayout) findViewById(R.id.ground);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

        cv = new CustomView(this);
        ground.addView(cv);
    }

    // 애니메이션 구현 클래스
    class CustomView extends View {

        ArrayList<RainDrop> rainDrops = new ArrayList<>();
        Paint paint = new Paint();

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for (RainDrop r : rainDrops) {
                canvas.drawCircle(r.x,r.y,r.size,null);
            }
        }

        public CustomView(Context context) {
            super(context);
            paint.setColor(Color.BLUE);

        }

        public void add(RainDrop rd) {
            rainDrops.add(rd);
        }
    }

    // 떨어지는 빗방울을 그려줄 클래스 (1개 단위)
    class RainDrop implements Runnable {

        // 하나의 빗방울이 가질 수 있는 속성 값 정의
        int x, y;
        int size, size_limit;
        int speed, speed_limit;

        public RainDrop() {

            Random rnd = new Random();

            x = rnd.nextInt(device_width);
            y = 0;

            size_limit = device_width / 20;
            size = rnd.nextInt(size_limit);

            speed_limit = device_height / 200;
            speed = rnd.nextInt(speed_limit);
        }

        @Override
        public void run() {
            // 화면 밖으로 벗어나면 해당 빗방울 Thread 는 종료 된다.
            while (y <= device_height) {
                y = y + speed;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Thread 를 인터페이스 형태로 표현! (화면을 그려주는 Thread)
    // new 를 할 때, Custom View 를 받는 역할
    // 계속해서 화면을 refresh 해 준다.
    class CallDraw implements Runnable {

        CustomView cv;
        int interval;

        public CallDraw(CustomView cv, int interval) {
            this.cv = cv;
            this.interval = interval;
            running = true;
        }

        @Override
        public void run() {
            cv.postInvalidate();

            // interval에 입력된 값 만큼 쉰 후에 화면을 반복해서 그려준다.
            while (running) {
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
