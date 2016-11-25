package com.jinfukeji.shuntupinche.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.activity.BannerActivity;
import com.jinfukeji.shuntupinche.activity.FeedBackActivity;
import com.jinfukeji.shuntupinche.activity.PassengerHistoryActivity;
import com.jinfukeji.shuntupinche.activity.YouHuiActivity;

/**
 * Created by "于志渊"
 * 时间:"17:47"
 * 包名:com.jinfukeji.shuntupinche.fragment
 * 描述:主界面详情
 */

public class IndexFragment extends Fragment {
    private TextView bybus_img,historicalrecord_img,huodong_img,feedback_img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index,container,false);
        initView(view);
        return view;
    }

    //初始化控件
    private void initView(View view) {
        bybus_img= (TextView) view.findViewById(R.id.bybus_img);
        historicalrecord_img= (TextView) view.findViewById(R.id.historicalrecord_img);
        huodong_img= (TextView) view.findViewById(R.id.huodong_img);
        feedback_img= (TextView) view.findViewById(R.id.feedback_img);

        //点击跳转事件
        bybus_img.setOnClickListener(new intentClick());
        historicalrecord_img.setOnClickListener(new intentClick());
        huodong_img.setOnClickListener(new intentClick());
        feedback_img.setOnClickListener(new intentClick());
    }

    private class intentClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bybus_img:
                    FragmentManager fm=getFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.content,new ChaXunFragment()).commit();
                    ((BannerActivity)getActivity()).hideBanner();
                    break;
                case R.id.historicalrecord_img:
                    Intent intent2=new Intent(getActivity(), PassengerHistoryActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.huodong_img:
                    Intent intent=new Intent(getActivity(), YouHuiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.feedback_img:
                    Intent intent1=new Intent(getActivity(), FeedBackActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    }
}
