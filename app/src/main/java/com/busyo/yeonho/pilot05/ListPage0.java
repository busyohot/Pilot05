package com.busyo.yeonho.pilot05;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 2015-12-23.
 * 뷰페이저에서 리스트를 보여주는 뷰
 */
public class ListPage0 extends LinearLayout {
    //정연호 작성

    Context mContext;

    ListView listView;
    ListPage0ViewAdapter listviewadapter;

    ArrayList<ListPage0ViewItem> data;
    ListPage0ViewItem jsdata;


    private OkHttpClient client = null;

    LayoutInflater inflater;
    private String sUrl;


    Button btfast ;     //빠른조치 버튼
    Button btmanual;    // 메뉴얼 버튼

    int page;   //두개의 리스트뷰를 구별할 번호 설정 (0번페이지-빠른조치, 1번페이지-메뉴얼)

    LayoutAnimationController controller;   //리스트뷰에 사용할 애니메이션 콘트롤러

    public ListPage0(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) { //ViewPagerAdapter 에서 넘어온 mContext

        //리스트뷰 애니메이션 선언 - 위에서 아래로 차례로 열리는 애니메이션
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(50);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);

        animation.setDuration(200);
        set.addAnimation(animation);
        controller = new LayoutAnimationController(set, 0.5f);
        //리스트뷰 애니메이션 선언 - 끝


        //처음에는 빠른조치를 리스트에 보여준다 - 빠른조치 URL
        sUrl = "http://asdev.hanssem.com/admin/board/doSearch.as?CONTEXT_TYPE=FAQ&PARAM_CONTEXT_TYPE=FAQ&VIEW_COUNT=40";

        data = new ArrayList<>();   //데이터 어레이 리스트 선언

        mContext = context;

        // inflate XML layout,  list_page0.xml inflate
        inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_page0, this, true);

        listView = (ListView) findViewById(R.id.list_page0text1);
        listviewadapter = new ListPage0ViewAdapter(mContext, R.layout.list_page0_adpater_item, data);
        listView.setAdapter(listviewadapter);

        getJsonData();  //제이슨 데이터 추출

        btfast = (Button)findViewById(R.id.btfast); //빠른조치 버튼
        btmanual = (Button)findViewById(R.id.btmanual); //메뉴얼 버튼

        //현재화면의 버튼을 돋보이게함
        btfast.setTextColor(Color.BLACK);
        btfast.setTypeface(Typeface.DEFAULT_BOLD);
        btmanual.setTextColor(Color.GRAY);
        btmanual.setTypeface(Typeface.DEFAULT);
        page=0;     //페이지 번호 0 - 빠른조치

        //Toast.makeText(mContext,"빠른조치 페이지",Toast.LENGTH_SHORT).show();  //선택한 페이지 안내 토스트

        //빠른조치 버튼
        btfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메뉴얼 리스트에 있다가 빠른조치리스트로 넘어올때만 버튼 작동 - 빠른조치 상태에서는 빠른조치 버튼을 눌러도 버튼 작동 안함
                if(page ==1)
                {
                    sUrl = "http://asdev.hanssem.com/admin/board/doSearch.as?CONTEXT_TYPE=FAQ&PARAM_CONTEXT_TYPE=FAQ&VIEW_COUNT=40";
                    getJsonData();
                    btfast.setTextColor(Color.BLACK);
                    btfast.setTypeface(Typeface.DEFAULT_BOLD);
                    btmanual.setTextColor(Color.GRAY);
                    btmanual.setTypeface(Typeface.DEFAULT);
                    page=0; //빠른조치 페이지 번호로 변경
                }
                //Toast.makeText(mContext,"빠른조치 페이지",Toast.LENGTH_SHORT).show();
            }
        });
        btmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //빠른조치 리스트에 있다가 메뉴얼리스트로 넘어올때만 버튼 작동 - 메뉴얼 상태에서는 메뉴얼 버튼을 눌러도 버튼 작동 안함
                if(page==0)
                {
                    sUrl = "http://asdev.hanssem.com/admin/board/doSearch.as?CONTEXT_TYPE=MAN&PARAM_CONTEXT_TYPE=MAN&VIEW_COUNT=40";
                    getJsonData();
                    btfast.setTextColor(Color.GRAY);
                    btfast.setTypeface(Typeface.DEFAULT);
                    btmanual.setTextColor(Color.BLACK);
                    btmanual.setTypeface(Typeface.DEFAULT_BOLD);
                    page=1; //메뉴얼 페이지 번호로 변경
                }
                //Toast.makeText(mContext, "메뉴얼 페이지", Toast.LENGTH_SHORT).show();
            }
        });

        //리스트중 한 항목을 눌렀을때
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.viewpager.setCurrentItem(1);   //뷰페이저 페이지를 0 에서 1로 이동 (1 페이지 - 상세설명 페이지)
                ViewPage1.setTv3subject(listviewadapter.getSUBJECT(position));  //상세페이지화면으로 제목 넘김
                ViewPage1.setTv3url(listviewadapter.getTHUMB_URL(position));    //상세페이지화면으로 이미지의 URL 넘김


            }
        });
    }

    //제이슨데이터 가져오기
    public void getJsonData(){
        new AsyncTask<String,Integer,String>() {
            @Override
            protected String doInBackground(String... params) { //백그라운드 작엄
                try {
                    client = new OkHttpClient();
                    Request request = new Request.Builder().url(sUrl).build();  //리스트에 보여줄 URL
                    Response response = client.newCall(request).execute();
                    return response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try {
                    JSONObject jsonObject = new JSONObject(result); //제이슨 오브젝트중
                    JSONArray jsonArray = jsonObject.optJSONArray("data");  //data 의 제이슨 어레이만 가져온다
                    onPrintJson(jsonObject.optJSONArray("data"));   //data의 제이슨 어레이를 추출하기위하여 넘김
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
    public void onPrintJson(JSONArray jsonArray) {

        try {
            Gson gson = new Gson();         //GSON으로 JSON데이터 추출
            data.clear();                   //기존 data 어레이 리스트를 초기화한다 (새로운 리스트를 불러오기위해 기존 데이터 삭제)
            for (int i = 0; i < jsonArray.length(); i++) {
                ListPage0JsonData temp = gson.fromJson(jsonArray.get(i).toString(), ListPage0JsonData.class);//temp 에 제이슨으로 추출할 데이터 형식을 담는다
                jsdata = new ListPage0ViewItem(temp.getTHUMB_URL(),temp.getSUBJECT(),temp.getREG_DT(),temp.getVIEW_COUNT());
                //추출한 데이터중  4개만 가져온다. 이미지url, 제목, 등록날짜, 조회수

                data.add(jsdata);                       //추출한 데이터를 data어레이리스트에 담는다
                listviewadapter.notifyDataSetChanged(); //리스트뷰아답터 데이터가 변경되어 새로고침
                listView.setSelectionFromTop(0, 0);  //리스트위치를 제일 위를 선언 (제일 위 부터 화면에 보이게 한다)
                listView.setLayoutAnimation(controller);    //리스트뷰 애니메이션
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}