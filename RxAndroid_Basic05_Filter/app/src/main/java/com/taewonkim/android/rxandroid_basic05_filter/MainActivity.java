package com.taewonkim.android.rxandroid_basic05_filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;

import static android.R.attr.filter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Integer dataset[] = {1, 2, 3, 1, 4, 5, 3, 6, 7};
    Button btnFilter, btnForeach, btnTake, btnFirst, btnLast, btnDistinct, btnGroupBy;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilter = (Button) findViewById(R.id.btnFilter);
        btnForeach = (Button) findViewById(R.id.btnForeach);
        btnTake = (Button) findViewById(R.id.btnTake);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnLast = (Button) findViewById(R.id.btnLast);
        btnDistinct = (Button) findViewById(R.id.btnDistinct);
        btnGroupBy = (Button) findViewById(R.id.btnGroupBy);

        tv = (TextView) findViewById(R.id.textView);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFilter:
                filter();
                break;

            case R.id.btnForeach:
                foreach();
                break;

            case R.id.btnTake:
                take(3);
                break;

            case R.id.btnFirst:
                first();
                break;

            case R.id.btnLast:
                last();
                break;

            case R.id.btnDistinct:
                distinct();
                break;

            case R.id.btnGroupBy:
                groupBy();
                break;
        }
    }

    // 조건문 걸어주기
    public void filter() {
        Observable.from(dataset)
                // observing 하는 dataset 에 if 문 형식처럼 조건을 걸어 줄 수 있다.
                .filter(item -> item % 2 == 0)
                .subscribe(result -> System.out.println(result+""));
    }

    // 순차적으로 출력
    public void foreach() {
        // observable 의 subscribe == stream 의 forEach
        Observable.from(dataset)
                   .forEach(result -> System.out.println(result));
    }

    // 데이터 셋으로부터 가져올 데이터 개수
    public void take(int count) {
        Observable.from(dataset)
                .take(count)
                .subscribe(result -> System.out.println(result));

    }

    // 첫번째 값
    public void first() {
        Observable.from(dataset)
                .first()
                .subscribe(result -> System.out.println(result));

    }

    // 마지막 값
    public void last() {
        Observable.from(dataset)
                .last()
                .subscribe(result -> System.out.println(result));
    }

    // 정렬
    public void distinct() {
        Observable.from(dataset)
                .distinct()
                .subscribe(result -> System.out.println(result));
    }

    public void groupBy() {
        Observable.from(dataset)
                // 짝수 성분으로 그룹핑
                .groupBy(item -> item%2 == 0)
                .subscribe(grouped -> grouped.toList().subscribe(
                        items -> Log.w("Group By TAG",items+" 짝수 :" + grouped.getKey())
                ));
    }
}
