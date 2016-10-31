package com.jinfukeji.shuntupinche.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by "于志渊"
 * 时间:"10:39"
 * 包名:com.jinfukeji.shuntupinche.utils
 * 描述:获取验证码时间工具
 */

public class VercodeTime extends CountDownTimer {
    private TextView tv;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public VercodeTime(long millisInFuture, long countDownInterval,TextView tv) {
        super(millisInFuture, countDownInterval);
        int seconds= (int) (millisInFuture/1000);
        int interval= (int) (countDownInterval/1000);
        this.tv=tv;
    }

    @Override
    public void onTick(long l) {
        tv.setClickable(false);
        tv.setText((l/1000)+"秒后重试");
    }

    @Override
    public void onFinish() {
        tv.setText("重新获取验证码");
        tv.setClickable(true);
    }
}
