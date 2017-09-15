package com.example.jacciik.mytaomaoduobao.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jacciik.mytaomaoduobao.Utils.SystemUiVisibilityUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by WangHengJie<522716844@qq.com> on 2017/4/1.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUiVisibilityUtil.setStatusBarColor(this);
        setContentView(getLayoutId());
        //绑定ButterKnife控件
        binder = ButterKnife.bind(this);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        binder.unbind();
    }
}

