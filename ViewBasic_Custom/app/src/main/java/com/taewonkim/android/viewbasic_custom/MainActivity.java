package com.taewonkim.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(0,0,300,300,paint);
    }
}
