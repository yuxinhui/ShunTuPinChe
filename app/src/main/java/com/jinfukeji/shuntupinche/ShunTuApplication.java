package com.jinfukeji.shuntupinche;

import android.app.Application;

/**
 * Created by "于志渊"
 * 时间:"9:28"
 * 包名:com.jinfukeji.shuntupinche
 * 描述:存放全局变量
 */

public class ShunTuApplication extends Application {
    public boolean isLogin=false;
    public static ShunTuApplication instace;
//    public static final String URL="http://test.jinfully.com/";
    public static final String URL="http://192.168.1.148:8080/carpool/";

    @Override
    public void onCreate() {
        super.onCreate();
        instace=this;
    }

    public static ShunTuApplication getInstace() {
        return instace;
    }

    public static void setInstace(ShunTuApplication instace) {
        ShunTuApplication.instace = instace;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public static String getURL() {
        return URL;
    }
    public void unLoginclear(){
        isLogin=false;
    }
}
