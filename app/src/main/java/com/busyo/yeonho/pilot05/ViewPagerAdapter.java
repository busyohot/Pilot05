package com.busyo.yeonho.pilot05;

/**
 * Created by user on 2015-12-23.
 *
 * 뷰페이저 아답타
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 뷰페이저를 위한 어댑터 정의
 */
public class ViewPagerAdapter extends PagerAdapter {

    /**
     * Context 객체
     */
    private Context mContext;

    /**
     * 초기화
     *
     * @param context
     */
    public ViewPagerAdapter( Context context)
    {
        mContext = context;
    }

    /**
     * 페이지 갯수
     */
    public int getCount()
    {
        return 2;   //리스트를 보여주는 페이지, 상세페이지 2개
    }

    /**
     * 뷰페이저가 만들어졌을 때 호출됨
     */
    public Object instantiateItem(ViewGroup container, int position) {
        // create a instance of the page and set data
        if(position == 0 )      //뷰페이저 0번이면 리스트(빠른조치,메뉴얼)
        {
            ListPage0 listPage0 = new ListPage0(mContext);
            container.addView(listPage0, position);
            return listPage0;
        }
        else if(position == 1)      //뷰페이저 1번이면 상세페이지
        {
            ViewPage1 viewpage1 = new ViewPage1(mContext);
            container.addView(viewpage1, position);
            return viewpage1;
        }
        return null;
    }

    /**
     * Called to remove the page
     */
    public void destroyItem(ViewGroup container, int position, Object view) {
        //container.removeView((View)view);
    }
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}