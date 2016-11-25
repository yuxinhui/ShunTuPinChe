package com.jinfukeji.shuntupinche.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfukeji.shuntupinche.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by "于志渊"
 * 时间:"15:05"
 * 包名:com.jinfukeji.shuntupinche.adapter
 * 描述:
 */

public class PassengerHistoryAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<String> arrayList=new ArrayList<String>();

    public PassengerHistoryAdapter(Context context, ArrayList<String> arrayList) {
        this.layoutInflater = layoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        if (arrayList != null){
            return arrayList.size();
        }
        return 0;
    }

    @Override
    public String getItem(int i) {
        if (arrayList != null){
            return arrayList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder holder;
        if (view == null){
            holder=new viewHolder();
            view=layoutInflater.inflate(R.layout.item_passengerchaxun,null);
            holder.time_et = (TextView) view.findViewById(R.id.time_et);
            holder.qidian_et = (TextView) view.findViewById(R.id.qidian_et);
            holder.zhongdian_et = (TextView) view.findViewById(R.id.zhongdian_et);
            holder.passengerrenshu_et = (TextView) view.findViewById(R.id.passengerrenshu_et);
            holder.lianxi_btn = (ImageView) view.findViewById(R.id.lianxi_btn);
            view.setTag(holder);
        }else {
            holder= (viewHolder) view.getTag();
        }
        final SharedPreferences sp=view.getContext().getSharedPreferences("History", Activity.MODE_PRIVATE);
        holder.qidian_et.setText(sp.getString("start",""));
        holder.zhongdian_et.setText(sp.getString("finsh",""));
        holder.passengerrenshu_et.setText(sp.getString("renshu",""));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long time=new Long(sp.getLong("date",0));
        holder.time_et.setText(sdf.format(time));
        holder.lianxi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("提示");
                builder.setMessage("是否现在就联系车主");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String num = sp.getString("phone","");
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        view.getContext().startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        return view;
    }
    class viewHolder{
        TextView time_et,qidian_et,zhongdian_et,passengerrenshu_et;
        ImageView lianxi_btn;
    }
}
