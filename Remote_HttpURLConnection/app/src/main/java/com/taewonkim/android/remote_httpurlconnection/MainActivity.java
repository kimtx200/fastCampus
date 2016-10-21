package com.taewonkim.android.remote_httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getNaver();

            }
        });
    }

    private void getNaver() {

        new AsyncTask<Void, Void, String>(){

            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("다운로드 중입니다.");
                progress.setMessage("Downloading ....");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result ="";

                try {
                    result = Remote.getData("http://openAPI.seoul.go.kr:8088/776a614c616b696d383446746a4468/json/VisitSeoulKr/1/5");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);

                StringBuffer sb = new StringBuffer();

                try {
                    JSONObject json = new JSONObject(s);
                    JSONObject topObject = json.getJSONObject("VisitSeoulKr");
                    JSONArray rows = topObject.getJSONArray("row");

                    int rowsCount = rows.length();
                    for (int i = 0; i < rowsCount; i++) {
                        JSONObject result = (JSONObject) rows.get(i);
                        String system = result.getString("SYSTEMNM");
                        String category = result.getString("CATEGORY4");

                        sb.append(system + " " + category + "\n");
                    }

                    tv.setText(sb.toString());
                    progress.dismiss();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}
