package com.busyo.yeonho.pilot05;

/**
 * Created by user on 2015-12-29.
 */
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
//photovoew-핀치줌 할때 그림 줄일때 outofindex 에러를 없애기 위해 커스텀 한 DrawerLayout  (HackyDrawerLayout.java) 사용
public class HackyDrawerLayout extends DrawerLayout {


    public HackyDrawerLayout(Context context) {
        super(context);
    }

    public HackyDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HackyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
}