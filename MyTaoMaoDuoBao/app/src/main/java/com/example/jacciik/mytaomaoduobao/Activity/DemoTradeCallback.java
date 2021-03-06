package com.example.jacciik.mytaomaoduobao.Activity;

import android.widget.Toast;

import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.trade.biz.context.AlibcResultType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;


/**
 * Created by fenghaoxiu on 16/8/23.
 */
public class DemoTradeCallback implements AlibcTradeCallback {

    @Override
    public void onTradeSuccess(AlibcTradeResult tradeResult) {
        //当addCartPage加购成功和其他page支付成功的时候会回调

        if(tradeResult.resultType.equals(AlibcResultType.TYPECART)){
            //加购成功
            Toast.makeText(TaoMaoDuoBaoApp.getInstance(), "加购成功", Toast.LENGTH_SHORT).show();
        }else if (tradeResult.resultType.equals(AlibcResultType.TYPEPAY)){
            //支付成功
            Toast.makeText(TaoMaoDuoBaoApp.getInstance(), "支付成功,成功订单号为"+tradeResult.payResult.paySuccessOrders, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int errCode, String errMsg) {
        //Toast.makeText(TaoMaoDuoBaoApp.getInstance(), "电商SDK出错,错误码="+errCode+" / 错误消息="+errMsg, Toast.LENGTH_SHORT).show();
    }
}
