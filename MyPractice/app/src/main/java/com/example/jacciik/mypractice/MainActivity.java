package com.example.jacciik.mypractice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    EditText editText;
    TextView tv_show;
    TextView tvOut;
    private MyService.Binder Binder;
    private String Tag="我是信息";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (TextView) findViewById(R.id.tvout);
        tv_show = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.editText);
        tv_show.setText("共享的数据是：" + ((App) getApplicationContext()).getData());
        findViewById(R.id.btnstart).setOnClickListener(this);
        findViewById(R.id.btnstop).setOnClickListener(this);
        findViewById(R.id.btnSaveData).setOnClickListener(this);
        findViewById(R.id.btnunbindservice).setOnClickListener(this);
        findViewById(R.id.btnsyncservice).setOnClickListener(this);
        findViewById(R.id.btnbindservice).setOnClickListener(this);
        System.out.println("普通信息");
        System.err.println("警告信息");
        Log.d(Tag,"调试信息");
        Log.e(Tag,"错误信息");
        Log.i(Tag,"输出信息");
        Log.w(Tag,"警告信息");
        Log.v(Tag,"无用信息");

//        setContentView(R.layout.activity_main);
//        bt=(Button) findViewById(R.id.button);
//        tv_show= (TextView) findViewById(R.id.text);
        //设置tv_show的环境资源
//        image=new ImageView(this);
//        image.setImageResource(R.drawable.ic_head_live);
//        tv_show=new TextView(MainActivity.this);
//        tv_show.setText(R.string.app_name);
//        tv_show.setText(String.format("TaskID:%d \nCurrent Activityid: \n%s",getTaskId(),toString()));
//        setContentView(tv_show);
//        setContentView(image);
//        System.out.println(getResources().getText(R.string.app_name));
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent("hello");
////                i.putExtra("data", "hello world");
//           try{     startActivity(new Intent("hello"));}
//           catch (Exception e){
//               Toast.makeText(MainActivity.this,"权限不够，无法进入",Toast.LENGTH_SHORT).show();
//
//           }
//            }
//        });
      /*  TextView tv_show=new TextView(this);
        tv_show.setText("Hello World");
        setContentView(tv_show);
//        ImageView iv=new ImageView(MainActivity.this);
//        iv.setImageResource(R.drawable.ic_head_live);
//        setContentView(iv);*/
       /* System.out.println("hello");
        Intent i=new Intent();
        setResult(1,i);
        startActivityForResult(i,0);*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnstart: {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("data", editText.getText().toString());
                startService(intent);
                break;
            }
            case R.id.btnstop: {
                stopService(new Intent(MainActivity.this, MyService.class));
                break;
            }
            case R.id.btnbindservice: {
                bindService(new Intent(MainActivity.this, MyService.class), this, Context.BIND_AUTO_CREATE);
                break;
            }
            case R.id.btnunbindservice: {
                unbindService(this);
                break;
            }
            case R.id.btnsyncservice: {
                if (Binder != null) {
                    Binder.setData(editText.getText().toString());
                }
                break;
            }
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Binder = (MyService.Binder) service;
        Binder.getMyservice().setCallback(new MyService.CallBack() {
            @Override
            public void OnDataChange(String edata) {
            Message msg=new Message();
                Bundle b=new Bundle();
                b.putString("data",edata);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvOut.setText(msg.getData().getString("data").toString());
        }
    };

}

