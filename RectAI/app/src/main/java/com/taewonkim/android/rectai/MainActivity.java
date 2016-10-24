package com.taewonkim.android.rectai;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button up;
    Button down;
    Button left;
    Button right;
    Button start;

    FrameLayout ground;
    CustomView customView;

    // 화면 전체 사이즈 가져오기
    private int groundUnit = 0;
    // 한번에 이동할 거리 설정 ( 10X10 ground 만들기 )
    private int unit = 0;
    // 커스텀 뷰(사각형) 의 현재 좌표 값 가져오기
    private int player_x = 0;
    private int player_y = 0;

    private final static int GROUND_LIMIT = 10;

    ArrayList<CustomThread> temps = new ArrayList<>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 화면의 사이즈를 가져오는 시스템 함수
        DisplayMetrics dm = getResources().getDisplayMetrics();
        groundUnit = dm.widthPixels;

        // 10X10 ground 생성을 위한 unit 값 설정
        unit = groundUnit / GROUND_LIMIT;

        up = (Button) findViewById(R.id.btnUp);
        down = (Button) findViewById(R.id.btnDown);
        left = (Button) findViewById(R.id.btnLeft);
        right = (Button) findViewById(R.id.btnRight);

        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        start = (Button) findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomThread customThread = new CustomThread(customView);
                temps.add(customThread);
                temps.get(count).start();
                count++;
            }
        });

        ground = (FrameLayout) findViewById(R.id.ground);

        customView = new CustomView(this);
        ground.addView(customView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUp:
                player_y = player_y + checkCollision("y", -1);
                break;

            case R.id.btnDown:
                player_y = player_y + checkCollision("y", +1);
                break;

            case R.id.btnLeft:
                player_x = player_x + checkCollision("x", -1);
                break;

            case R.id.btnRight:
                player_x = player_x + checkCollision("x", +1);
                break;
        }
        customView.invalidate();
    }

    // 이동 대상(x | y) , 이동 값
    private int checkCollision(String direction, int nextValue) {

        // 외곽선 체크
        if (direction.equals("y")) {
            if ((player_y + nextValue) < 0 || (player_y + nextValue) >= GROUND_LIMIT)
                return 0;
        } else {
            if ((player_x + nextValue) < 0 || (player_x + nextValue) >= GROUND_LIMIT)
                return 0;
        }
        return nextValue;
    }

    public class CustomView extends View {

        Paint paint = new Paint();

        private int x = 0;
        private int y = 0;

        public CustomView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            // ground 의 경계선 그리기
            paint.setColor(Color.GRAY);
            canvas.drawRect(0, 0, groundUnit, groundUnit, paint);

            // Player 사각형 그리기
            paint.setColor(Color.RED);
            canvas.drawRect(player_x * unit, player_y * unit, player_x * unit + unit, player_y * unit + unit, paint);

            paint.setColor(Color.BLACK);
            canvas.drawCircle(x, y, 50, paint);
        }

        public void moveRect(int offset) {

            if (player_x*unit > x)
                x = x + offset;
            else
                x = x - offset;

            if (player_y*unit > y)
                y = y + offset;
            else
                y = y - offset;
        }
    }
}

// 커스텀 뷰의 애니메이션을 Thread 로 구동 되게 하기 위한 클래스 선언
class CustomThread extends Thread {

    // 한번의 변화당 이동하는 거리 설정 = offset
    private static final int OFFSET = 3;
    MainActivity.CustomView customView;

    public CustomThread(MainActivity.CustomView customView) {
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
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

