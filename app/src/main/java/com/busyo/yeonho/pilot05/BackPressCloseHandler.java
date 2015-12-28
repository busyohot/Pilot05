package com.busyo.yeonho.pilot05;

/**
 * Created by user on 2015-12-27.
 * 뒤로가기 버튼 2번 눌러야 앱 종료되는 클래스
 */
import android.app.Activity;
import android.widget.Toast;

public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {   //2초가 지나면 취소
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {     //2초 이내에 다시 뒤로가기르 누르면 앱 종료
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}