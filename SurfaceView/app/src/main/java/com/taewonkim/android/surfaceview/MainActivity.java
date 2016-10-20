package com.taewonkim.android.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    int deviceWidth = 0;
    int deviceHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 메인액티비티가 생성 되었을 때, main xml 을 뿌려주지 않도록 한다.
        //setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceHeight = metrics.heightPixels;
        deviceWidth = metrics.widthPixels;

        CustomSurfaceView surfaceView = new CustomSurfaceView(this);
        setContentView(surfaceView);

    }

    // 속도가 빠르다 . why??
    public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {


        private SurfaceThread thread;

        public CustomSurfaceView(Context context) {
            super(context);

            getHolder().addCallback(this);
            thread = new SurfaceThread(getHolder());
            thread.setDaemon(true);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        // 뷰가 화면에 보여지는 시점
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread.running = true;
            thread.start();
        }

        // 뷰에 변경사항이 생겼을 때 호출
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        // 화면에서 뷰가 보이지 않는 시점점
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            thread.running = false;

            while (retry) {
                // 서브스레드를 메인스레드와 함께 동기화 시켜준다.
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 무한 반복하면서 위에서 정의 한 surface 뷰에 그림을 그려주는 역할을 한다.
    public class SurfaceThread extends Thread {

        private SurfaceHolder surfaceHolder;
        public boolean running = true;

        Paint paint = new Paint();
        int x = 0;
        int y = 0;

        public SurfaceThread(SurfaceHolder surfaceHolder) {
            // surfaceView 에서 넘겨준 Holder 를 가지고 작업을 한다.

            this.surfaceHolder = surfaceHolder;

        }

        @Override
        public void run() {
            // 무한 반복 하면서 그림을 그려 준다.
            while (true) {
                Canvas canvas = null;
                try {
                    // 1. canvas 를 가져온다.
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        // 2. canvas 을 이용해 사각형을 그려 준다.
                        paint.setColor(Color.WHITE);
                        canvas.drawRect(x - 1, y - 1, x + 50 - 1, y + 50 - 1, paint);

                        paint.setColor(Color.BLUE);
                        canvas.drawRect(x, y, x + 50, y + 50, paint);
                    }

                    x = x + 1;
                    y = y + 1;

                    if (x > deviceWidth) {
                        x = 0;
                        y = 0;
                    }
                    // 여기서 lock 을 풀어 주면서 실제 디스플레이에 그려주게 된다.

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 에러가 나더라도 canvas 의 결과 값을 항상 보여 주도록 한다.
                finally {
                    if (canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
