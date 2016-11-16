package com.jinfukeji.shuntupinche;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by "于志渊"
 * 时间:"18:10"
 * 包名:com.jinfukeji.shuntupinche
 * 描述:
 */

public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,IndexActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
