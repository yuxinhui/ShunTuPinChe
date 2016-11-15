package com.jinfukeji.shuntupinche.activity.owenr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.activity.owenr.seletephoto.OwenrVerifySeletePhoto;
import com.jinfukeji.shuntupinche.activity.owenr.seletephoto.OwenrVerifySeletePhoto1;
import com.jinfukeji.shuntupinche.activity.owenr.seletephoto.OwenrVerifySeletePhoto2;
import com.jinfukeji.shuntupinche.activity.owenr.seletephoto.OwenrVerifySeletePhoto3;
import com.jinfukeji.shuntupinche.activity.owenr.seletephoto.OwenrVerifySeletePhoto4;
import com.jinfukeji.shuntupinche.activity.owenr.seletephoto.OwenrVerifySeletePhoto5;
import com.jinfukeji.shuntupinche.utils.seletephotoutil.IHeaderView;
import com.jinfukeji.shuntupinche.utils.seletephotoutil.IHeaderView1;
import com.jinfukeji.shuntupinche.utils.seletephotoutil.IHeaderView2;
import com.jinfukeji.shuntupinche.utils.seletephotoutil.IHeaderView3;
import com.jinfukeji.shuntupinche.utils.seletephotoutil.IHeaderView4;
import com.jinfukeji.shuntupinche.utils.seletephotoutil.IHeaderView5;

import java.io.File;

/**
 * Created by "于志渊"
 * 时间:"16:26"
 * 包名:com.jinfukeji.shuntupinche.activity.owenr
 * 描述:车主身份验证界面
 */

