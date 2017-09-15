package com.example.jacciik.mytaomaoduobao.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.jacciik.mytaomaoduobao.Bean.Data;
import com.example.jacciik.mytaomaoduobao.Bean.VersionBean;
import com.example.jacciik.mytaomaoduobao.NetWork.RetrofitHelper;
import com.example.jacciik.mytaomaoduobao.R;
import com.example.jacciik.mytaomaoduobao.Utils.ConstantUtil;
import com.example.jacciik.mytaomaoduobao.Utils.PreferenceUtil;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.tag;


public class MainActivity extends FragmentActivity {

    private FragmentMain mainFragment; //首页fragment
    private FragmentMessage messageFragment; //webview fragment
    private FragmentPersonIndex personFragment; //个人信息fragment


    BottomBar bottomBar;

    String person = "person";
    String index = "index";
    String message = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmdb_activity_main);
        addFragment("index");
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        //为bottomBar设置监听
        bottomBar.setOnTabSelectListener((@IdRes int tabId) -> {
            switch (tabId) {
                case R.id.index:
                    addFragment(index);
                    break;
                case R.id.message:
                    addFragment(message);
                    break;
                case R.id.person:
                    addFragment(person);
                    break;
            }

        });

    }

    @Override
    public void onBackPressed() {
        //获取当前选中的页签
        int tabNum = bottomBar.getCurrentTabId();
        if (tabNum != R.id.index) {

        } else {
            super.onBackPressed();
        }
    }

    //添加Fragment
    public void addFragment(String tag) {
        // 开启事务
        FragmentTransaction beginTransaction = getSupportFragmentManager()
                .beginTransaction();
        //判断fragment是否存在
        if (tag.equals(index))
        {
            if (mainFragment != null) {
                hideFragment(beginTransaction);
                beginTransaction.show(mainFragment);}
                else {
                    mainFragment = FragmentMain.newInstance();
                    beginTransaction.add(R.id.contentContainer, mainFragment);
                }
            }

            if (tag.equals(message)) {

                messageFragment = FragmentMessage.newInstance();
                beginTransaction.add(R.id.contentContainer, messageFragment);
            }


            if (tag.equals(person)) {
                if (personFragment != null) {
                    hideFragment(beginTransaction);
                    beginTransaction.show(personFragment);
                } else {
                    personFragment = FragmentPersonIndex.newInstance();
                    beginTransaction.add(R.id.contentContainer, personFragment);
                }
            }

            beginTransaction.commit();
        }

    @Override
    protected void onResume() {
        super.onResume();
        if (bottomBar.getCurrentTabId() == R.id.person) {
            personFragment.initUserInfo();
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (personFragment != null) {
            transaction.hide(personFragment);
        }
    }

    }



