package com.taewonkim.android.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

            Button btnSend1;
            EditText et;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                et = (EditText)findViewById(R.id.editText1);
            }

            public void send(View v){
                Intent intent = new Intent(this, SubActivity.class);

                String str = et.getText().toString();
                intent.putExtra("key1", str);
        intent.putExtra("key2", "김태원");
        intent.putExtra("key3", 27);

        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }







    /*
    public String eval(String str){
        String result = "";
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        try {
            result = engine.eval(str).toString();
        } catch(ScriptException e){
            e.printStackTrace();
        }
        return result;
    }*/
}
