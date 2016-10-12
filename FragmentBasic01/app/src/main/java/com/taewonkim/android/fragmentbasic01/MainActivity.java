package com.taewonkim.android.fragmentbasic01;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        goOne();
                        break;
                    case R.id.btn2:
                        goTwo();
                        break;
                }
            }
        });
    }

    public void goOne() {
        FragmentManager manager = getSupportFragmentManager();

        // 프레그먼트 스택에 해당 프레그먼트를 집어 넣겠다
        FragmentTransaction transaction = manager.beginTransaction();

        // 프레그먼트는 액티비티가 실행 될 때 한번만 생성해 주도록 한다.
        transaction.replace(R.id.fragment, new FragmentOne());
        // 뒤로가기 역할 - 트랜잭션 로그를 저장 하는 역할 이다.
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goTwo() {
        FragmentManager manager = getSupportFragmentManager();

        // 프레그먼트 스택에 해당 프레그먼트를 집어 넣겠다
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, new FragmentTwo());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
