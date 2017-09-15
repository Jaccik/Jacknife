package com.example.jacciik.mypractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {
    TextView tv;
    EditText editText;
//    Button bt1 ;
//final static String action="hello";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        tv= (TextView) findViewById(R.id.text);
        editText= (EditText) findViewById(R.id.editText);
        tv.setText("共享的数据是："+((App)getApplicationContext()).getData());
        findViewById(R.id.btnSaveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App)getApplicationContext()).setData(editText.getText().toString());
                tv.setText("共享的数据是："+editText.getText().toString());
            }
        });
    }
}
