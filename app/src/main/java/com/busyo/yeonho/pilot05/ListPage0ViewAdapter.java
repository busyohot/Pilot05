package com.busyo.yeonho.pilot05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by user on 2015-12-24.
 */

public class ListPage0ViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<ListPage0ViewItem> data;
    private int layout;

    public ListPage0ViewAdapter(Context context, int layout, ArrayList<ListPage0ViewItem> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
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

        thumb.setImageResource(R.drawable.image_loading);     //기본이미지
        if(listPage0ViewItem.getTHUMB_URL().equals("") || listPage0ViewItem.getTHUMB_URL().equals(null))    //url이 공백이거나 null이면
        {
            thumb.setImageResource(R.drawable.image_not_found);     //이미지 찾지 못하는 그림
        }
        else
        {
            Glide.with(mContext).load(listPage0ViewItem.getTHUMB_URL()).into(thumb);    //해당 url의 이미지
        }

        TextView subject=(TextView)convertView.findViewById(R.id.subject);
        subject.setText(listPage0ViewItem.getSUBJECT());

        TextView date=(TextView)convertView.findViewById(R.id.date);
        date.setText(listPage0ViewItem.getREG_DT());

        TextView count=(TextView)convertView.findViewById(R.id.count);
        count.setText(String.valueOf(listPage0ViewItem.getVIEW_COUNT()));

        return convertView;
    }
}