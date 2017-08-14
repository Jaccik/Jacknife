package com.example.jacciik.mytaomaoduobao.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.example.jacciik.mytaomaoduobao.Utils.ConstantUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里百川电商
 * 项目名称：阿里巴巴电商SDK
 * 开发团队：阿里巴巴百川商业化团队
 * 阿里巴巴电商SDK答疑群号：1200072507
 * <p/>
 * 功能说明：WebView代理页面
 */
public class AliSdkWebViewProxyActivity extends Activity {


    private WebView webView;
    private Map<String, String> exParams = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String num_iid = intent.getStringExtra("num_iid");

        initView();

        initParams();

        AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams(ConstantUtil.MM_PID, ConstantUtil.MM_PID, null); // 若非淘客taokeParams设置为null即可
        AlibcBasePage alibcBasePage = new AlibcDetailPage(num_iid);
        AlibcShowParams alibcShowParams = new AlibcShowParams(OpenType.H5, false);
        AlibcTrade.show(AliSdkWebViewProxyActivity.this,webView, null, null,alibcBasePage,alibcShowParams,alibcTaokeParams,exParams, new DemoTradeCallback());

    }



    private void initView() {

        LinearLayout linearLayout = new LinearLayout(this, null);
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.addView(webView, params);
        setContentView(linearLayout);

    }

    private void initParams(){
        exParams.put("isv_code", "appisvcode");
        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改
    }

    //登录须重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        //调用了AlibcTrade.show方法的Activity都需要调用AlibcTradeSDK.destory()
        AlibcTradeSDK.destory();
        super.onDestroy();
    }
}
