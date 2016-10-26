package com.taewonkim.android.remote_retrofitwithokttp;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 태원 on 2016-10-26.
 */

public interface ISeoulOpenData {

    // 리턴 받는 파일의 타입
    // retrofit 을 이용 할 땐 중괄호{} 안에 변수로 쓰일 것들을 넣어준다.
    // query(?) 방식과 pass(변수날리기) 방식의 차이점
    @GET("/{key}/json/{serviceName}/{begin}/{end}/")
    retrofit2.Call<RemoteData> getData(@Path("key") String key, @Path("serviceName") String serviceName, @Path("begin") int begin, @Path("end") int end);

}
