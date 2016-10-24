package com.taewonkim.android.remote_rest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 태원 on 2016-10-21.
 */

public class Remote {

    private static final String TAG = "REMOTE";

    public static String getData(String webUrl) throws Exception {

        StringBuffer result = new StringBuffer();

        URL url = new URL(webUrl);

        // http connection - webURL 주소에 해당하는 서버와 연결
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // REST API의 종류
        // GET = 조회
        // POST = 입력
        // ==== 밑의 두개는 보안문제로 사용하지 않음 ===
        // DELETE = 삭제
        // PUT = 수정

        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String dataLine = null;
            while ((dataLine = br.readLine()) != null) {
                result.append(dataLine);
            }
            br.close();
        } else {
            Log.i(TAG, "HTTP Error code = " + responseCode);

        }
        return result.toString();
    }

}
