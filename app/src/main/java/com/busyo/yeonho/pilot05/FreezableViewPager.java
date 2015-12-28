package com.busyo.yeonho.pilot05;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by user on 2015-12-26.
 * ViewPager 좌 우 스크롤 안되게 하는 클래스
 * xml파일에 viewpger를 정의할때 사용한다
 *
 *
 * <com.busyo.yeonho.pilot05.FreezableViewPager
     android:layout_width="match_parent"
     android:layout_height="match_parent"
     android:id="@+id/viewpager"
     android:background="#e11a1a"/>

 이런식으로 사용한다
 */
public class FreezableViewPager extends ViewPager {

    public boolean melted=true;

    public FreezableViewPager(Context context) {
        super(context);
        freeze();
    }

    public FreezableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        freeze();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            if (melted) {
                return super.onTouchEvent(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (melted) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void freeze() {
        melted = false;
    }

    public void melt() {
        melted = true;
    }

}