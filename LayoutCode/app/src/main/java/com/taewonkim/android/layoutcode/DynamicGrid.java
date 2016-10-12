package com.taewonkim.android.layoutcode;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class DynamicGrid extends AppCompatActivity {

    Button btnCreate;
    GridLayout grid;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnCreate = (Button)findViewById(R.id.btnCreate);
        grid = (GridLayout)findViewById(R.id.grid);

        setContentView(R.layout.activity_dynamic_grid);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button newBtn = new Button(DynamicGrid.this);
                count++;
                newBtn.setText("HI");
                grid.addView(newBtn);

            }
        });
    }


}
