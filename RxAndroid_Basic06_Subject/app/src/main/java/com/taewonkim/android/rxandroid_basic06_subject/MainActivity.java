package com.taewonkim.android.rxandroid_basic06_subject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPublish, btnBehavior, btnReplay, btnAsync, btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPublish = (Button) this.findViewById(R.id.btnPublish);
        btnBehavior = (Button) findViewById(R.id.btnBehavior);
        btnReplay = (Button) findViewById(R.id.btnReplay);
        btnAsync = (Button) findViewById(R.id.btnAsync);
        btnComplete = (Button) findViewById(R.id.btnComplete);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPublish:
                publish();
                break;

            case R.id.btnBehavior:
                behavior();
                break;

            case R.id.btnReplay:
                replay();
                break;

            case R.id.btnAsync:
                async();
                break;

            case R.id.btnComplete:
                complete();
                break;

        }
    }

    // 구독한 시점부터 발행한 아이템들을 받는다
    public void publish() {
        PublishSubject<String> subject = PublishSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(item -> Log.e("Publish","item="+item));

        subject.onNext("D");
        subject.onNext("E");

    }

    // 가장 최근에 관찰된 아이템부터 구독한다.
    public void behavior() {

        BehaviorSubject<String> subject = BehaviorSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(item -> Log.e("Behavior","item="+item));

        subject.onNext("D");
        subject.onNext("E");

    }

    public void replay() {
        ReplaySubject<String> subject = ReplaySubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(item -> Log.e("Replay","item="+item));

        subject.onNext("D");
        subject.onNext("E");
    }

    // 발행의 처음과 끝이 명확 할 때 Aysnc Task 를 사용 할 수 있음.
    public void async() {

        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(item -> Log.e("Async","item="+item));

        subject.onNext("D");
        subject.onNext("E");

    }

    // 가장 마지막 item 만 compelete 가 호출된 시점에 반환 해 준다.
    public void complete() {

        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(item -> Log.e("Async","item="+item));

        subject.onNext("D");
        subject.onNext("E");
        subject.onCompleted();
    }
}
