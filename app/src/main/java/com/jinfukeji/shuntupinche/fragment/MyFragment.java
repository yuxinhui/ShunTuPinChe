package com.jinfukeji.shuntupinche.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.activity.MyLuXianActivity;
import com.jinfukeji.shuntupinche.weather.MyTianQiActivity;

/**
 * Created by "于志渊"
 * 时间:"17:55"
 * 包名:com.jinfukeji.shuntupinche.fragment
 * 描述:个人账户界面详情
 */
public class MyFragment extends Fragment{
    private RelativeLayout luxian_rl,yue_rl,tianqi_rl,ditu_rl,shezhi_rl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        initView(view);
        onClick();
        return view;
    }

    private void onClick() {
        tianqi_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MyTianQiActivity.class);
                startActivity(intent);
            }
        });
        luxian_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MyLuXianActivity.class);
                startActivity(intent);
            }
        });
        /*yue_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"程序员正在努力写代码,请期待下个版本的更新",Toast.LENGTH_SHORT).show();
            }
        });
        ditu_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"程序员正在努力写代码,请期待下个版本的更新",Toast.LENGTH_SHORT).show();
            }
        });
        shezhi_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"程序员正在努力写代码,请期待下个版本的更新",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void initView(View view) {
        luxian_rl= (RelativeLayout) view.findViewById(R.id.changyongluxian_rl);
        tianqi_rl= (RelativeLayout) view.findViewById(R.id.tianqi_rl);
        /*yue_rl= (RelativeLayout) view.findViewById(R.id.yue_rl);
        ditu_rl= (RelativeLayout) view.findViewById(R.id.ditu_rl);
        shezhi_rl= (RelativeLayout) view.findViewById(R.id.shezhi_rl);*/
    }
}
