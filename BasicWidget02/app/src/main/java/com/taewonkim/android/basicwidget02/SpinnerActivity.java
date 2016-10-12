package com.taewonkim.android.basicwidget02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {

    String[] data = {"월", "화", "수", "목", "금", "토", "일"};

    Spinner spin;
    TextView spinText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spin = (Spinner)findViewById(R.id.spinner);
        spinText = (TextView)findViewById(R.id.spinnerText);

        // 리스트 계열을 항상 Adapter 가 필요하다!
        // ArrayAdapter 의 선언
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, data);
        spin.setAdapter(adapter);

        // 그냥 기존의 Set On Click Listener 를 쓰게 되면 Spinner 를 누를 때 마다 호출 된다.
        // 따라서 spinner 내의 item 을 접근하기 위해 setOnItemClickListener 를 사용한다.
       spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               spinText.setText(data[position]+" 요일");
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }
}
