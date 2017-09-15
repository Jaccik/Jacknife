package com.example.jacciik.mytaomaoduobao.Activity;

import android.support.v4.app.Fragment;

/**
 * Created by Jacciik on 2017/8/24.
 */

class FragmentMessage extends Fragment{
    private static FragmentMessage instance = null;
    public static FragmentMessage newInstance() {
        if (instance == null) {
            instance = new FragmentMessage();
        }
        return instance;
    }
    public void onBackPress(){

    }
}
