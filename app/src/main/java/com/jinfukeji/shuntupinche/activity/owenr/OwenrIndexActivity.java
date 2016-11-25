package com.jinfukeji.shuntupinche.activity.owenr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.ShunTuApplication;
import com.jinfukeji.shuntupinche.bean.OwenrRecordBean;
import com.jinfukeji.shuntupinche.utils.DatePickerUtil;
import com.jinfukeji.shuntupinche.utils.DialogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by "于志渊"
 * 时间:"14:05"
 * 包名:com.jinfukeji.shuntupinche.activity.owenr
 * 描述:车主登陆后进入的首页
 */
public class OwenrIndexActivity extends AppCompatActivity{
    private EditText start_et,renshu_et,finsh_et,date_et;
    private TextView history_txt,owenrverify_txt;
    private Button lijifabu_btn;
    OwenrRecordBean owenrRecordBean=new OwenrRecordBean();
    private String oid,tel;
    private String chuShiDi,riQi,renShu,muDi;
    RequestQueue queue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owenrindex);
        queue= Volley.newRequestQueue(this);
        /*Intent intent=getIntent();
        oid=intent.getStringExtra("id");
        tel=intent.getStringExtra("telephone");*/
        SharedPreferences sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        oid=sp.getString("id","");
        tel=sp.getString("USERNAME","");
        initView();
        initData();
        onClick();
    }

    private void initData() {
        chuShiDi=start_et.getText().toString();
        riQi=date_et.getText().toString();
        renShu=renshu_et.getText().toString();
        muDi=finsh_et.getText().toString();
    }

    //点击事件
    private void onClick() {
        date_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                String date=sdf.format(new Date(System.currentTimeMillis()));
                DatePickerUtil datePickerUtil=new DatePickerUtil(OwenrIndexActivity.this,date);
                datePickerUtil.dateTimePicKDialog(date_et);
            }
        });
        lijifabu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                if (TextUtils.isEmpty(chuShiDi)){
                    start_et.requestFocus();
                    start_et.setError("初始地不能为空");
                    return;
                }
                if (TextUtils.isEmpty(riQi)){
                    date_et.requestFocus();
                    date_et.setError("日期不可以为空");
                    return;
                }
                if (TextUtils.isEmpty(renShu)){
                    renshu_et.requestFocus();
                    renshu_et.setError("人数不能为空");
                    return;
                }
                if (TextUtils.isEmpty(muDi)){
                    finsh_et.requestFocus();
                    finsh_et.setError("目的地不能为空");
                    return;
                }
                String url_fabu= ShunTuApplication.URL+"info/add?oid="+oid+"&strtime="+riQi+"&startPlace="+
                        chuShiDi+"&destination="+muDi+"&number="+renShu+"&telephone="+tel;
                Log.e("立即发布",url_fabu);
                faBu(url_fabu);
                return;
            }
        });
        history_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),OwenrHistoryRecordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        owenrverify_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OwenrIndexActivity.this,OwenrVerifyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //立即发布的Volly请求
    private void faBu(String url_fabu) {
        StringRequest owenrFaBu=new StringRequest(Request.Method.POST, url_fabu,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson=new Gson();
                        owenrRecordBean=gson.fromJson(s,OwenrRecordBean.class);
                        if ("ok".equals(owenrRecordBean.getStatus())){
                            DialogUtils.createToasdt(OwenrIndexActivity.this,owenrRecordBean.getMessage());
                        }else {
                            Toast.makeText(OwenrIndexActivity.this,"发布失败,车主身份或许还未验证",Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder=new AlertDialog.Builder(OwenrIndexActivity.this);
                            builder.setTitle("提示");
                            builder.setMessage("是否进行车主身份验证");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent=new Intent(OwenrIndexActivity.this,OwenrVerifyActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                DialogUtils.createToasdt(OwenrIndexActivity.this,"请检查网络连接是否正确");
            }
        });
        queue.add(owenrFaBu);
    }

    //初始化各个控件
    private void initView() {
        start_et= (EditText) findViewById(R.id.start_et);
        date_et= (EditText) findViewById(R.id.date_et);
        renshu_et= (EditText) findViewById(R.id.renshu_et);
        finsh_et= (EditText) findViewById(R.id.finsh_et);
        lijifabu_btn= (Button) findViewById(R.id.lijifabu_btn);
        history_txt= (TextView) findViewById(R.id.history_txt);
        owenrverify_txt= (TextView) findViewById(R.id.owenrverify_txt);
    }
    //退出程序
    private long exitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis() - exitTime >2000){
                Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}