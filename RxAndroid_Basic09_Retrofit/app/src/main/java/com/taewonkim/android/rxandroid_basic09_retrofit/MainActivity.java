package com.taewonkim.android.rxandroid_basic09_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText etCity;

    static final String BASE_URL = "http://api.openweathermap.org";
    static final String API_KEY = "3a377ba2e83a798f803109fd62c23334";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
        etCity = (EditText) findViewById(R.id.etCity);

        Button btnGet = (Button) findViewById(R.id.btnGet);
        btnGet.setOnClickListener(event -> getWeather());
    }

    public void getWeather(){
        String cityName = etCity.getText().toString();

        // 1. Retrofit Client 생성하기
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                    .build();

        // 2. API SERVICE 생성
        IWeather service = retrofit.create(IWeather.class);

        // 3. DATA Observer 를 생성
        Observable<RemoteData> weatherData = service.getData(cityName, API_KEY);

        // 4. Scriber 를 생성
        //  1) Scribe 데이터를 가져오는 대상 (Observable) 은 new Thread 로 새로운 Thread 에서 작업하고
        //  2) 가져온 데이터를 화면에 세팅해 주는 Observer 는 MainThread 에서 작업한다.
        weatherData.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            data -> {
                                String temp ="";

                                temp += "ID : " + data.getId() +"\n";
                                temp += "NAME : " + data.getName()+"\n";
                                temp += "BASE : " + data.getBase();

                                tvResult.setText(temp);
                            }
                    );
    }
}
