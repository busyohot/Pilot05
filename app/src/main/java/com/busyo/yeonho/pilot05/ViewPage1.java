package com.busyo.yeonho.pilot05;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by user on 2015-12-23.
 *
 * 상세화면 뷰
 */
public class ViewPage1 extends LinearLayout{

    static Context mContext;
    static TextView  tv3subject;
    static ImageView tv3url;

    public ViewPage1(Context context) {
        super(context);
        init(context);
    }

    public ViewPage1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // inflate XML layout. 상세페이지 view_page1.xml 파일 inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_page1, this, true);
        tv3subject = (TextView)findViewById(R.id.view_page1text1);
        tv3url = (ImageView)findViewById(R.id.view_page1imageview1);
    }

    public static void setTv3subject(String SUBJECT)
    {
        tv3subject.setText(SUBJECT);
    }

    public String getTv3subject() {
        return tv3subject.getText().toString();
    }

    public static void setTv3url(String THUMB_URL)
    {
        tv3url.setImageResource(R.drawable.image_loading);     //기본이미지
        //이미지 url이 공백이거나 없으면 image_not_found 이미지표시
        if(THUMB_URL.equals("") || THUMB_URL.equals(null))
        {
            tv3url.setImageResource(R.drawable.image_not_found);
        }
        //이미지url이 있으면 해당 이미지를 표시
        else
        {
            Glide.with(mContext).load(THUMB_URL).into(tv3url);
        }
    }
    public String getTv3url() {
        return tv3url.toString();
    }
}