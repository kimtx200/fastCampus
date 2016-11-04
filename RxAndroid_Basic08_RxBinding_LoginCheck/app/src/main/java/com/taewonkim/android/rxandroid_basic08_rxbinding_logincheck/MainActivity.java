package com.taewonkim.android.rxandroid_basic08_rxbinding_logincheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

import static com.jakewharton.rxbinding.widget.RxTextView.textChangeEvents;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        Observable<TextViewTextChangeEvent> idObs = RxTextView.textChangeEvents((EditText) findViewById(R.id.etId));
        Observable<TextViewTextChangeEvent> pwObs = RxTextView.textChangeEvents((EditText) findViewById(R.id.etPw));

        Observable.combineLatest(idObs, pwObs,
                (idChanges, pwChanges) -> {
                    boolean idCheck = (idChanges.text().length() >= 5);
                    boolean pwCheck = (pwChanges.text().length() >= 8);

                    return idCheck && pwCheck;

                })
                .subscribe(
                        checkFlag -> btnSignIn.setEnabled(checkFlag)
                );
    }
}
