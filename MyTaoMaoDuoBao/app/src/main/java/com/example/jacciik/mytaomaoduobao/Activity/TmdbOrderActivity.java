package com.example.jacciik.mytaomaoduobao.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.example.jacciik.mytaomaoduobao.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TmdbOrderActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.backforward)
    ImageView backforward;
    @BindView(R.id.refresh)
    ImageView refresh;


    private int orderType = 0;//订单页面参数，仅在H5方式下有效

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        //订单回退按钮
        backforward.setOnClickListener((View view)->web_view.goBack());
        //刷新按钮
        refresh.setOnClickListener((View view)->web_view.reload());
        AlibcShowParams alibcShowParams;//页面打开方式，默认，H5，Native
        Map<String, String> exParams;//yhhpass参数
        alibcShowParams = new AlibcShowParams(OpenType.H5, false);

        exParams = new HashMap<>();
        exParams.put("isv_code", "appisvcode");
        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改

        boolean isAllOrder = true;

        AlibcMyOrdersPage alibcMyOrdersPage = new AlibcMyOrdersPage(orderType , isAllOrder);
        //在当前的webview中打开购物车
        AlibcTrade.show(this, web_view, null, null, alibcMyOrdersPage, alibcShowParams, null, exParams, new DemoTradeCallback());
    }

    @Override
    public int getLayoutId() {
        return R.layout.tmdb_activity_order;
    }

    public void initView() {
        //加载淘猫夺宝网址
        web_view.canGoBack();
        web_view.canGoForward();
        WebSettings webSettings = web_view.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //设置不用系统浏览器打开,直接显示在当前Webview
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //在手机淘宝中打开购物车商品
                //AlibcTrade.show(getActivity() , new AlibcPage(url), itemParams, null, itemExParams, new DemoTradeCallback());
                return false;
            }

            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //Do something
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                //Do something
            }
        });
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }
}
