package com.taewonkim.android.customview_pushpush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button up;
    Button down;
    Button left;
    Button right;

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

    int[][] map = {
            {3, 1, 0, 0, 0, 1, 1, 1, 1, 3},
            {0, 1, 0, 1, 0, 0, 2, 0, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
            {1, 1, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 2, 1, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 2, 0, 0, 0, 0},
            {3, 1, 1, 1, 0, 1, 1, 1, 0, 3},
    };

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
        }
        else {
            if ((player_x + nextValue) < 0 || (player_x + nextValue) >= GROUND_LIMIT)
                return 0;
        }

        // 장애물 체크
        if (direction.equals("y")) {
            if (map[player_y + nextValue][player_x] == 1)
                return 0;
        }
        else {
            if (map[player_y][player_x + nextValue] == 1)
                return 0;
        }

        return nextValue;
    }

    private class CustomView extends View {

        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            // ground 의 경계선 그리기
            paint.setColor(Color.GRAY);
            canvas.drawRect(0, 0, groundUnit, groundUnit, paint);

            // ground 에 장애물 그리기
            paint.setColor(Color.BLACK);
            for (int i = 0; i < GROUND_LIMIT; i++) {
                for (int j = 0; j < GROUND_LIMIT; j++) {
                    if (map[i][j] == 1)
                        canvas.drawRect(j * unit, i * unit, j * unit + unit, i * unit + unit, paint);
                }
            }

            // ground 에 장애물 그리기
            paint.setColor(Color.MAGENTA);
            for (int i = 0; i < GROUND_LIMIT; i++) {
                for (int j = 0; j < GROUND_LIMIT; j++) {
                    if (map[i][j] == 2)
                        canvas.drawRect(j * unit, i * unit, j * unit + unit, i * unit + unit, paint);
                }
            }

            // 움직일 사각형 그리기
            paint.setColor(Color.RED);
            canvas.drawRect(player_x * unit, player_y * unit, player_x * unit + unit, player_y * unit + unit, paint);

        }
    }


}




