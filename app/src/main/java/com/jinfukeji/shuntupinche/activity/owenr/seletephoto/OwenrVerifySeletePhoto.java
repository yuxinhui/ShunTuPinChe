package com.jinfukeji.shuntupinche.activity.owenr.seletephoto;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jinfukeji.shuntupinche.R;
import com.jinfukeji.shuntupinche.utils.seletephotoutil.IHeaderView;

import java.io.File;

/**
 * Created by "于志渊"
 * 时间:"11:08"
 * 包名:com.jinfukeji.shuntupinche.activity.owenr
 * 描述:
 */

public class OwenrVerifySeletePhoto {
    private IHeaderView iHeaderView;
    private Context context;
    public static final String KEY_PHOTO_PATH = "photo_path";

    public OwenrVerifySeletePhoto(IHeaderView iHeaderView, Context context) {
        this.iHeaderView = iHeaderView;
        this.context = context;
    }

    /*设置并显示Dialog*/
    public void showSeletePhoto(){
        View view = LayoutInflater.from(context).inflate(R.layout.activity_verifyphoto, null);
        final Dialog dialog = new Dialog(context, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window=dialog.getWindow();
        window.setWindowAnimations(R.style.anim_style);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.x = 0;
        layoutParams.y = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT; //保证dialog窗体可以水平铺满
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        dialog.onWindowAttributesChanged(layoutParams);//设置dialog的摆放位置
        dialog.setCanceledOnTouchOutside(true);//设置点击dialog以为的区域dialog消失
        dialog.show();

        /*相册选择*/
        dialog.findViewById(R.id.select_photo_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFromAlbum();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        /*拍照*/
        dialog.findViewById(R.id.take_photo_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //拍照
    private void takePhoto() {
        String state = Environment.getExternalStorageState();
        /*判断是否有SD卡*/
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
                    new File(Environment.getExternalStorageDirectory(), "iMon.jpg")));
            ((Activity) context).startActivityForResult(intent, 3);
        } else {
            Toast.makeText(context, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    //系统图片
    private void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        ((Activity) context).startActivityForResult(intent, 2);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 0.7);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 210);
        intent.putExtra("return-data", true);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 4);
        }
    }

    public void setView(Intent date) {
        Bundle bundle = date.getExtras();
        Bitmap bitmap = bundle.getParcelable("data");
        iHeaderView.setHeaderBitmap(bitmap);
    }
}
