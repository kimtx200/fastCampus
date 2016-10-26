package com.taewonkim.android.remote_retrofitwithokttp;

import java.util.List;

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

