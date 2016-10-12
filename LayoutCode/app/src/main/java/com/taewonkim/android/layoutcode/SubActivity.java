package com.taewonkim.android.layoutcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout_main = new RelativeLayout(this);
        LinearLayout linearLayout1 = new LinearLayout(this);

        relativeLayout_main.addView(linearLayout1);

        TextView textView1 = new TextView(this);
        textView1.setText("Funny!! :)");

        linearLayout1.addView(textView1);
        setContentView(relativeLayout_main);
    }
}
