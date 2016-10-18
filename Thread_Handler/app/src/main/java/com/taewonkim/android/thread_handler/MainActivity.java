package com.taewonkim.android.thread_handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button start;
    Button call;
    Button startHandler;

    SubThread subThread;
    LooperHandler handlerThread;

    int sum = 10;

    public void run(){

        try{
            for(int i=0; i<20; i++){
                sum= sum+i;
                Thread.sleep(300);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    // 반복 횟수가 제한 적이고 종료시점이 명확한 경우 unsync thread
    // 무한 반복일 경우 thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        start = (Button) findViewById(R.id.btnStart);
        call = (Button) findViewById(R.id.btnCall);
        subThread = new SubThread(handler);

        handlerThread = new LooperHandler(this, "Looper Handler");


        startHandler = (Button) findViewById(R.id.btnStartHandler);
        startHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.start();
                run();
                handlerThread.hideProgress();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subThread.start();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subThread.printLog();
            }
        });
    }

    Handler handler = new Handler() {

        // on Click 메소드 처럼 호출이 되었을 때 특정 액션을 수행한다.
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    int result = msg.arg1;
                    textView.setText("Result : " + result);
                    break;
            }
        }
    };

    // Looper 가 필요한 Thread 가 아예 구현이 되어 있는 Thread
    class LooperHandler extends HandlerThread {

        public static final int SHOW_PROGRESS = 1;
        public static final int HIDE_PROGRESS = 2;
        Handler looperHandler;
        Context context;

        ProgressDialog progress;

        public LooperHandler(Context context, String name) {
            super(name);
            this.context = context;
        }

        // 일반 Thread 에서 Looper.prepare(); 역할을 해 준다.
        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
//            looperHandler = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//
//                    switch (msg.what) {
//                        case SHOW_PROGRESS:
//                            showProgress();
//                            break;
//
//                        case HIDE_PROGRESS:
//                            hideProgress();
//                            break;
//
//                    }
//                }
//            };

            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Progress Bar Title");
            progress.setMessage("check");
            progress.setCancelable(false);

            Log.i("Looper Handler", "show progress!!");
            progress.show();
        }

        private void showProgress() {

        }

        private void hideProgress() {
            Log.i("Looper Handler", "hide progress!!");
            textView.setText(sum+"");
            progress.dismiss();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            quit();
        }
    }
}

class SubThread extends Thread {
    Handler mainHandler;
    Handler subHandler;

    public SubThread(Handler mHandler) {
        mainHandler = mHandler;
    }

    //Looper 실습 - 메세지를 반복적으로 받아 오기 위한
    public void run() {

//            int sum = 0;
//            for (int i = 0; i < 100; i++)
//                sum = sum + i;
//
//            Message msg = new Message();
//            msg.what = 1;
//            msg.arg1 = sum;
//            mainHandler.sendEmptyMessage(1);
        // Looper 를

        Looper.prepare();
        Log.i("SubThread", "after loop prepare");
        subHandler = new Handler() {

        };
        Log.i("SubThread", "before loop");

    }

    public void printLog() {
        Log.i("SubThread", "called from the main Thread");
    }
}

