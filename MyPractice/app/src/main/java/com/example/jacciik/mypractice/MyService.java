package com.example.jacciik.mypractice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import static android.R.attr.data;

public class MyService extends Service {
    private String edata="这是默认信息";
    private boolean Running=false;

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
   return new Binder();

    }
    public class Binder extends android.os.Binder{
        public void setData(String edata){
            MyService.this.edata=edata;
        }
        public MyService getMyservice(){
            return MyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        edata=intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service started!");
        Running=true;
        new Thread(){
            @Override
            public void run() {
                int i=0;
                String str = null;
                super.run();
                while (Running){
                    i++;
                    str=i+"次："+edata;
                    System.out.println(str);
                    if(callback!=null){
                        callback.OnDataChange(str);
                    }
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service stopped!");
        Running=false;
    }
    private CallBack callback;

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    public CallBack getCallback() {
        return callback;
    }

    public static interface CallBack{
        public void OnDataChange(String edata);
    }
}
