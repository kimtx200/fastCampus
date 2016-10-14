package com.taewonkim.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new CustomView(this));
    }
}

class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        // canvas = 붓
        // paint = 물감
        Paint paint = new Paint();

        // left, top, right, botton 을 순서대로 집어 넣어 준다.
        // left 와 right 는 x좌표, top 과 bottom 은 y좌표를 나타낸다.
//        paint.setColor(Color.MAGENTA);
//        canvas.drawRect(0,0,300,300,paint);

        paint.setColor(Color.BLUE);
        // x좌표, y좌표, 지름

        if (x >= 0 || y >= 0)
            canvas.drawCircle(x, y, rad, paint);
    }


    int x = -1;
    int y = -1;
    int rad = 100;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // 눌렀다가 떼었을때 동작
            case MotionEvent.ACTION_UP:
                // 전역 변수에 클릭한 좌표를 전달
                x = (int)event.getX();
                y = (int)event.getY();

                // 새로운 Action 값이 들어왔을 때, draw를 새롭게 그려준다.
                invalidate();

        }


        return super.onTouchEvent(event);
    }
}
