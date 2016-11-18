package com.jinfukeji.shuntupinche.activity.owenr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jinfukeji.shuntupinche.R;

/**
 * Created by "于志渊"
 * 时间:"16:26"
 * 包名:com.jinfukeji.shuntupinche.activity.owenr
 * 描述:车主身份验证界面
 */

public class OwenrVerifyActivity extends AppCompatActivity{
    private ImageView verify_shenfen_zheng_img,verify_shenfen_fan_img,verify_jiashi_zheng_img,verify_jiashi_fan_img,verify_chexing_zheng_img,verify_chexing_fan_img,verify_return;
    private Button verify_tijiao_but;
    String oid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owenrverify);
        Intent intent=getIntent();
        oid=intent.getStringExtra("oid");
        initView();
        onClick();
    }

    //初始化控件
    private void initView() {
        verify_return= (ImageView) findViewById(R.id.verify_return);
        verify_tijiao_but= (Button) findViewById(R.id.verify_tijiao_but);
        verify_shenfen_zheng_img= (ImageView) findViewById(R.id.verify_shenfen_zheng_img);
        verify_shenfen_fan_img= (ImageView) findViewById(R.id.verify_shenfen_fan_img);
        verify_jiashi_zheng_img= (ImageView) findViewById(R.id.verify_jiashi_zheng_img);
        verify_jiashi_fan_img= (ImageView) findViewById(R.id.verify_jiashi_fan_img);
        verify_chexing_zheng_img= (ImageView) findViewById(R.id.verify_chexing_zheng_img);
        verify_chexing_fan_img= (ImageView) findViewById(R.id.verify_chexing_fan_img);
    }

    //图片点击事件
    private void onClick() {
        verify_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),OwenrIndexActivity.class);
                startActivity(intent);
            }
        });
        verify_shenfen_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        verify_shenfen_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        verify_chexing_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        verify_chexing_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        verify_jiashi_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        verify_jiashi_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        verify_tijiao_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 点击取消按钮
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
