package com.busyo.yeonho.pilot05;

/**
 * Created by user on 2015-12-24.
 *
 * data 어레이 리스트에 담길 항목 정의
 */
public class ListPage0ViewItem {

    private String SUBJECT;
    private String THUMB_URL;
    private String REG_DT;
    private int  VIEW_COUNT;

    public String getSUBJECT() {
        return SUBJECT;
    }

    public String getTHUMB_URL() {
        return THUMB_URL;
    }

    public String getREG_DT() {
        return REG_DT;
    }

    public int getVIEW_COUNT() {
        return VIEW_COUNT;
    }

    public ListPage0ViewItem(String THUMB_URL, String SUBJECT, String REG_DT, int VIEW_COUNT)
    {
        this.THUMB_URL=THUMB_URL;
        this.SUBJECT=SUBJECT;
        this.REG_DT=REG_DT;
        this.VIEW_COUNT=VIEW_COUNT;
    }
}