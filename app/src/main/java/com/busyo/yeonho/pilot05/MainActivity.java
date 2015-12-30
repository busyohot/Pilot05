package com.busyo.yeonho.pilot05;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //이수영 작성.

    public static ViewPager viewpager = null; //viewpager 선언
    public static ViewPagerAdapter viewpageradapter; // viewpageradapter 선언
    private BackPressCloseHandler backPressCloseHandler; //뒤로가기 두번 눌러야 종료

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        //툴바에 네비게이션 드로어 불러오는 토글 만들기
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //툴바에 네비게이션 드로어 불러오는 토글 만들기끝

        //여기부터 왼쪽에 네비게이션 드로어
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //여기까지가 왼쪽에 네비게이션 드로어

        // 뷰페이저 객체를 참조하고 어댑터를 설정합니다.
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpageradapter = new ViewPagerAdapter(this);
        viewpager.setAdapter(viewpageradapter);
        // 뷰페이저 객체를 참조하고 어댑터를 설정하는것 끝

        ImageView div = (ImageView)findViewById(R.id.DimageView);
        TextView dtv1 = (TextView)findViewById(R.id.DtextView1);
        TextView dtv2 = (TextView)findViewById(R.id.DtextView2);



        //네비게이션 드로어에서

        //'쯔위'사진 누르면 네이버에 '쯔위'검색결과 페이지로 이동
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri weuri = Uri.parse("https://m.search.naver.com/search.naver?query=%EC%AF%94%EC%9C%84&where=m&sm=mtp_hty");
                Intent weit  = new Intent(Intent.ACTION_VIEW,weuri);
                startActivity(weit);
            }
        });

        //정연호 텍스트뷰를 누르면 정연호 에게 메일보내기
        dtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mail1uri = Uri.parse("mailto:busyo@nate.com");
                Intent mail1it  = new Intent(Intent.ACTION_SENDTO,mail1uri);
                startActivity(mail1it);
            }
        });
        //이수영 텍스트뷰를 누르면 이수영 에게 메일보내기
        dtv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mail2uri = Uri.parse("mailto:leesy_0305@naver.com");
                Intent mail2it  = new Intent(Intent.ACTION_SENDTO,mail2uri);
                startActivity(mail2it);
            }
        });

    }

    //뒤로가기 버튼 누르기
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);  //네비게이션 드로어
        if (drawer.isDrawerOpen(GravityCompat.START))   //네비게이션 드로어가 열려있으면
        {
            drawer.closeDrawer(GravityCompat.START);    //네비게이션 드로어를 닫는다
        }
        else if (viewpager.getCurrentItem() == 1)   //뷰페이저 화면이 1번 화면 (상세페이지) 라면
        {
            viewpager.setCurrentItem(0);    //0번 페이지(리스트)로 뷰페이저 이동
        }
        else
        {
            //super.onBackPressed();
            backPressCloseHandler.onBackPressed();  //뒤로가기버튼 두번 눌러야 종료
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //오른쪽 위 메뉴 누르면 한샘모바일 페이지 이동
        if (id == R.id.action_settings) {
            Uri hsuri = Uri.parse("http://hanssem.com/mmain/main.do");
            Intent hsit  = new Intent(Intent.ACTION_VIEW,hsuri);
            startActivity(hsit);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {//네비게이션 드로어에서 각각 항목을 선택하면 토스트 메세지 보여줌
        // Handle navigation view item_page2 clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(),"카메라 기능 선택",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(),"갤러리 기능 선택",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(),"슬라이드 쇼 기능 선택",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(),"도구설정 기능 선택",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(),"공유하기 기능 선택",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(),"전송하기 기능 선택",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}