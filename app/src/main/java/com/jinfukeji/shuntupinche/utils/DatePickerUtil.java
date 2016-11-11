package com.jinfukeji.shuntupinche.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jinfukeji.shuntupinche.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by "于志渊"
 * 时间:"18:20"
 * 包名:com.jinfukeji.shuntupinche.utils
 * 描述:日期时间选择控件 使用方法： private EditText inputDate;//需要设置的日期时间文本编辑框 private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) { DateTimePickDialogUtil
 *           dateTimePicKDialog=new
 *           DateTimePickDialogUtil(SinvestigateActivity.this,initDateTime);
 *           dateTimePicKDialog.dateTimePicKDialog(inputDate);
 *
 *           } });
 */

public class DatePickerUtil implements DatePicker.OnDateChangedListener{
    private DatePicker datePicker;
    private AlertDialog ad;
    private String date,initDate;
    private Activity activity;

    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity
     *            ：调用的父activity
     * @param /initDate
     *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
     */
    public DatePickerUtil(Activity activity,String initDate) {
        this.activity = activity;
        this.initDate=initDate;
    }

    public void init(DatePicker datePicker){
        Calendar calendar=Calendar.getInstance();
        if (!(initDate == null || "".equals(initDate))){
            calendar=this.getCalendarByInintData(initDate);
        }else {
            initDate=calendar.get(Calendar.YEAR)+"年"+
                    calendar.get(Calendar.MONTH)+"月"+
                    calendar.get(Calendar.DAY_OF_MONTH)+"日";
        }
        datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),this);
    }

    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @param initDate
     *            初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInintData(String initDate) {
        Calendar calendar=Calendar.getInstance();
        String date=spliteString(initDate, "日", "index", "front"); // 日期

        String yearStr = spliteString(date, "年", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();

        calendar.set(currentYear,currentMonth,currentDay);
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr
     *            源串
     * @param pattern
     *            匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern, String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")){
            loc=srcStr.indexOf(pattern);// 取得字符串第一次出现的位置
        }else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

    @Override
    public void onDateChanged(DatePicker view, int i, int i1, int i2) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        date=sdf.format(calendar.getTime());
        ad.setTitle(date);
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @param inputDate
     *            :为需要设置的日期时间文本编辑框
     * @return
     */
    public AlertDialog dateTimePicKDialog(final EditText inputDate){
        LinearLayout dateLinearLayout= (LinearLayout) activity.getLayoutInflater().inflate(R.layout.utils_date,null);
        datePicker= (DatePicker) dateLinearLayout.findViewById(R.id.datepicker);
        init(datePicker);

        ad = new AlertDialog.Builder(activity)
                .setView(dateLinearLayout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText(date);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText("");
                    }
                }).show();
        onDateChanged(null,0,0,0);
        return ad;
    }
}
