package com.taewonkim.android.layoutcode;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. "layout" 이라는 이름으로 Relative Layout 생성

/*
        // 2. "layout" 안에 원하는 Component 삽입
        // 2.1 버튼(btn1) 생성
        Button btn1 = new Button(this);
        // 2.1.1 버튼(btn)에 대한 속성 값 결정
        btn1.setText("TEST BUTTON"); // 버튼 텍스트 설정
        btn1.setBackgroundColor(Color.BLUE); // 버튼 배경색 설정
        // 2.2 생성한 btn1 을 layout 에 삽입한다.
        layout.addView(btn1);
*//*
        Button btn2 = new Button(this);
        btn2.setText("Open DynamicGridView");
        layout.addView(btn2);

        setContentView(layout);
*//*
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DynamicGrid.class);
                startActivity(intent);
            }
        });*/
/*
        // 3. Layout 속성 설정
        // 3.1 레이아웃을 control 할 수 있는 객체 생성 - 이때 레이아웃의 적용 범위를 설정 할 수 있다.
        RelativeLayout.LayoutParams btn1Param =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                                //↑ layout_width                            ↑ layout_height
        // 3.2 레이아웃 내의 Component 들의 gravity 를 제어 하는 함수
        btn1Param.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btn1Param.addRule(RelativeLayout.CENTER_VERTICAL);

        // 컴포넌트 정의를 완료한(넣을거 다 넣은) layout 을 보여준다
        setContentView(layout);
/*
        // 버튼의 클릭 이벤트 설정
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });
*/
    }

    public void openGrid(View v){
        Intent intent = new Intent(MainActivity.this, DynamicGrid.class);
        startActivity(intent);
    }

}
