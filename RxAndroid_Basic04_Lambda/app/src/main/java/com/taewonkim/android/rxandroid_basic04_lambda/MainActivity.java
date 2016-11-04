package com.taewonkim.android.rxandroid_basic04_lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLambda, btnMap, btnFlatMap, btnZip;
    TextView textView;
    ListView listView;

    ArrayAdapter<String> adapter;
    ArrayList<String> database = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLambda = (Button) findViewById(R.id.btnLambda);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnFlatMap = (Button) findViewById(R.id.btnFlatMap);
        btnZip = (Button) findViewById(R.id.btnZip);

        btnLambda.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnFlatMap.setOnClickListener(this);
        btnZip.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, database);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLambda:
                doLambda();
                break;

            case R.id.btnMap:
                doMap();
                break;

            case R.id.btnFlatMap:
                doFlatMap();
                break;

            case R.id.btnZip:
                doZip();
                break;
        }
    }

    public void doLambda() {
        Observable<String> observable = Observable.just("I am Lambda");
        observable.subscribe(
                item -> textView.setText(item)
        );
    }

    // Stream 구현 해 보기
    // Collection 을 받아오는 과정 중의 데이터들을 control 할 수 있는 메소드를 생성해주는 형태
    public void doMap() {
        Observable.from(new String[]{"Dog", "Bird", "Chicken", "Horse", "Rabbit", "Tiger"})
                .map(item -> "["+item+"]")
                .subscribe(
                        item -> Log.i("TAG", item),
                        error -> error.printStackTrace(),
                        () -> adapter.notifyDataSetChanged()
                );
    }

    public void doFlatMap() {
        Observable.from(new String[]{"Dog", "Bird", "Chicken", "Horse", "Rabbit", "Tiger"})
                .flatMap(item -> Observable.from(new String[]{"name:"+item, item.getBytes()+""}))
                .subscribe(
                        item -> Log.i("TAG", item),
                        e -> e.printStackTrace(),
                        () -> adapter.notifyDataSetChanged()
                );
    }

    // 합성과 관련된 Operator
    // 여러개의 Observer 를 합성 시켜주는 ...
    public void doZip(){
        Observable.zip(
                Observable.just("Kim Tae Won"),
                Observable.just("image.jpg"),
                (item1, item2) -> "Name:"+item1+", Progile image:"+item2)
                .subscribe(zipped -> Log.w("ZIP TAG","onNext item"+zipped)
        );
    }
}
