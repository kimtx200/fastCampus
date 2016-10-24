package com.taewonkim.android.remote_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> database = new ArrayList<>();
    ListView listView;
    Button getList;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomAdapter(this);
        listView.setAdapter(adapter);

        getList = (Button) findViewById(R.id.btnGetList);
        getList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void getData() {

        new AsyncTask<Void, Void, String>() {

//            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

//                progress = new ProgressDialog(MainActivity.this);
//                progress.setTitle("다운로드 중입니다.");
//                progress.setMessage("Downloading ....");
//                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                progress.setCancelable(false);
//                progress.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result = "";

                try {
                    result = Remote.getData("http://192.168.0.173:8080/sub/request.jsp");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                super.onPostExecute(jsonString);

                try {
                    JSONObject json = new JSONObject(jsonString);
                    JSONArray rows = json.getJSONArray("root");

                    int rowsCount = rows.length();
                    for (int i = 0; i < rowsCount; i++) {
                        JSONObject result = (JSONObject) rows.get(i);
                        String value = result.getString("key");
                        database.add(value);
                    }
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public static String postData(String webUrl, Map params) throws IOException {

        String result = "";

        URL url = new URL(webUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        // POST 처리 일 경우만 -------------------------------------------------

        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();

        ArrayList<String> keySet = new ArrayList<>(params.keySet());
        for (String key : keySet) {
            String param = key + "=" + params.get(key);
            os.write(param.getBytes());
        }
        os.flush();
        os.close();

        // ----------------------------------------------------------------------

        return result;

    }

    class CustomAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public CustomAdapter(Context context) {
            this.inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return database.size();
        }

        @Override
        public Object getItem(int position) {
            return database.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String data = database.get(position);

            convertView = inflater.inflate(R.layout.list_item, null);
            TextView tv = (TextView) convertView.findViewById(R.id.textValue);
            tv.setText(data.toString());

            return convertView;
        }
    }
}
