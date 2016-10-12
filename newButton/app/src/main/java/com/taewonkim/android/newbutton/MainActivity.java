package com.taewonkim.android.newbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    Button btnNew;
    Button btnCreate;
    GridLayout grid;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = (Button)findViewById(R.id.btnCreate);
        grid = (GridLayout)findViewById(R.id.grid);
    }

    public void newButton(View v){
        btnNew = new Button(this);
        count = count + 1;
        btnNew.setText(count+"");
        grid.addView(btnNew);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid.removeView(v);
            }
        });

    }
}
