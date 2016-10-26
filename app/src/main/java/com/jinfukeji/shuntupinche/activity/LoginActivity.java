package com.jinfukeji.shuntupinche.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jinfukeji.shuntupinche.R;

/**
 * Created by "于志渊"
 * 时间:"11:00"
 * 包名:com.jinfukeji.shuntupinche.activity
 * 描述:登录界面
 */
public class LoginActivity extends AppCompatActivity{
    private Button login_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn= (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(), BannerActivity.class);
                startActivity(intent);
            }
        });
    }
}
