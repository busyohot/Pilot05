package com.busyo.yeonho.pilot05;

/**
 * Created by user on 2015-12-21.
 * 추출할 4개의 데이터 정의 - json
 *
 */
public class ListPage0JsonData {

    private String SUBJECT="";
    private String THUMB_URL="";
    private String REG_DT="";
    private int VIEW_COUNT=0;

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getTHUMB_URL() {
        return THUMB_URL;
    }

    public void setTHUMB_URL(String THUMB_URL) {
        this.THUMB_URL = THUMB_URL;
    }

    public String getREG_DT() {
        return REG_DT;
    }

    public void setREG_DT(String REG_DT) {
        this.REG_DT = REG_DT;
    }

    public int getVIEW_COUNT() {
        return VIEW_COUNT;
    }

    public void setVIEW_COUNT(int VIEW_COUNT) {
        this.VIEW_COUNT = VIEW_COUNT;
    }

    @Override
    public String toString() {
        return "ListPage0JsonData{" +
                "THUMB_URL=" + THUMB_URL +
                ", SUBJECT='" + SUBJECT + '\'' +
                ", REG_DT='" + REG_DT + '\'' +
                ", VIEW_COUNT='" + VIEW_COUNT + '\'' +
                '}';
    }
}