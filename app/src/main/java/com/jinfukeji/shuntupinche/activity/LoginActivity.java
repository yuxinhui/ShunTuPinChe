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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.ShunTuApplication;
import com.jinfukeji.shuntupinche.bean.LoginBean;
import com.jinfukeji.shuntupinche.utils.DialogUtils;

/**
 * Created by "于志渊"
 * 时间:"11:00"
 * 包名:com.jinfukeji.shuntupinche.activity
 * 描述:登录界面
 */
public class LoginActivity extends AppCompatActivity{
    private EditText phonenumber,pwd;
    private TextView needzhuce,forgetpass;
    private RadioGroup passandlogin_rg;
    private RadioButton remberpwd,automaticlogin;
    private Button login_btn;

    String loginId,password;
    public boolean isLogin;

    RequestQueue queue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isLogin=ShunTuApplication.getInstace().isLogin();
        if (isLogin){
            startMainActivity();
        }else {
            queue= Volley.newRequestQueue(this);
            initView();
            initData();
            onClick();
        }

    }

    //点击事件
    private void onClick() {
        needzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zhuce=new Intent(getApplication(),RegistActivity.class);
                startActivity(zhuce);
                finish();
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                if (TextUtils.isEmpty(loginId)){
                    phonenumber.setError("手机号码不能为空");
                    phonenumber.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    pwd.setError("密码不能为空");
                    pwd.requestFocus();
                    return;
                }
                String url_login= ShunTuApplication.URL+"carpool/login?telephone="+loginId+"&password="+password;
                Log.e("登录接口",url_login);
                login(url_login);
                finish();
                return;
            }
        });
        /*passandlogin_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == remberpwd.getId()){

                }else if (i == automaticlogin.getId()){

                }
            }
        });*/
    }

    //使用volly进行登录操作
    private void login(final String url_login) {
        StringRequest loginRequest=new StringRequest(Request.Method.POST, url_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            LoginBean bean=gson.fromJson(s,LoginBean.class);
                            if ("ok".equals(bean.getStatus())){
                                isLogin=true;
                                ShunTuApplication.getInstace().setLogin(isLogin);
                                startMainActivity();
                                DialogUtils.createToasdt(LoginActivity.this,bean.getMessage());
                            }else {
                                DialogUtils.createToasdt(LoginActivity.this,"手机号或者密码错误");
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.createToasdt(LoginActivity.this,"请检查网络连接是否正确");
                    }
                });
            }
        })/*{
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("username",loginId);
                params.put("password",password);
                return params;
            }
        }*/;
        queue.add(loginRequest);
    }

    //获取手机号与密码
    private void initData() {
        loginId=phonenumber.getText().toString();
        password=pwd.getText().toString();
    }

    private void startMainActivity() {
        Intent intent=new Intent(getApplication(),BannerActivity.class);
        intent.putExtra("isLogin",isLogin);
        startActivity(intent);
    }

    //初始化控件
    private void initView() {
        phonenumber= (EditText) findViewById(R.id.phonenumber);
        pwd= (EditText) findViewById(R.id.pwd);
        passandlogin_rg= (RadioGroup) findViewById(R.id.passandlogin_rg);
        remberpwd= (RadioButton) findViewById(R.id.remberpwd);
        automaticlogin= (RadioButton) findViewById(R.id.automaticlogin);
        login_btn= (Button) findViewById(R.id.login_btn);
        needzhuce= (TextView) findViewById(R.id.needzhuce);
        forgetpass= (TextView) findViewById(R.id.forgetpass);
    }
}
