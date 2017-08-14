package com.example.jacciik.mytaomaoduobao.Activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;

//import com.alibaba.wxlib.util.SysUtil;

import com.example.jacciik.mytaomaoduobao.DAO.GreenDaoManager;


import org.xutils.x;




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
        taoMaoDuoBaoApp = this;
        //初始化xUtils
        x.Ext.init(this);
        //初始化GreenDaoManager
        GreenDaoManager.getInstance();

        //电商SDK初始化
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                //回调函数
            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(TaoMaoDuoBaoApp.this, "初始化失败,错误码=" + code + " / 错误消息=" + msg, Toast.LENGTH_SHORT).show();
            }
        });

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
       /* SysUtil.setApplication(this);
        mContext = getApplicationContext();
        return SysUtil.isTCMSServiceProcess(mContext);*/
       return  true;
    }
}
