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
 * 时间:"17:54"
 * 包名:com.jinfukeji.shuntupinche.fragment
 * 描述：拼车查询界面详情
 */
public class ChaXunFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chaxun,container,false);
        return view;
    }
}
