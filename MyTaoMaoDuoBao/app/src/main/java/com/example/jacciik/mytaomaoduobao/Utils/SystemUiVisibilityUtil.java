package com.example.jacciik.mytaomaoduobao.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.example.jacciik.mytaomaoduobao.R;

/**
 * Created by WangHengJie<522716844@qq.com> on 2017/4/1.
 * <p>
 * StatusBar隐藏显示工具类
 */

public class SystemUiVisibilityUtil {

    private SystemUiVisibilityUtil(){

    }

    /**
     * * 显示或隐藏StatusBar
     *
     * @param enable false 显示，true 隐藏
     */
    public static void hideStatusBar(Window window, boolean enable) {

        WindowManager.LayoutParams p = window.getAttributes();
        if (enable)
        //|=：或等于，取其一
        {
            p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else
        //&=：与等于，取其二同时满足，     ~ ： 取反
        {
            p.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        window.setAttributes(p);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();//获取屏幕宽度
        return width;
    }

    public static void setStatusBarColor(Activity activity){
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(R.color.black));
    }
}
