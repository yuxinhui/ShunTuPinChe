package com.jinfukeji.shuntupinche.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.adapter.PassengerHistoryAdapter;

import java.util.ArrayList;

/**
 * Created by "于志渊"
 * 时间:"10:50"
 * 包名:com.jinfukeji.shuntupinche.activity
 * 描述:乘客历史记录界面
 */

public class PassengerHistoryActivity extends AppCompatActivity {
    private ListView passengerhistory_lv;
    private ImageView returnpassenger;
    PassengerHistoryAdapter passengerHistory;
    ArrayList<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengerhistory);
        initView();
        onClick();
    }

    private void onClick() {
        returnpassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        passengerhistory_lv= (ListView) findViewById(R.id.passengerhistory_lv);
        returnpassenger= (ImageView) findViewById(R.id.return_passenger);
        passengerHistory=new PassengerHistoryAdapter(this,arrayList);
        passengerhistory_lv.setAdapter(passengerHistory);
    }
}
