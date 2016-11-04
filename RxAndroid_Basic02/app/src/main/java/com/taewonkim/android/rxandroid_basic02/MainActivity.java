package com.taewonkim.android.rxandroid_basic02;

import android.content.Context;
import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

import static com.taewonkim.android.rxandroid_basic02.R.id.textView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    Button btnJust;
    Button btnFrom;
    Button btnDefer;

    TextView textView;

    ArrayAdapter<String> adapter;
    ArrayList<String> database = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        listView = (ListView) findViewById(R.id.listView);

        btnJust = (Button) findViewById(R.id.btnJust);
        btnJust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doJust();
            }
        });

        btnFrom = (Button) findViewById(R.id.btnFrom);
        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doFrom();
            }
        });

        btnDefer = (Button) findViewById(R.id.btnDefer);
        btnDefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDefer();
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, database);
        listView.setAdapter(adapter);
    }

    public void doJust() {
        rx.Observable<String> observable = rx.Observable.just("Dog");

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        });

    }

    public void doFrom() {
        rx.Observable<String> observable = rx.Observable.from(new String[]{"Dog", "Bird", "Chicken", "Horse", "Rabbit", "Tiger"});

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                database.add(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    // 지연처리 함수 실행(몇 초 뒤에 실행하도록 해라!!!!)
    // 호출 할 때마다 옵저버 객체를 매번 생성 해야한다.
    public void doDefer() {
        rx.Observable<String> observable = rx.Observable.defer(new Func0<rx.Observable<String>>() {
            @Override
            public rx.Observable<String> call() {
                return rx.Observable.just("Bird");
            }
        });

        observable.delaySubscription(1, TimeUnit.SECONDS).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

            }
        });
    }

}
