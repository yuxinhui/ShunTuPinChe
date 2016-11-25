package com.jinfukeji.shuntupinche.activity.owenr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.ShunTuApplication;
import com.jinfukeji.shuntupinche.utils.UpLoadUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by "于志渊"
 * 时间:"16:26"
 * 包名:com.jinfukeji.shuntupinche.activity.owenr
 * 描述:车主身份验证界面
 */

public class OwenrVerifyActivity extends AppCompatActivity{
    private ImageView verify_shenfen_zheng_img,verify_shenfen_fan_img,verify_jiashi_zheng_img,verify_jiashi_fan_img,verify_chexing_zheng_img,verify_chexing_fan_img,verify_return;
    private Button verify_tijiao_but;
    String oid;

    // 服务器地址
    String HOST ;
    private String IDPicPath = null,IDPicfPath = null, drivingPicPath =null, drivingPicfPath =null, carPicPath =null, carPicfPath =null;
    private boolean isUpLoad=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owenrverify);
        /*Intent intent=getIntent();
        oid=intent.getStringExtra("oid");*/
        SharedPreferences sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        oid=sp.getString("id","");
        Log.e("车主ID",oid);
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
                finish();
            }
        });
        verify_shenfen_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheetDialog(OwenrVerifyActivity.this).builder().setTitle("提示")
                        .setCancelable(false).setCanceledOnTouchOutside(false)
                        .addSheetItem("拍照上传", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //拍照，设置图片的保存路径作为全局变量
                                IDPicPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/shenfenzheng.jpg";
                                Log.e("身份拍照路径", IDPicPath);
                                File temp = new File(IDPicPath);
                                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                                startActivityForResult(it,111);
                            }
                        }).show();
            }
        });
        verify_shenfen_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheetDialog(OwenrVerifyActivity.this).builder().setTitle("提示")
                        .setCancelable(false).setCanceledOnTouchOutside(false)
                        .addSheetItem("拍照上传", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //拍照，设置图片的保存路径作为全局变量
                                IDPicfPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/shenfenfan.jpg";
                                Log.e("身份反拍照路径", IDPicfPath);
                                File temp = new File(IDPicfPath);
                                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                                startActivityForResult(it,121);
                            }
                        }).show();
            }
        });
        verify_jiashi_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheetDialog(OwenrVerifyActivity.this).builder().setTitle("提示")
                        .setCancelable(false).setCanceledOnTouchOutside(false)
                        .addSheetItem("拍照上传", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //拍照，设置图片的保存路径作为全局变量
                                drivingPicPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/jiashizheng.jpg";
                                Log.e("驾驶正拍照路径", drivingPicPath);
                                File temp = new File(drivingPicPath);
                                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                                startActivityForResult(it,131);
                            }
                        }).show();
            }
        });
        verify_jiashi_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheetDialog(OwenrVerifyActivity.this).builder().setTitle("提示")
                        .setCancelable(false).setCanceledOnTouchOutside(false)
                        .addSheetItem("拍照上传", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //拍照，设置图片的保存路径作为全局变量
                                drivingPicfPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/jiashifan.jpg";
                                Log.e("驾驶反拍照路径", drivingPicfPath);
                                File temp = new File(drivingPicfPath);
                                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                                startActivityForResult(it,141);
                            }
                        }).show();
            }
        });
        verify_chexing_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheetDialog(OwenrVerifyActivity.this).builder().setTitle("提示")
                        .setCancelable(false).setCanceledOnTouchOutside(false)
                        .addSheetItem("拍照上传", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //拍照，设置图片的保存路径作为全局变量
                                carPicPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/chexingzheng.jpg";
                                Log.e("车型正拍照路径", carPicPath);
                                File temp = new File(carPicPath);
                                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                                startActivityForResult(it,151);
                            }
                        }).show();
            }
        });
        verify_chexing_fan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheetDialog(OwenrVerifyActivity.this).builder().setTitle("提示")
                        .setCancelable(false).setCanceledOnTouchOutside(false)
                        .addSheetItem("拍照上传", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //拍照，设置图片的保存路径作为全局变量
                                carPicfPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/chexingfan.jpg";
                                Log.e("车型反拍照路径", carPicfPath);
                                File temp = new File(carPicfPath);
                                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                                startActivityForResult(it,161);
                            }
                        }).show();
                        /*.addSheetItem("相册选择", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 相册选取
                                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
                                startActivityForResult(intent1, 162);
                            }
                        })*/
            }
        });
        verify_tijiao_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (IDPicPath == null || IDPicfPath == null || drivingPicPath == null || drivingPicfPath == null || carPicPath == null || carPicfPath == null){
                        Toast.makeText(OwenrVerifyActivity.this, "请选择图片！", 2000).show();
                    }else {
                        final File IDPicfile = new File(IDPicPath);
                        final File IDPicffile = new File(IDPicfPath);
                        final File drivingPicfile=new File(drivingPicPath);
                        final File drivingPicffile=new File(drivingPicfPath);
                        final File carPicfile=new File(carPicPath);
                        final File carPicffile=new File(carPicfPath);
                        final Map map = new HashMap();
                        map.put("IDPic",IDPicfile);
                        map.put("IDPicf",IDPicffile);
                        map.put("drivingPic",drivingPicfile);
                        map.put("drivingPicf",drivingPicffile);
                        map.put("carPic",carPicfile);
                        map.put("carPicf",carPicffile);
                        HOST= ShunTuApplication.URL+"authentic/upOwnerPic?oid="+oid+"&IDPic=&IDPicf=?drivingPic=&drivingPicf=&carPic=&carPicf=";
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                UpLoadUtil.postFile(HOST,map);
                            }
                        }).start();
                        Toast.makeText(OwenrVerifyActivity.this,"资料已上传，正在等待审核......\r\n请勿重复提交",Toast.LENGTH_LONG).show();
                    }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 111:
                if (resultCode == Activity.RESULT_OK){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=2;//图片宽高都为原来的二分之一，即图片为原来的四分之一
                    Bitmap bitmap=BitmapFactory.decodeFile(IDPicPath,options);
                    verify_shenfen_zheng_img.setImageBitmap(bitmap);
                }
                break;
            /*case 112:
                Bitmap bitmap=null;
                // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
                ContentResolver resolver=getContentResolver();
                try {
                    Uri originalUri = data.getData(); // 获得图片的uri
                    bitmap=MediaStore.Images.Media.getBitmap(resolver,originalUri);// 显得到bitmap图片
                    // 这里开始的第二部分，获取图片的路径：
                    String[] proj = { MediaStore.Images.Media.DATA };
                    // 好像是android多媒体数据库的封装接口，具体的看Android文档
                    @SuppressWarnings("deprecation")
                    Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                    // 按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    // 最后根据索引值获取图片路径
                    IDPicPath = cursor.getString(column_index);
                    verify_shenfen_zheng_img.setImageURI(originalUri);
                    Log.e("身份正", IDPicPath);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;*/
            case 121:
                if (resultCode == Activity.RESULT_OK){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    Bitmap bitmap2=BitmapFactory.decodeFile(IDPicfPath,options);
                    verify_shenfen_fan_img.setImageBitmap(bitmap2);
                }
                break;
            case 131:
                if (resultCode == Activity.RESULT_OK){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    Bitmap bitmap3=BitmapFactory.decodeFile(drivingPicPath,options);
                    verify_jiashi_zheng_img.setImageBitmap(bitmap3);
                }
                break;
            case 141:
                if (resultCode == Activity.RESULT_OK){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    Bitmap bitmap4=BitmapFactory.decodeFile(drivingPicfPath,options);
                    verify_jiashi_fan_img.setImageBitmap(bitmap4);
                }
                break;
            case 151:
                if (resultCode == Activity.RESULT_OK){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    Bitmap bitmap5=BitmapFactory.decodeFile(carPicPath,options);
                    verify_chexing_zheng_img.setImageBitmap(bitmap5);
                }
                break;
            case 161:
                if (resultCode == Activity.RESULT_OK){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    Bitmap bitmap6=BitmapFactory.decodeFile(carPicfPath,options);
                    verify_chexing_fan_img.setImageBitmap(bitmap6);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
