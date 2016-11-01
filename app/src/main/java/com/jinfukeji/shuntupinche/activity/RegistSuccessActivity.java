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
 * 时间:"16:30"
 * 包名:com.jinfukeji.shuntupinche.activity
 * 描述:注册成功界面
 */

public class RegistSuccessActivity extends AppCompatActivity{
    private Button registsuccess_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registsuccess);
        registsuccess_btn= (Button) findViewById(R.id.registsuccess_btn);
        registsuccess_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
