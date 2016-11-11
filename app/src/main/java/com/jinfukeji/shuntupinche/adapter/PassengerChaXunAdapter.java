package com.jinfukeji.shuntupinche.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class PassengerChaXunAdapter extends BaseAdapter{
    ArrayList<PassengerChaXunBean.DataBean> dataBeen=new ArrayList<PassengerChaXunBean.DataBean>();
    LayoutInflater mLayoutInflater;

    public PassengerChaXunAdapter(ArrayList<PassengerChaXunBean.DataBean> dataBeen, Context context) {
        this.dataBeen = dataBeen;
        this.mLayoutInflater = mLayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (dataBeen != null){
            return dataBeen.size();
        }
        return 0;
    }

    @Override
    public PassengerChaXunBean.DataBean getItem(int i) {
        if (dataBeen != null){
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
        if (view == null){
            holder=new viewHolder();
            view=mLayoutInflater.inflate(R.layout.item_passengerchaxun,null);
            holder.time_et= (TextView) view.findViewById(R.id.time_et);
            holder.qidian_et= (TextView) view.findViewById(R.id.qidian_et);
            holder.zhongdian_et= (TextView) view.findViewById(R.id.zhongdian_et);
            holder.passengerrenshu_et= (TextView) view.findViewById(R.id.passengerrenshu_et);
            view.setTag(holder);
        }else {
            holder= (viewHolder) view.getTag();
        }
        PassengerChaXunBean.DataBean bean=getItem(i);
        holder.qidian_et.setText(bean.getStartPlace());
        holder.zhongdian_et.setText(bean.getDestination());
        holder.passengerrenshu_et.setText(bean.getNumber());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long time=new Long(bean.getTime());
        holder.time_et.setText(sdf.format(time));
        return view;
    }
    class viewHolder{
        TextView time_et,qidian_et,zhongdian_et,passengerrenshu_et;
    }
}
