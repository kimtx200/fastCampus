package com.taewonkim.android.rxandroid_basic09_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ted on 2016-11-04.
 */

/*

http://api.openweathermap.org/data/2.5/weather?q=seoul&APPID=3a377ba2e83a798f803109fd62c23334

 */
public interface IWeather {

    @GET("data/2.5/weather")
    Observable<RemoteData> getData(@Query("q") String cityId, @Query("APPID") String key);

}
