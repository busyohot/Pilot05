package com.busyo.yeonho.pilot05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by user on 2015-12-24.
 */

public class ListPage0ViewAdapter extends BaseAdapter {

    //정연호 작성
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<ListPage0ViewItem> data;
    private int layout;

    public ListPage0ViewAdapter(Context context, int layout, ArrayList<ListPage0ViewItem> data){//리스트뷰 아답타
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data; //제이슨으로 추출한 데이터
        this.layout=layout;
        this.mContext=context;
    }

    @Override
    public int getCount(){return data.size();}
    @Override
    public String getItem(int position)
    {
        return data.get(position).getSUBJECT();
    }
    public String getTHUMB_URL(int position)
    {
        return data.get(position).getTHUMB_URL();
    }
    public String getSUBJECT(int position)
    {
        return data.get(position).getSUBJECT();
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(layout,parent,false);
        }
        ListPage0ViewItem listPage0ViewItem =data.get(position);

        ImageView thumb=(ImageView)convertView.findViewById(R.id.thumb);
        Glide.with(mContext).load(listPage0ViewItem.getTHUMB_URL())         //로드 이미지
                            .diskCacheStrategy(DiskCacheStrategy.ALL)       //이미지를 디스크에 캐시하여 다음에 빨리보여준다
                            .fitCenter()                                   //이미지 가운데
                            .dontAnimate()                                  //에니메이션 효과 없음(이미지가 로딩중이미지의 크기로 바뀌어 보였다가 정상크기로 돌아오는것을 방지하기위함
                .placeholder(R.drawable.image_loading)          //이미지 로딩 전에 미리 보여줄 이미지
                            .error(R.drawable.image_not_found)              //이미지 로딩 못했을때 보여줄 에러 이미지
                            .into(thumb);                                   //이미지 뷰 이름


        TextView subject=(TextView)convertView.findViewById(R.id.subject);
        subject.setText(listPage0ViewItem.getSUBJECT());

        TextView date=(TextView)convertView.findViewById(R.id.date);
        date.setText(listPage0ViewItem.getREG_DT());

        TextView count=(TextView)convertView.findViewById(R.id.count);
        count.setText(String.valueOf(listPage0ViewItem.getVIEW_COUNT()));

        return convertView;
    }
}