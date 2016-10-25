package com.taewonkim.android.remote_httpurlconnection_cookie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.taewonkim.android.remote_httpurlconnection_cookie.Remote.postData;

public class MainActivity extends AppCompatActivity {

    EditText etId;
    EditText etPw;
    Button btnSignIn;
    TextView tvResult;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getApplicationContext().getSharedPreferences("cookie", 0);
        editor = sp.edit();

        setContentView(R.layout.activity_main);

        etId = (EditText) findViewById(R.id.editId);
        etPw = (EditText) findViewById(R.id.editPw);

        tvResult = (TextView) findViewById(R.id.textResult);

        btnSignIn = (Button) findViewById(R.id.button);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        tvResult.setText("User_ID : " + sp.getString("USERID", ""));

    }


    private void signIn() {

        Map userInfo = new HashMap();
        userInfo.put("user_id", etId.getText().toString());
        userInfo.put("user_pw", etPw.getText().toString());


        new AsyncTask<Map, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(Map... params) {
                String result = "";
                String url = "http://192.168.0.173:8080/setCookies.jsp";

                try {
                    result = Remote.postData(url, params[0], "POST");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                // getApplicationContext() - 이 함수를 호출 해 줌으로써 앱내의 모든 액티비티에서 쿠키를 접근 할 수 있도록 함
                // getSharedPreferences() -

                List<HttpCookie> cookies = Remote.cookieManager.getCookieStore().getCookies();
                StringBuffer cookieString = new StringBuffer();

                for (HttpCookie cookie : cookies) {
                    cookieString.append(cookie.getName() + "=" + cookie.getValue() + "\n");
                    editor.putString(cookie.getName(), cookie.getValue());
                }
                editor.commit();
                //tvResult.setText("cookie = "+cookieString);
            }
        }.execute(userInfo);

    }
}


