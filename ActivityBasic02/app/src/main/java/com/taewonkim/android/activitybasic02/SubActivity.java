package com.taewonkim.android.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SubActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et = (EditText)findViewById(R.id.editText2);

        Intent intent = getIntent();
        String str = intent.getStringExtra("key1");

        et.setText(str);
    }

    public void back(View v){
            // activity on destroy
            finish();
        }
}
