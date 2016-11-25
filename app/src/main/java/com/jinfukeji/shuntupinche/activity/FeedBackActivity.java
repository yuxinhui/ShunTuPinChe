package com.jinfukeji.shuntupinche.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.jinfukeji.shuntupinche.bean.LoginBean;

/**
 * Created by "于志渊"
 * 时间:"16:58"
 * 包名:com.jinfukeji.shuntupinche.activity
 * 描述:用户意见反馈界面
 */

public class FeedBackActivity extends AppCompatActivity{
    private EditText feedback_et;
    private Button feedback_btn;
    LoginBean bean=new LoginBean();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
        onClick();
    }

    //提交意见
    private void onClick() {
        feedback_et.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);//设置EditText的显示方式为多行文本输入
        feedback_et.setSingleLine(false);//改变默认的单行模式
        feedback_et.setHorizontallyScrolling(false);//水平滚动设置为False

        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback=feedback_et.getText().toString();
                if (TextUtils.isEmpty(feedback)){
                    feedback_et.requestFocus();
                    feedback_et.setError("真诚邀请您给点宝贵意见");
                    return;
                }
                SharedPreferences sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
                String id=sp.getString("id","");
                Log.e("id",id);
                String url_feedback= ShunTuApplication.URL+"feedback/add?opid="+id+"&content="+feedback;
                Log.e("意见反馈",url_feedback);
                feedBack(url_feedback);
                return;
            }
        });
    }

    //请求数据
    private void feedBack(String url_feedback) {
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request_feedback=new StringRequest(Request.Method.POST, url_feedback,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            bean=gson.fromJson(s,LoginBean.class);
                            if ("ok".equals(bean.getStatus())){
                                Toast.makeText(FeedBackActivity.this,"提交成功,谢谢您的意见",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(FeedBackActivity.this,"提交失败，请检查网络连接",Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request_feedback);
    }

    //初始化控件
    private void initView() {
        feedback_et= (EditText) findViewById(R.id.feedback_et);
        feedback_btn= (Button) findViewById(R.id.feedback_btn);
    }
}
