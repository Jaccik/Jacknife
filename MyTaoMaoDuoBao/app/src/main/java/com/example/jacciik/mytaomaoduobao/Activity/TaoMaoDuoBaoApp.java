package com.example.jacciik.mytaomaoduobao.Activity;

import android.app.Application;
import android.content.Context;


/**
 * Created by WangHengJie<522716844@qq.com> on 2017/4/1.
 */

public class TaoMaoDuoBaoApp extends Application {
    String TAG = "TaoMaoDuoBaoApp";
    public static TaoMaoDuoBaoApp taoMaoDuoBaoApp;

    public static final String NAMESPACE = "taomaoduobao";

    //云旺OpenIM的DEMO用到的Application上下文实例
    private static Context mContext;

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        taoMaoDuoBaoApp = this;
        //阿里云旺初始化
        if(mustRunFirstInsideApplicationOnCreate()){
            //todo 如果在":TCMSSevice"进程中，无需进行openIM和app业务的初始化，以节省内存
            return;
        }

    }

    public static TaoMaoDuoBaoApp getInstance() {
        return taoMaoDuoBaoApp;
    }

    private boolean mustRunFirstInsideApplicationOnCreate() {
        //必须的初始化
        return true;

    }
}