public class OwenrVerifyActivity extends AppCompatActivity implements IHeaderView,IHeaderView1,IHeaderView2,IHeaderView3,IHeaderView4,IHeaderView5{
    private OwenrVerifySeletePhoto verifySeletePhoto;
    private OwenrVerifySeletePhoto1 verifySeletePhoto1;
    private OwenrVerifySeletePhoto2 verifySeletePhoto2;
    private OwenrVerifySeletePhoto3 verifySeletePhoto3;
    private OwenrVerifySeletePhoto4 verifySeletePhoto4;
    private OwenrVerifySeletePhoto5 verifySeletePhoto5;
    private ImageView verify_shenfen_zheng_img,verify_shenfen_fan_img,verify_jiashi_zheng_img,verify_jiashi_fan_img,verify_chexing_zheng_img,verify_chexing_fan_img,verify_return;
    private Button verify_tijiao_but;
    String shenfen_zheng,shenfen_fan,jiashi_zheng,jiashi_fan,chexing_zheng,chexing_fan,oid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owenrverify);
        Intent intent=getIntent();
        oid=intent.getStringExtra("oid");
        verifySeletePhoto=new OwenrVerifySeletePhoto(this,this);
        verifySeletePhoto1=new OwenrVerifySeletePhoto1(this,this);
        verifySeletePhoto2=new OwenrVerifySeletePhoto2(this,this);
        verifySeletePhoto3=new OwenrVerifySeletePhoto3(this,this);
        verifySeletePhoto4=new OwenrVerifySeletePhoto4(this,this);
        verifySeletePhoto5=new OwenrVerifySeletePhoto5(this,this);
        initView();
        onClick();
    }

    //初始化控件
    private void initView() {
        verify_return= (ImageView) findViewById(R.id.verify_return);
        verify_tijiao_but= (Button) findViewById(R.id.verify_tijiao_but);
        verify_shenfen_zheng_img= (ImageView) findViewById(R.id.verify_shenfen_zheng_img);
        verify_shenfen_fan_img= (ImageView) findViewById(R.id.verify_shenfen_fan_img);
        verify_jiashi_zheng_img= (ImageView) findViewById(R.id.verify_jiashi_zheng_img);
        verify_jiashi_fan_img= (ImageView) findViewById(R.id.verify_jiashi_fan_img);
        verify_chexing_zheng_img= (ImageView) findViewById(R.id.verify_chexing_zheng_img);
        verify_chexing_fan_img= (ImageView) findViewById(R.id.verify_chexing_fan_img);
    }

    //图片点击事件
    private void onClick() {
        verify_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),OwenrIndexActivity.class);
                startActivity(intent);
            }
        });
        verify_shenfen_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySeletePhoto.showSeletePhoto();
            }
        });
        verify_shenfen_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySeletePhoto1.showSeletePhoto();
            }
        });
        verify_chexing_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySeletePhoto5.showSeletePhoto();
            }
        });
        verify_chexing_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySeletePhoto4.showSeletePhoto();
            }
        });
        verify_jiashi_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySeletePhoto3.showSeletePhoto();
            }
        });
        verify_jiashi_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySeletePhoto2.showSeletePhoto();
            }
        });
        verify_tijiao_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    //获取数据
    private void initData() {
        shenfen_zheng=verify_shenfen_zheng_img.getContext().toString();
        shenfen_fan=verify_shenfen_fan_img.getContext().toString();
        jiashi_zheng=verify_jiashi_zheng_img.getContext().toString();
        jiashi_fan=verify_jiashi_fan_img.getContext().toString();
        chexing_zheng=verify_chexing_zheng_img.getContext().toString();
        chexing_fan=verify_chexing_fan_img.getContext().toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 点击取消按钮
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case 2:
                Uri uri = data.getData();
                verifySeletePhoto.startPhotoZoom(uri);
                break;
            case 3:
                File file = new File(Environment.getExternalStorageDirectory() + "/" + "iMon.jpg");
                verifySeletePhoto.startPhotoZoom(Uri.fromFile(file));
                break;
            case 4:
                if (data != null) {
                    verifySeletePhoto.setView(data);
                }
                break;
            case 5:
                Uri uri1 = data.getData();
                verifySeletePhoto1.startPhotoZoom(uri1);
                break;
            case 6:
                File file1 = new File(Environment.getExternalStorageDirectory() + "/" + "iMon.jpg");
                verifySeletePhoto1.startPhotoZoom(Uri.fromFile(file1));
                break;
            case 7:
                if (data != null) {
                    verifySeletePhoto1.setView(data);
                }
                break;
            case 8:
                Uri uri2 = data.getData();
                verifySeletePhoto2.startPhotoZoom(uri2);
                break;
            case 9:
                File file2 = new File(Environment.getExternalStorageDirectory() + "/" + "iMon.jpg");
                verifySeletePhoto2.startPhotoZoom(Uri.fromFile(file2));
                break;
            case 10:
                if (data != null) {
                    verifySeletePhoto2.setView(data);
                }
                break;
            case 11:
                Uri uri3 = data.getData();
                verifySeletePhoto3.startPhotoZoom(uri3);
                break;
            case 12:
                File file3 = new File(Environment.getExternalStorageDirectory() + "/" + "iMon.jpg");
                verifySeletePhoto3.startPhotoZoom(Uri.fromFile(file3));
                break;
            case 13:
                if (data != null) {
                    verifySeletePhoto3.setView(data);
                }
                break;
            case 14:
                Uri uri4 = data.getData();
                verifySeletePhoto4.startPhotoZoom(uri4);
                break;
            case 15:
                File file4 = new File(Environment.getExternalStorageDirectory() + "/" + "iMon.jpg");
                verifySeletePhoto4.startPhotoZoom(Uri.fromFile(file4));
                break;
            case 16:
                if (data != null) {
                    verifySeletePhoto4.setView(data);
                }
                break;
            case 17:
                Uri uri5 = data.getData();
                verifySeletePhoto5.startPhotoZoom(uri5);
                break;
            case 18:
                File file5 = new File(Environment.getExternalStorageDirectory() + "/" + "iMon.jpg");
                verifySeletePhoto5.startPhotoZoom(Uri.fromFile(file5));
                break;
            case 19:
                if (data != null) {
                    verifySeletePhoto5.setView(data);
                }
                break;
        }
    }

    @Override
    public void setHeaderBitmap(Bitmap bitmap) {
        verify_shenfen_zheng_img.setImageBitmap(bitmap);
    }

    @Override
    public void setHeaderBitmap1(Bitmap bitmap) {
        verify_shenfen_fan_img.setImageBitmap(bitmap);
    }

    @Override
    public void setHeaderBitmap2(Bitmap bitmap) {
        verify_jiashi_zheng_img.setImageBitmap(bitmap);
    }

    @Override
    public void setHeaderBitmap3(Bitmap bitmap) {
        verify_jiashi_fan_img.setImageBitmap(bitmap);
    }

    @Override
    public void setHeaderBitmap4(Bitmap bitmap) {
        verify_chexing_zheng_img.setImageBitmap(bitmap);
    }

    @Override
    public void setHeaderBitmap5(Bitmap bitmap) {
        verify_chexing_fan_img.setImageBitmap(bitmap);
    }
}
