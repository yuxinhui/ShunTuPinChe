package com.jinfukeji.shuntupinche.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.shuntupinche.R;

/**
 * Created by "于志渊"
 * 时间:"17:55"
 * 包名:com.jinfukeji.shuntupinche.fragment
 * 描述:个人账户界面详情
 */
public class MyFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        return view;
    }
}
