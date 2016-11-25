package com.jinfukeji.shuntupinche.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.jinfukeji.shuntupinche.activity.BannerActivity;
import com.jinfukeji.shuntupinche.adapter.PassengerChaXunAdapter;
import com.jinfukeji.shuntupinche.bean.PassengerChaXunBean;
import com.jinfukeji.shuntupinche.utils.DatePickerUtil;
import com.jinfukeji.shuntupinche.utils.DialogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by "于志渊"
 * 时间:"17:54"
 * 包名:com.jinfukeji.shuntupinche.fragment
 * 描述：拼车查询界面详情
 */
public class ChaXunFragment extends Fragment{
    private ImageView pinche_return_img;
    private RelativeLayout lvtu_rl;
    private EditText chengke_start_et,chengke_finsh_et,chengke_renshu_et,chengke_date_et;
    private Button chengke_chaxun_but;
    private ListView chengke_lv;
    String chengke_start,chengke_finsh,chengke_renshu,chengke_date;
    RequestQueue queue;
    PassengerChaXunBean passengerChaXunBean=new PassengerChaXunBean();
    ArrayList<PassengerChaXunBean.DataBean> arrayList=new ArrayList<PassengerChaXunBean.DataBean>();
    private PassengerChaXunAdapter chaXunAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chaxun,container,false);
        queue= Volley.newRequestQueue(getActivity());
        initView(view);
        initData();
        onClick();
        return view;
    }

    //点击事件
    private void onClick() {
        chengke_date_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                String date=sdf.format(new Date(System.currentTimeMillis()));
                DatePickerUtil datePickerUtil=new DatePickerUtil(getActivity(),date);
                datePickerUtil.dateTimePicKDialog(chengke_date_et);
            }
        });
        chengke_chaxun_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                arrayList.clear();
                if (TextUtils.isEmpty(chengke_start)){
                    chengke_start_et.requestFocus();
                    chengke_start_et.setError("出发站不能为空");
                    return;
                }
                if (TextUtils.isEmpty(chengke_finsh)){
                    chengke_finsh_et.requestFocus();
                    chengke_finsh_et.setError("终点站不能为空");
                    return;
                }
                if (TextUtils.isEmpty(chengke_renshu)){
                    chengke_renshu_et.requestFocus();
                    chengke_renshu_et.setError("人数不能为空");
                    return;
                }
                if (TextUtils.isEmpty(chengke_date)){
                    chengke_date_et.requestFocus();
                    chengke_date_et.setError("日期不能为空");
                    return;
                }
                String url_chaXun= ShunTuApplication.URL+"info/select?startPlace="+chengke_start+"&destination="+chengke_finsh+"&time="+chengke_date+"&number="+chengke_renshu;
                chaXun(url_chaXun);
                Log.e("乘客查询",url_chaXun);
                return;
            }
        });
        pinche_return_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //从栈中将当前fragment推出
                getFragmentManager().popBackStack();
                ((BannerActivity)getActivity()).showBanner();
            }
        });
    }

    //Volly请求查询数据
    private void chaXun(String url_chaXun) {
        StringRequest passenger=new StringRequest(Request.Method.POST, url_chaXun,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            passengerChaXunBean=gson.fromJson(s,PassengerChaXunBean.class);
                            if ("ok".equals(passengerChaXunBean.getStatus())){
                                ArrayList<PassengerChaXunBean.DataBean> been= (ArrayList<PassengerChaXunBean.DataBean>) passengerChaXunBean.getData();
                                arrayList.clear();
                                arrayList.addAll(been);
                                chaXunAdapter.notifyDataSetChanged();
                                DialogUtils.createToasdt(getActivity(),passengerChaXunBean.getMessage());
                            }else {
                                Toast.makeText(getActivity(),"尚未有车主发布信息",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(),"尚未有车主发布信息.........",Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(passenger);
    }

    //获取EditText控件的值
    private void initData() {
        chengke_start=chengke_start_et.getText().toString();
        chengke_finsh=chengke_finsh_et.getText().toString();
        chengke_renshu=chengke_renshu_et.getText().toString();
        chengke_date=chengke_date_et.getText().toString();
    }

    //初始化各个控件
    private void initView(View view) {
        pinche_return_img= (ImageView) view.findViewById(R.id.pinche_return_img);
        lvtu_rl= (RelativeLayout) view.findViewById(R.id.lvtu_rl);
        chengke_start_et= (EditText) view.findViewById(R.id.chengke_start_et);
        chengke_finsh_et= (EditText) view.findViewById(R.id.chengke_finsh_et);
        chengke_renshu_et= (EditText) view.findViewById(R.id.chengke_renshu_et);
        chengke_date_et= (EditText) view.findViewById(R.id.chengke_date_et);
        chengke_chaxun_but= (Button) view.findViewById(R.id.chengke_chaxun_but);

        chengke_lv= (ListView) view.findViewById(R.id.chengke_lv);
        chaXunAdapter=new PassengerChaXunAdapter(arrayList,getActivity());
        chengke_lv.setAdapter(chaXunAdapter);
    }
}
