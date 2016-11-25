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
import com.jinfukeji.shuntupinche.bean.PassengerChaXunBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by "于志渊"
 * 时间:"10:23"
 * 包名:com.jinfukeji.shuntupinche.adapter
 * 描述:乘客查询界面适配器
 */

public class PassengerChaXunAdapter extends BaseAdapter {
    ArrayList<PassengerChaXunBean.DataBean> dataBeen = new ArrayList<PassengerChaXunBean.DataBean>();
    LayoutInflater mLayoutInflater;
    PassengerChaXunBean.DataBean chaXunBean=new PassengerChaXunBean.DataBean();

    public PassengerChaXunAdapter(ArrayList<PassengerChaXunBean.DataBean> dataBeen, Context context) {
        this.dataBeen = dataBeen;
        this.mLayoutInflater = mLayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (dataBeen != null) {
            return dataBeen.size();
        }
        return 0;
    }

    @Override
    public PassengerChaXunBean.DataBean getItem(int i) {
        if (dataBeen != null) {
            return dataBeen.get(i);
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
        if (view == null) {
            holder = new viewHolder();
            view = mLayoutInflater.inflate(R.layout.item_passengerchaxun, null);
            holder.time_et = (TextView) view.findViewById(R.id.time_et);
            holder.qidian_et = (TextView) view.findViewById(R.id.qidian_et);
            holder.zhongdian_et = (TextView) view.findViewById(R.id.zhongdian_et);
            holder.passengerrenshu_et = (TextView) view.findViewById(R.id.passengerrenshu_et);
            holder.lianxi_btn = (ImageView) view.findViewById(R.id.lianxi_btn);
            holder.lianxi_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("提示");
                    builder.setMessage("是否现在就联系车主");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String num = chaXunBean.getTelephone();
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
                            SharedPreferences sp=view.getContext().getSharedPreferences("History", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putLong("date",chaXunBean.getTime());
                            editor.putString("start",chaXunBean.getStartPlace());
                            editor.putString("finsh",chaXunBean.getDestination());
                            editor.putString("renshu",chaXunBean.getNumber());
                            editor.putString("phone",chaXunBean.getTelephone());
                            editor.commit();
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
            view.setTag(holder);
        }else {
            holder= (viewHolder) view.getTag();
        }
        chaXunBean=getItem(i);
        holder.qidian_et.setText(chaXunBean.getStartPlace());
        holder.zhongdian_et.setText(chaXunBean.getDestination());
        holder.passengerrenshu_et.setText(chaXunBean.getNumber());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long time=new Long(chaXunBean.getTime());
        holder.time_et.setText(sdf.format(time));
        return view;
    }
    class viewHolder{
        TextView time_et,qidian_et,zhongdian_et,passengerrenshu_et;
        ImageView lianxi_btn;
    }
}
