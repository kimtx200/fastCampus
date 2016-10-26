package com.taewonkim.android.remote_okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "48626469646b696d3639426f657459";
        String serviceName = "GangseoTheaterInfo";

        // api 내의 데이터셋의 시작점 설정
        int begin = 1;
        // api 내의 데이터셋을 한번에 가져올 양을 설정 (한번에 최대 1000건까지 불러오기 가능)
        int end = 10;

        String url = "http://openapi.gangseo.seoul.kr:8088/" + key + "/json/" + serviceName + "/" + begin + "/" + end + "/";
        callHttp(url);

    }

    // httpUrlConnection 의 로직 구현
    public void callHttp(String url){
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... params) {
                String result = "";

                try {
                    result = getData(params[0]);
                }catch(Exception e){
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Log.e("Result", result);
            }
        }.execute(url);
    }

    public String getData(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        // client 에 넘겨 줄 request 객체를 생성
        Request request = new Request.Builder().url(url).build();

        // server 로 부터 response 를 받음
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
