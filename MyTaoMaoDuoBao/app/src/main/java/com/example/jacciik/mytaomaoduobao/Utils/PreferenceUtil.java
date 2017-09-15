package com.example.jacciik.mytaomaoduobao.Utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.jacciik.mytaomaoduobao.Activity.TaoMaoDuoBaoApp;

/**
 * Created by WangHengJie<522716844@qq.com> on 2017/4/1.
 * <p/>
 * SP缓存工具类
 */

public class PreferenceUtil {

    private PreferenceUtil(){

    }

    public static boolean getBoolean(String key, boolean defValue) {

        return PreferenceManager.getDefaultSharedPreferences(TaoMaoDuoBaoApp.getInstance())
                .getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                TaoMaoDuoBaoApp.getInstance());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        editor.commit();
    }

    public static void putString(String key , String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                TaoMaoDuoBaoApp.getInstance());
        //获取Editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //写入登录名
        editor.putString(key , value);
        editor.apply();
        editor.commit();
    }

    public static String getString(String key, String defValue) {

        return PreferenceManager.getDefaultSharedPreferences(TaoMaoDuoBaoApp.getInstance())
                .getString(key, defValue);
    }

    public static void clearLoginData(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                TaoMaoDuoBaoApp.getInstance());
        //获取Editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();

    }

}
