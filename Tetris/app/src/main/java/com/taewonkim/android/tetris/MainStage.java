package com.taewonkim.android.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by 태원 on 2016-10-17.
 */
public class MainStage extends View {

    Stage stage;
    Block block;

    DisplayMetrics metrics = getResources().getDisplayMetrics();

    final int ground_width = 548;
    final int ground_height = 986;

    final int preview_width = 219;
    final int preview_height = 219;

    final int stage_width = 12;
    final int stage_height = 21;

    final int unit = ground_width / 12;


    public MainStage(Context context, Stage stage, Block block) {
        super(context);
        this.stage = stage;
        this.block = block;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. 스테이지 영역을 모두 그린다.
        // 1.1 스테이지 영역에 해당 블럭이 포함 된 채로 그려 준다.

        // ground
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, ground_width, ground_height, paint);

        // stage
        paint.setColor(Color.BLACK);
        for (int i = 0; i < stage_width; i++) {
            for (int j = 0; j < stage_height; j++) {
                if (stage.stageOne[j][i] != 0)
                    canvas.drawRect(i * unit, j * unit, (i + 1) * unit, (j + 1) * unit, paint);
            }
        }

        // block


        // 1.2 현재 움직이는 블럭을 그린다.

        // 2. 프리뷰 영역을 그린다.
        // preview
        paint.setColor(Color.GRAY);
        canvas.drawRect(ground_width, ground_height, ground_width+preview_width, ground_height+preview_height, paint);

        // 2.1 다음 블럭과 함께 그린다.
    }
}
