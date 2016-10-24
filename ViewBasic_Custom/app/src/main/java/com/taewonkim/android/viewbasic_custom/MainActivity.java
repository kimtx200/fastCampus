package com.taewonkim.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    CustomView customView;
    FrameLayout ground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // custom view, Frame Layout 을 생성
        customView = new CustomView(this);
        ground = (FrameLayout) findViewById(R.id.ground);

        // frame layout 에 custom view 삽입
        ground.addView(customView);

        // Clear Button 구현 및 클릭 이벤트 구현
        Button clear = (Button) findViewById(R.id.btnClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.reset();
            }
        });

        // animate Button 구현 및 클릭 이벤트 구현
        Button animate = (Button) findViewById(R.id.btnAnimate);
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomThread customThread = new CustomThread(customView);
                customThread.start();
            }
        });

    }
}

class CustomView extends View {

    // 사각형의 초기 값
    private final static int WIDTH = 100;
    private final static int HEIGHT = 100;

    // 사각형의 변동 값(이동 값)
    private int x = 0;
    private int y = 0;

    Paint paint = new Paint();
    Path path = new Path();

    public CustomView(Context context) {
        super(context);

        paint.setColor(Color.MAGENTA);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10f);

    }

    // Path 를 초기화 하는 메소드
    public void reset() {
        path = new Path();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        // canvas = 붓
        // paint = 물감

        // left, top, right, botton 을 순서대로 집어 넣어 준다.

        // left 와 right 는 x좌표, top 과 bottom 은 y좌표를 나타낸다.
//        paint.setColor(Color.MAGENTA);
//        canvas.drawRect(0,0,300,300,paint);

//        paint.setColor(Color.BLUE);
//        // x좌표, y좌표, 지름
//
//        if (x >= 0 || y >= 0)
//            canvas.drawCircle(x, y, rad, paint);

//        canvas.drawPath(path, paint);

        canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, paint);
    }

    public void moveRect(int offset) {
        x = x + offset;
        y = y + offset;
    }
}

// 커스텀 뷰의 애니메이션을 Thread 로 구동 되게 하기 위한 클래스 선언
class CustomThread extends Thread {

    CustomView customView;
    // 한번의 변화당 이동하는 거리 설정 = offset
    private static final int OFFSET = 2;


    public CustomThread(CustomView customView) {
        this.customView = customView;
    }

    @Override
    public void run() {
        int limit = 0;
        while (limit < 1000) {
            // customView 에 그려지는 사각형의 좌표값을 조작 한다.
            customView.moveRect(OFFSET);
            customView.postInvalidate();
            limit = limit + 1;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
