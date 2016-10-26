package com.taewonkim.android.remote_retrofitwithokttp;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.http.Url;

/**
 * Created by 태원 on 2016-10-26.
 */

public class RestAdapter {

    // TIMEOUT 시간을 정해 서버와 연결을 유지하는 시간을 미리 설정해 둠.
    public static final int CONNECT_TIMEOUT = 15;
    public static final int WRITE_TIMEOUT = 5;
    public static final int READ_TIMEOUT = 5;

    private static final String SERVER_URL = "http://openapi.gangseo.seoul.kr:8088/";
    private static OkHttpClient client;
    private static ISeoulOpenData service;

    public synchronized static ISeoulOpenData getInstance(){

        if(service == null){
            // 통신 로그를 확인하기 위한 interceptor 설정
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // 쿠키 매니저 설정
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            // OK HTTP 설정
            // SSL을 통한 서버 연결인 경우 인증서가 없으면 통신 자체가 안된다.
            // 이 경우, 회피하기 위해 인증서를 무시하는 세팅을 해 줄 수 있다.


        }

        return service;
    }

}
