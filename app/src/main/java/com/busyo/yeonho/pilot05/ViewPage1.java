package com.busyo.yeonho.pilot05;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by user on 2015-12-23.
 *
 * 상세화면 뷰
 */
public class ViewPage1 extends LinearLayout{


    //정연호 작성
    static Context mContext;
    static TextView  tv3subject;
    static ImageView tv3url;
    static PhotoViewAttacher photoViewAttacher;    //이미지 핀치줌 선언 // Dependencies 에 com.github.chrisbanes.photoview:library:1.2.4  추가
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
        tv3url = (PhotoView)findViewById(R.id.view_page1imageview1);
        //이미지뷰가 아닌 포토뷰로 정의한 이유
        //핀치줌 사용시 이미지뷰는 가운데 있지 않고 한쪽으로 치우쳐 있다가 줌을 하면 가운데로 온다
        //포토뷰는 처음부터 가운데이 온다//핀치줌 사용을 위하여 포토뷰로 정의

         //이미지뷰 를 핀치줌 (두 손가락으로 확대 축소)
        photoViewAttacher = new PhotoViewAttacher(tv3url);

    }

    public static void setTv3subject(String SUBJECT)    //tv3subject 텍스트뷰에 넘어온 SUBJECT 값 입력
    {
        tv3subject.setText(SUBJECT);
    }

    public static void setTv3url(String THUMB_URL)  //tv3url 포토뷰에 Glide 로 이미지 삽입 후 PhotoViewAttacher update
    {

        Glide.with(mContext).load(THUMB_URL)                                //로드 이미지
                .diskCacheStrategy(DiskCacheStrategy.ALL)       //이미지를 디스크에 캐시하여 다음에 빨리보여준다
                .fitCenter()                                  //이미지 가운데
                .dontAnimate()                                  //에니메이션 효과 없음(이미지가 로딩중이미지의 크기로 바뀌어 보였다가 정상크기로 돌아오는것을 방지하기위함
                .placeholder(R.drawable.image_loading)          //이미지 로딩 전에 미리 보여줄 이미지
                .error(R.drawable.image_not_found)              //이미지 로딩 못했을때 보여줄 에러 이미지
                .into(tv3url);                                  //이미지 뷰 이름
                photoViewAttacher.update();//PhotoViewAttacher update


    }


}