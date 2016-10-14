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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = new CustomView(this);
        FrameLayout ground = (FrameLayout) findViewById(R.id.ground);

        ground.addView(customView);

        Button clear = (Button) findViewById(R.id.btnClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.reset();
            }
        });

    }
}

class CustomView extends View {

    int x = -1;
    int y = -1;
    int rad = 100;

    Paint paint = new Paint();
    Path path = new Path();

    public CustomView(Context context) {
        super(context);

        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);

    }

    // Path 를 초기화 하는 메소드
    public void reset(){
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

        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        x = (int)event.getX();
        y = (int)event.getY();

        switch (event.getAction()) {

            // 눌렀다가 떼었을때 동작
            case MotionEvent.ACTION_UP:
                // 전역 변수에 클릭한 좌표를 전달

                break;

            case MotionEvent.ACTION_DOWN:

                path.moveTo(x,y);
                break;

            case MotionEvent.ACTION_MOVE:

                path.lineTo(x,y);
                break;
        }

        // 새로운 Action 값이 들어왔을 때, draw를 새롭게 그려준다.
        invalidate();
        return true;
    }
}
