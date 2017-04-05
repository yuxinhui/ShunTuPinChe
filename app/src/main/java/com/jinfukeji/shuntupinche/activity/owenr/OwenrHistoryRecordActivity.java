package com.jinfukeji.shuntupinche.activity.owenr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import com.jinfukeji.shuntupinche.bean.OwenrDeleteBeen;
import com.jinfukeji.shuntupinche.bean.OwenrHistoryRecordBean;
import com.jinfukeji.shuntupinche.bean.OwenrUpDataBeen;
import com.jinfukeji.shuntupinche.utils.DatePickerUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by "于志渊"
 * 时间:"16:32"
 * 包名:com.jinfukeji.shuntupinche.activity.owenr
 * 描述:司机发布记录界面
 */

public class OwenrHistoryRecordActivity extends AppCompatActivity {
    private ImageView return_img;
    private ListView date_lv;
    private OwenrHistoryRecordAdapter owenrHistoryRecordAdapter;
    private OwenrHistoryRecordBean owenrHistoryRecordBean=new OwenrHistoryRecordBean();
    private ArrayList<OwenrHistoryRecordBean.DataBean> dataBeen=new ArrayList<>();
    String oid;
    String url_owenrhistory;
    RequestQueue queue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owenrhistoryrecord);
        /*Intent intent=getIntent();
        oid=intent.getStringExtra("oid");*/
        SharedPreferences sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        oid=sp.getString("id","");
        queue= Volley.newRequestQueue(this);
        url_owenrhistory= ShunTuApplication.URL+"info/selectByOid?oid="+oid;
        Log.e("发布记录",url_owenrhistory);
        initData();
        date_lv= (ListView) findViewById(R.id.date_lv);
        owenrHistoryRecordAdapter=new OwenrHistoryRecordAdapter(dataBeen,this);
        date_lv.setAdapter(owenrHistoryRecordAdapter);
        initView();
    }

    private void initView() {
        return_img= (ImageView) findViewById(R.id.return_img);
        return_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),OwenrIndexActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //获取发布历史记录数据
    public void initData() {
        final ProgressDialog dialog=ProgressDialog.show(this,"发布记录","加载中......");
        StringRequest owenrHiatory=new StringRequest(Request.Method.POST, url_owenrhistory,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson=new Gson();
                        owenrHistoryRecordBean=gson.fromJson(s,OwenrHistoryRecordBean.class);
                        ArrayList<OwenrHistoryRecordBean.DataBean> been= (ArrayList<OwenrHistoryRecordBean.DataBean>) owenrHistoryRecordBean.getData();
                        if (been==null){
                            Toast.makeText(OwenrHistoryRecordActivity.this,"还没有发布记录",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        }else {
                            dataBeen.clear();
                            dataBeen.addAll(been);
                            owenrHistoryRecordAdapter.notifyDataSetChanged();
                            Toast.makeText(OwenrHistoryRecordActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(OwenrHistoryRecordActivity.this,"历史发布信息查询失败",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        queue.add(owenrHiatory);
    }

    /**
     * Created by "于志渊"
     * 时间:"16:53"
     * 包名:com.jinfukeji.shuntupinche.activity
     * 描述:司机发布记录适配器
     */

    public class OwenrHistoryRecordAdapter extends BaseAdapter {
        ArrayList<OwenrHistoryRecordBean.DataBean> dataBeen1=new ArrayList<OwenrHistoryRecordBean.DataBean>();
        LayoutInflater mInflater;

        //修改删除界面所需要的控件
        private EditText up_time_et,up_qidian_et,up_zhongdian_et,up_renshu_et;
        String up_time,up_qidian,up_zhongdian,up_renshu,old_time,old_qidian,old_zhongdian,old_renshu;
        OwenrUpDataBeen up_dataBean=new OwenrUpDataBeen();
        OwenrHistoryRecordBean.DataBean bean=new OwenrHistoryRecordBean.DataBean();
        OwenrDeleteBeen delete_dataBeen=new OwenrDeleteBeen();
        RequestQueue queue1;
        View view1;
        OwenrHistoryRecordAdapter historyRecordAdapter;

        public OwenrHistoryRecordAdapter(ArrayList<OwenrHistoryRecordBean.DataBean> dataBeen1, Context context) {
            this.dataBeen1 = dataBeen1;
            this.mInflater = mInflater.from(context);
        }

        @Override
        public int getCount() {
            if (dataBeen1 != null){
                return dataBeen1.size();
            }
            return 0;
        }

        @Override
        public OwenrHistoryRecordBean.DataBean getItem(int i) {
            if (dataBeen1 != null){
                return dataBeen1.get(i);
            }
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            viewHolder holder;
            if (view == null){
                holder=new viewHolder();
                view=mInflater.inflate(R.layout.item_owenrjilu,null);
                queue1= Volley.newRequestQueue(view.getContext());
                historyRecordAdapter=new OwenrHistoryRecordAdapter(dataBeen1,view.getContext());
                holder.time_et= (TextView) view.findViewById(R.id.time_et);
                holder.qidian_et= (TextView) view.findViewById(R.id.qidian_et);
                holder.zhongdian_et= (TextView) view.findViewById(R.id.zhongdian_et);
                holder.owenrrenshu_et= (TextView) view.findViewById(R.id.owenrrenshu_et);
                //删除修改按钮
                holder.xiugai_btn= (ImageView) view.findViewById(R.id.xiugai_btn);
                holder.shanchu_btn= (ImageView) view.findViewById(R.id.shanchu_btn);
                upAndDelete(i, holder);
                view.setTag(holder);
            }else {
                holder= (viewHolder) view.getTag();
            }
            bean=getItem(i);
            holder.qidian_et.setText(bean.getStartPlace());
            holder.zhongdian_et.setText(bean.getDestination());
            holder.owenrrenshu_et.setText(bean.getNumber());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            long time=new Long(bean.getTime());
            String s=sdf.format(time);
            holder.time_et.setText(s);
            return view;
        }

        //修改删除的点击事件
        private void upAndDelete(final int i, viewHolder holder) {
            holder.xiugai_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view1=mInflater.inflate(R.layout.activity_owenrupdata,null);
                    queue1= Volley.newRequestQueue(view1.getContext());
                    initView(view1);
                    initOldData(i);
                    AlertDialog.Builder builder=new AlertDialog.Builder(view1.getContext());
                    builder.setView(view1);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            initNewData(i);
                            String up_id=bean.getId();
                            String up_phone=bean.getTelephone();
                            String url_updata= ShunTuApplication.URL+"info/update?id="+up_id+"&strtime="+up_time+"&startPlace="
                                    +up_qidian+"&destination="+up_zhongdian+"&number="+up_renshu+"&telephone="+up_phone;
                            Log.e("修改信息",url_updata);
                            upData(url_updata);
                            return;

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
            holder.shanchu_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                    builder.setTitle("提示");
                    builder.setMessage("是否删除记录");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String delete=bean.getId();
                            String url_delete=ShunTuApplication.URL+"info/delete?ids="+delete;
                            historyRecordAdapter.notifyDataSetChanged();
                            Log.e("删除信息",url_delete);
                            deleteData(url_delete);
                            return;
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
        }

        //Volly请求删除数据
        private void deleteData(String url_delete) {
            final StringRequest delete_data=new StringRequest(Request.Method.POST, url_delete,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s != null){
                                Gson gson=new Gson();
                                delete_dataBeen=gson.fromJson(s,OwenrDeleteBeen.class);
                                if ("ok".equals(up_dataBean.getStatus())){
                                    Toast.makeText(OwenrHistoryRecordActivity.this,"信息删除成功,下拉刷新试试",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(OwenrHistoryRecordActivity.this,"信息删除失败",Toast.LENGTH_SHORT).show();
                        }
                    });
            queue1.add(delete_data);
        }

        //Volly请求修改的数据
        private void upData(String url_updata) {
            StringRequest up_owenrjilu=new StringRequest(Request.Method.POST, url_updata,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s != null){
                                Gson gson=new Gson();
                                up_dataBean=gson.fromJson(s,OwenrUpDataBeen.class);
                                if ("ok".equals(up_dataBean.getStatus())){
                                    Toast.makeText(view1.getContext(),"信息修改成功,下拉刷新试试",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(view1.getContext(),"信息修改失败",Toast.LENGTH_SHORT).show();
                        }
                    });
            queue1.add(up_owenrjilu);
        }

        //获取修改的信息
        private void initNewData(int i) {
            up_time=up_time_et.getText().toString();
            up_qidian=up_qidian_et.getText().toString();
            up_zhongdian=up_zhongdian_et.getText().toString();
            up_renshu=up_renshu_et.getText().toString();
        }

        //获取原本旧的信息
        private void initOldData(int i) {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            long time=new Long(bean.getTime());
            old_time=sdf.format(time);
            old_qidian=bean.getStartPlace();
            old_zhongdian=bean.getDestination();
            old_renshu=bean.getNumber();
            up_time_et.setText(old_time.toCharArray(),0,old_time.length());
            up_qidian_et.setText(old_qidian);
            up_zhongdian_et.setText(old_zhongdian);
            up_renshu_et.setText(old_renshu);
        }

        //修改界面控件
        private void initView(final View view1) {
            up_time_et= (EditText) view1.findViewById(R.id.up_time_et);
            up_qidian_et= (EditText) view1.findViewById(R.id.up_qidian_et);
            up_zhongdian_et= (EditText) view1.findViewById(R.id.up_zhongdian_et);
            up_renshu_et= (EditText) view1.findViewById(R.id.up_renshu_et);

            up_time_et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                    String up_date=sdf.format(new Date(System.currentTimeMillis()));
                    DatePickerUtil pickerUtil=new DatePickerUtil(OwenrHistoryRecordActivity.this,up_date);
                    pickerUtil.dateTimePicKDialog(up_time_et);
                }
            });
        }

        public class viewHolder{
            TextView time_et,qidian_et,zhongdian_et,owenrrenshu_et;
            ImageView xiugai_btn,shanchu_btn;
        }
    }
}