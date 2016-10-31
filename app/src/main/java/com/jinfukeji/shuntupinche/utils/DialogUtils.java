package com.jinfukeji.shuntupinche.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by "于志渊"
 * 时间:"11:36"
 * 包名:com.jinfukeji.shuntupinche.utils
 * 描述:
 */

public class DialogUtils {
    public static void createAlertDialog(Context context, String title, String msg, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener clickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(msg).setPositiveButton("确定",onClickListener).setNegativeButton("取消", clickListener).create().show();
    }

    public static void createToasdt(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
