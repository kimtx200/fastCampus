package com.taewonkim.android.remote_retrofitwithokttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callHttp();
    }

    public void callHttp(){

        String key = "48626469646b696d3639426f657459";
        String serviceName = "GangseoTheaterInfo";

        // api 내의 데이터셋의 시작점 설정
        int begin = 1;
        // api 내의 데이터셋을 한번에 가져올 양을 설정 (한번에 최대 1000건까지 불러오기 가능)
        int end = 10;

        retrofit2.Call<RemoteData> remoteData = RestAdapter.getInstance().getData(key, serviceName, begin, end);
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if(response.isSuccessful()){
                    RemoteData data = response.body();
                    List<Row> rows = data.getGangseoTheaterInfo().getRow();
                    for(Row row : rows)
                        Log.d("TEST",row.CLG_STDT);

                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {

            }
        });
    }
}
