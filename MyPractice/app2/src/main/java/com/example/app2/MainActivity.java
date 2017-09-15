package com.example.app2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
private Intent serviceintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceintent=new Intent();
        serviceintent.setComponent(new ComponentName("com.example.jacciik.mypractice","com.example.jacciik.mypractice.MyService"));
  findViewById(R.id.btnStartAnotherAty).setOnClickListener(this);
        findViewById(R.id.btnstartapp2).setOnClickListener(this);
        findViewById(R.id.btnstopapp2).setOnClickListener(this);
        findViewById(R.id.btnbindservice).setOnClickListener(this);
        findViewById(R.id.btnunbindservice).setOnClickListener(this);
  }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnstartapp2:
            {
                startService(serviceintent);
                break;
            }
            case R.id.btnstopapp2:
            {
                stopService(serviceintent);
                break;
            }
            case R.id.btnbindservice:
            {
                bindService(serviceintent,this, BIND_AUTO_CREATE);
                break;
            }
            case R.id.btnunbindservice:
            {
                unbindService(this);
            }
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("service binded!");
        System.out.println(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("service unbinded!");
    }
}
