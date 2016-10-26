package com.jinfukeji.shuntupinche;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jinfukeji.shuntupinche.activity.LoginActivity;
import com.jinfukeji.shuntupinche.activity.RegistActivity;

public class IndexActivity extends AppCompatActivity {
    private Button login_img_btn,regist_img_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        login_img_btn=(Button) findViewById(R.id.login_img_btn);
        regist_img_btn= (Button) findViewById(R.id.regist_img_btn);
        login_img_btn.setOnClickListener(new ClickEvent());
        regist_img_btn.setOnClickListener(new ClickEvent());
    }
    //图片点击事件
    private class ClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_img_btn:
                    Intent login=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(login);
                    break;
                case R.id.regist_img_btn:
                    Intent regist=new Intent(getApplicationContext(),RegistActivity.class);
                    startActivity(regist);
                    break;
            }
        }
    }
}
