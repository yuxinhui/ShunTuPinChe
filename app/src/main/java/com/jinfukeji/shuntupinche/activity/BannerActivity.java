package com.jinfukeji.shuntupinche.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.fragment.ChaXunFragment;
import com.jinfukeji.shuntupinche.fragment.IndexFragment;
import com.jinfukeji.shuntupinche.fragment.MyFragment;

/**
 * Created by "于志渊"
 * 时间:"16:38"
 * 包名:com.jinfukeji.shuntupinche.activity
 * 描述:底部导航栏
 */

public class BannerActivity extends FragmentActivity implements View.OnClickListener{
    //fragment对象
    private IndexFragment index_fg;
    private ChaXunFragment chaxun_fg;
    private MyFragment my_fg;
    //定义底部导航栏布局
    private RelativeLayout index_layout,chaxun_layout,my_layout;
    //定义导航栏中的控件
    private ImageView index_img,chaxun_img,my_img;
    private TextView index_txt,chaxun_txt,my_txt;
    //FragmentManager对象
    FragmentManager fragmentManager;
    //选中时颜色的切换
    private int bule=0xFF3Cb6EF;
    private int Gray=0xFF9B9999;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        fragmentManager = getSupportFragmentManager();
        //控件初始化
        initView();
        setChioceItem(0);
    }
    //初始化各个控件
    private void initView() {
        index_layout= (RelativeLayout) findViewById(R.id.index_layout);
        chaxun_layout= (RelativeLayout) findViewById(R.id.chaxun_layout);
        my_layout= (RelativeLayout) findViewById(R.id.my_layout);

        index_img= (ImageView) findViewById(R.id.index_img);
        chaxun_img= (ImageView) findViewById(R.id.chaxun_img);
        my_img= (ImageView) findViewById(R.id.my_img);

        index_txt= (TextView) findViewById(R.id.index_txt);
        chaxun_txt= (TextView) findViewById(R.id.chaxun_txt);
        my_txt= (TextView) findViewById(R.id.my_txt);

        index_layout.setOnClickListener(this);
        chaxun_layout.setOnClickListener(this);
        my_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.index_layout:
                setChioceItem(0);
                break;
            case R.id.chaxun_layout:
                setChioceItem(1);
                break;
            case R.id.my_layout:
                setChioceItem(2);
                break;
            default:
                break;
        }
    }
    //定义一个选中一个item后的处理
    public void setChioceItem(int chioceItem) {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        clearColor();
        hideFragments(transaction);
        switch (chioceItem){
            case 0:
                index_txt.setTextColor(bule);
                index_img.setImageResource(R.mipmap.shouye01);
                if (index_fg==null){
                    index_fg=new IndexFragment();
                    transaction.add(R.id.content,index_fg);
                }else {
                    transaction.show(index_fg);
                }
                break;
            case 1:
                chaxun_txt.setTextColor(bule);
                chaxun_img.setImageResource(R.mipmap.chaxun01);
                if (chaxun_fg==null){
                    chaxun_fg= new ChaXunFragment();
                    transaction.add(R.id.content,chaxun_fg);
                }else {
                    transaction.show(chaxun_fg);
                }
                break;
            case 2:
                my_txt.setTextColor(bule);
                my_img.setImageResource(R.mipmap.geren01);
                if (my_fg==null){
                    my_fg=new MyFragment();
                    transaction.add(R.id.content,my_fg);
                }else {
                    transaction.show(my_fg);
                }
                break;
        }
        transaction.commit();
    }
    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (index_fg != null){
            transaction.hide(index_fg);
        }
        if (chaxun_fg != null){
            transaction.hide(chaxun_fg);
        }
        if (my_fg != null){
            transaction.hide(my_fg);
        }
    }

    //选择颜色与图标
    private void clearColor() {
        index_txt.setTextColor(Gray);
        index_img.setImageResource(R.mipmap.shouye02);
        chaxun_txt.setTextColor(Gray);
        chaxun_img.setImageResource(R.mipmap.chaxun02);
        my_txt.setTextColor(Gray);
        my_img.setImageResource(R.mipmap.geren02);
    }
}
