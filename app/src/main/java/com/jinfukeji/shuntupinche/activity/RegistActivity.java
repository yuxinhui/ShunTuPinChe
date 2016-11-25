package com.jinfukeji.shuntupinche.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.jinfukeji.shuntupinche.bean.SmsMessage;
import com.jinfukeji.shuntupinche.bean.ZhuceBean;
import com.jinfukeji.shuntupinche.utils.DialogUtils;
import com.jinfukeji.shuntupinche.utils.VercodeTime;

/**
 * Created by "于志渊"
 * 时间:"11:22"
 * 包名:com.jinfukeji.shuntupinche
 * 描述:注册界面
 */
public class RegistActivity extends AppCompatActivity{
    private EditText number,password,yanzhengmaNum;
    private TextView getyanzhengma;
    private Button regist_btn;
    private RadioButton chezhu,chengke;
    private RadioGroup checkedchange;
    String telepone,password_txt,validataCode;
    RequestQueue queue;
    SmsMessage smsMessage=new SmsMessage();
    ZhuceBean zhuceBean=new ZhuceBean();
    String url_regist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        queue= Volley.newRequestQueue(this);
        initView();
        initData();
        onClick();
    }
    //view控件的监听事件
    private void onClick() {
        getyanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                if (TextUtils.isEmpty(telepone)){
                    number.requestFocus();
                    number.setError("手机号码不能为空");
                    return;
                }
                if (telepone.length() != 11 || !telepone.startsWith("1")){
                    number.setError("请填写正确的手机号");
                    number.requestFocus();
                    return;
                }
                String url_getCode= ShunTuApplication.URL+"tel_code?telephone="+telepone;
                Log.e("验证码接口",url_getCode);
                getVerCode(url_getCode);
                VercodeTime vct=new VercodeTime(1000*300,1000,getyanzhengma);
                vct.start();
            }
        });
        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                if (TextUtils.isEmpty(telepone)){
                    number.requestFocus();
                    number.setError("手机号码不能为空");
                    return;
                }
                if (telepone.length() != 11 || !telepone.startsWith("1")){
                    number.setError("请输入正确的手机号");
                    number.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password_txt)){
                    password.requestFocus();
                    password.setError("密码不能为空");
                    return;
                }
                if (password_txt.length() < 6){
                    password.setError("密码长度至少大于6位");
                    password.requestFocus();
                    return;
                }
                if (getyanzhengma.getText().toString().equals("重新获取验证码")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DialogUtils.createToasdt(RegistActivity.this,"验证码已经过期，请重新获取");
                        }
                    });
                    return;
                }
                if (TextUtils.isEmpty(validataCode)){
                    yanzhengmaNum.requestFocus();
                    yanzhengmaNum.setError("验证码不能为空");
                    return;
                }
                String url_finsh=ShunTuApplication.URL+"register?telephone="+telepone+"&password="+password_txt+"&identity="+url_regist+"&tel_code="+validataCode;
                regist(url_finsh);
                Log.e("注册",url_finsh);
                return;
            }
        });
        checkedchange.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==chezhu.getId()){
                    url_regist="owner";
                }else if (i == chengke.getId()){
                    url_regist="passenger";
                }
            }
        });
    }

    private void initData() {
        telepone=number.getText().toString();
        password_txt=password.getText().toString();
        Log.e("密码",password_txt);
        validataCode=yanzhengmaNum.getText().toString();
        Log.e("验证码",validataCode);
    }

    //初始化控件
    public void initView() {
        number= (EditText) findViewById(R.id.number);
        password= (EditText) findViewById(R.id.password);
        yanzhengmaNum= (EditText) findViewById(R.id.yanzhengmaNum);
        getyanzhengma= (TextView) findViewById(R.id.getyanzhengma);
        regist_btn= (Button) findViewById(R.id.regist_btn);
        checkedchange= (RadioGroup) findViewById(R.id.checkchange);
        chezhu= (RadioButton) findViewById(R.id.chezhu);
        chengke= (RadioButton) findViewById(R.id.chengke);
    }

    //获取验证码的volley请求
    public void getVerCode(String url_getCode) {
        StringRequest requestVerCode=new StringRequest(Request.Method.POST, url_getCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            smsMessage=gson.fromJson(s,SmsMessage.class);
                            if ("ok".equals(smsMessage.getStatus())){
                                DialogUtils.createToasdt(RegistActivity.this,smsMessage.getMessage());
                                return;
                            }else {
                                Toast.makeText(RegistActivity.this,"验证码获取失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DialogUtils.createToasdt(RegistActivity.this,"请检查网络连接是否正确");
                            }
                        });
                    }
                });
        queue.add(requestVerCode);
    }

    //注册的volley请求
    public void regist(final String url_finsh) {
        StringRequest request=new StringRequest(Request.Method.POST, url_finsh,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            zhuceBean=gson.fromJson(s,ZhuceBean.class);
                            if ("ok".equals(zhuceBean.getStatus())){
                                Intent intent=new Intent(RegistActivity.this,RegistSuccessActivity.class);
                                startActivity(intent);
                                DialogUtils.createToasdt(RegistActivity.this,zhuceBean.getMessage());
                                finish();
                            }else {
                                DialogUtils.createToasdt(RegistActivity.this,"注册失败");
                                return;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                DialogUtils.createToasdt(RegistActivity.this,volleyError.getMessage());
            }
        });
        queue.add(request);
    }
}
