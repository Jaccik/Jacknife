package com.example.jacciik.mypractice;

import android.app.Application;

/**
 * Created by Jacciik on 2017/9/2.
 */

public class App extends Application {
    private String data="defaut";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data=data;
    }
}
