package com.taewonkim.android.rxandroid_basic07_binding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

import static com.jakewharton.rxbinding.view.RxView.clicks;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // rxView Binding
        clicks(findViewById(R.id.btnBind))
                .map(event -> new Random().nextInt())
                .subscribe(
                        rnd -> ((TextView)findViewById(R.id.tvResult)).setText(rnd+"")
                );

        Observable<String> leftObs = RxView.clicks(findViewById(R.id.btnLeft))
                .map(event -> "Left");

        Observable<String> rightObs = RxView.clicks(findViewById(R.id.btnRight))
                .map(event -> "Right");

        // rxView merge
        Observable.merge(leftObs, rightObs)
                .subscribe(
                        text -> Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                );

        RxTextView.textChangeEvents((EditText)findViewById(R.id.tvResult))
                .subscribe(
                        word -> Log.i("SEARCH", "WORD:"+word.text().toString())
                );
    }
}
