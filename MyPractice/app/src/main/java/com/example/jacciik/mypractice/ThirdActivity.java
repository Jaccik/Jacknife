package com.example.jacciik.mypractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent x=getIntent();
        tv1=(TextView) findViewById(R.id.textView2);
//        tv1.setText(x.getStringExtra("kk"));

    }
}
