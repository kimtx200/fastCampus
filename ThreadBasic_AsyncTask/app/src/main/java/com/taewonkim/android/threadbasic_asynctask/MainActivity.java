package com.taewonkim.android.threadbasic_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button download;
    TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        percent = (TextView) findViewById(R.id.textView);

        progressBar.setProgress(0);
        download = (Button) findViewById(R.id.btnDownload);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Async Task 동작 로직
                new DownloadTask().execute(100);
            }
        });
    }

    //    1      2     3
    class DownloadTask extends AsyncTask<Integer, Integer, Void> {
        // 백그라운드가 시작 할 때 넘겨주는 자료형들 - doInBackground 의 parameter type
        // 메인스레드를 건드릴수 있는 함수 호출 -
        // 백그라운드 작업을 마친 뒤 결과 값을 돌려 받는 것

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 1.
        @Override
        protected Void doInBackground(Integer... params) {

            int max = params[0];
            try {
                for (int i = 0; i < max; i++) {
                    publishProgress();
                    Thread.sleep(100);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        // 2.
        // 이 함수의 내부 만 메인스레드로 여겨진다.
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            percent.setText(values[0]+" %");
        }

        // 3.

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(MainActivity.this, aString, Toast.LENGTH_SHORT).show();
        }

    }
}

