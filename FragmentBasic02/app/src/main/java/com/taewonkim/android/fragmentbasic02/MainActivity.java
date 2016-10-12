package com.taewonkim.android.fragmentbasic02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        setOne();
    }

    // Frame Layout 에 동적으로 Fragment 를 올려주기 위해
    // JAVA CODE 로 직접 구현 한다.
    public void setOne() {
        FragmentManager manager = getSupportFragmentManager();

        // 프레그먼트 스택에 해당 프레그먼트를 집어 넣겠다
        FragmentTransaction transaction = manager.beginTransaction();
        // 프레그먼트는 액티비티가 실행 될 때 한번만 생성해 주도록 한다.
        transaction.replace(R.id.fragment, fragmentOne);
        // 뒤로가기 역할 - 트랜잭션 로그를 저장 하는 역할 이다.
        //transaction.addToBackStack(null);
        // StartActivity 와 같은 역할
        transaction.commit();

    }

    public void goOne() {
        FragmentManager manager = getSupportFragmentManager();

        // 프레그먼트 스택에 해당 프레그먼트를 집어 넣겠다
        FragmentTransaction transaction = manager.beginTransaction();
        // 프레그먼트는 액티비티가 실행 될 때 한번만 생성해 주도록 한다.
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.fragment, fragmentOne);
        // 뒤로가기 역할 - 트랜잭션 로그를 저장 하는 역할 이다.
        //transaction.addToBackStack(null);
        // StartActivity 와 같은 역할
        transaction.commit();
    }

    public void goTwo() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.fragment, fragmentTwo);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}
