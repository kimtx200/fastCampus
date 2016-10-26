package com.taewonkim.android.remote_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.R.attr.key;

/*

    @ open API 인증 키
    - 48626469646b696d3639426f657459

    @ 강서구 공연장 조회 API
    - http://openapi.gangseo.seoul.kr:8088//(인증키)/json/GangseoTheaterInfo/1/5

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "48626469646b696d3639426f657459";
        String serviceName = "GangseoTheaterInfo";

        // api 내의 데이터셋의 시작점 설정
        int begin = 1;
        // api 내의 데이터셋을 한번에 가져올 양을 설정 (한번에 최대 1000건까지 불러오기 가능)
        int end = 10;

        String url = "http://openapi.gangseo.seoul.kr:8088/" + key + "/json/" + serviceName + "/" + begin + "/" + end + "/";

        // 외부 json file 을 내부 객체로 convert 하는 과정
        // 1. retrofit client 생성
        Retrofit client = new Retrofit.Builder().baseUrl("http://openapi.gangseo.seoul.kr:8088/").addConverterFactory(GsonConverterFactory.create()).build();

        // 2. retrofit client 에서 사용할 interface 지정
        ISeoulOpenData service = client.create(ISeoulOpenData.class);

        // 3. 생성한 interface 를 통해서 데이터를 호출한다.
        retrofit2.Call<RemoteData> remoteData = service.getData(key, serviceName, begin, end);

        // 4. 비동기 데이터를 받기 위한 리스너 생성
        remoteData.enqueue(new Callback<RemoteData>() {

            // 비동기 데이터를 받아 현재 프로젝트에 처리하는 부분
            @Override
            public void onResponse(retrofit2.Call<RemoteData> call, Response<RemoteData> response) {
                if (response.isSuccessful()) {
                    RemoteData data = response.body();
                    for (Row row : data.getGangseoTheaterInfo().getRow()) {
                        Log.i("TEST", row.TRNM_NM);
                    }
                } else {
                    Log.e("RemoteData", response.message());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

interface ISeoulOpenData {

    // 리턴 받는 파일의 타입
    // retrofit 을 이용 할 땐 중괄호{} 안에 변수로 쓰일 것들을 넣어준다.
    // query(?) 방식과 pass(변수날리기) 방식의 차이점
    @GET("/{key}/json/{serviceName}/{begin}/{end}/")
    retrofit2.Call<RemoteData> getData(@Path("key") String key, @Path("serviceName") String serviceName, @Path("begin") int begin, @Path("end") int end);

}

// 데이터셋의 레코드 하나하나를 객체로 받아 올 클래스 선언
class RemoteData {
    private GangseoTheaterInfo GangseoTheaterInfo;

    public GangseoTheaterInfo getGangseoTheaterInfo() {
        return GangseoTheaterInfo;
    }

    public void setGangseoTheaterInfo(GangseoTheaterInfo GangseoTheaterInfo) {
        this.GangseoTheaterInfo = GangseoTheaterInfo;
    }

    @Override
    public String toString() {
        return "ClassPojo [GangseoTheaterInfo = " + GangseoTheaterInfo + "]";
    }
}

class GangseoTheaterInfo {
    private RESULT RESULT;

    private String list_total_count;

    private List<Row> row;

    public RESULT getRESULT() {
        return RESULT;
    }

    public void setRESULT(RESULT RESULT) {
        this.RESULT = RESULT;
    }

    public String getList_total_count() {
        return list_total_count;
    }

    public void setList_total_count(String list_total_count) {
        this.list_total_count = list_total_count;
    }

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "ClassPojo [RESULT = " + RESULT + ", list_total_count = " + list_total_count + ", row = " + row + "]";
    }
}

class Row {

     String STATE_GBN;
     String TEL;
     String TRDPL_ADDR;
     String REG_YMD;
     String APV_REG_NO;
     String DCB_YMD;
     String TRNM_NM;
     String CLG_STDT;
     String SEAT_NUM;
     String STATE_GBN_CD;
     String CLG_ENDDT;

    public String getSTATE_GBN() {
        return STATE_GBN;
    }

    public void setSTATE_GBN(String STATE_GBN) {
        this.STATE_GBN = STATE_GBN;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getTRDPL_ADDR() {
        return TRDPL_ADDR;
    }

    public void setTRDPL_ADDR(String TRDPL_ADDR) {
        this.TRDPL_ADDR = TRDPL_ADDR;
    }

    public String getREG_YMD() {
        return REG_YMD;
    }

    public void setREG_YMD(String REG_YMD) {
        this.REG_YMD = REG_YMD;
    }

    public String getAPV_REG_NO() {
        return APV_REG_NO;
    }

    public void setAPV_REG_NO(String APV_REG_NO) {
        this.APV_REG_NO = APV_REG_NO;
    }

    public String getDCB_YMD() {
        return DCB_YMD;
    }

    public void setDCB_YMD(String DCB_YMD) {
        this.DCB_YMD = DCB_YMD;
    }

    public String getTRNM_NM() {
        return TRNM_NM;
    }

    public void setTRNM_NM(String TRNM_NM) {
        this.TRNM_NM = TRNM_NM;
    }

    public String getCLG_STDT() {
        return CLG_STDT;
    }

    public void setCLG_STDT(String CLG_STDT) {
        this.CLG_STDT = CLG_STDT;
    }

    public String getSEAT_NUM() {
        return SEAT_NUM;
    }

    public void setSEAT_NUM(String SEAT_NUM) {
        this.SEAT_NUM = SEAT_NUM;
    }

    public String getSTATE_GBN_CD() {
        return STATE_GBN_CD;
    }

    public void setSTATE_GBN_CD(String STATE_GBN_CD) {
        this.STATE_GBN_CD = STATE_GBN_CD;
    }

    public String getCLG_ENDDT() {
        return CLG_ENDDT;
    }

    public void setCLG_ENDDT(String CLG_ENDDT) {
        this.CLG_ENDDT = CLG_ENDDT;
    }

    @Override
    public String toString() {
        return "ClassPojo [STATE_GBN = " + STATE_GBN + ", TEL = " + TEL + ", TRDPL_ADDR = " + TRDPL_ADDR + ", REG_YMD = " + REG_YMD + ", APV_REG_NO = " + APV_REG_NO + ", DCB_YMD = " + DCB_YMD + ", TRNM_NM = " + TRNM_NM + ", CLG_STDT = " + CLG_STDT + ", SEAT_NUM = " + SEAT_NUM + ", STATE_GBN_CD = " + STATE_GBN_CD + ", CLG_ENDDT = " + CLG_ENDDT + "]";
    }
}

class RESULT {
    private String MESSAGE;

    private String CODE;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    @Override
    public String toString() {
        return "ClassPojo [MESSAGE = " + MESSAGE + ", CODE = " + CODE + "]";
    }
}