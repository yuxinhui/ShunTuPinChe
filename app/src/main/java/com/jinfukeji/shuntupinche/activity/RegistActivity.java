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
 * 时间:"11:22"
 * 包名:com.jinfukeji.shuntupinche
 * 描述:注册界面
 */
public class RegistActivity extends AppCompatActivity{
    private Button regist_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        regist_btn= (Button) findViewById(R.id.regist_btn);
        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registOK=new Intent(getBaseContext(),RegistSuccessActivity.class);
                startActivity(registOK);
                finish();
            }
        });
    }
}
