package com.example.jacciik.mytaomaoduobao.Model;


import com.example.jacciik.mytaomaoduobao.Bean.TbkResult;
import com.example.jacciik.mytaomaoduobao.NetWork.RetrofitHelper;

import io.reactivex.Observable;

/**
 * Created by wanghengjie on 2017/4/12 0012.
 * mail:522716844@qq.com
 */

public class TbkItemModelImpl implements ITbkItemModel {

    @Override
    public void getTbkItemByCatgory(String keyword ,int page ,OnTbkItemRequestListener listener ) {
        Observable<TbkResult>  results = RetrofitHelper
                .getTaoMaoAPI()
                .getUserInfoByCatAndPage(keyword,page);
        listener.onSuccess(results);
    }

    public interface OnTbkItemRequestListener{
        void onSuccess(Observable<TbkResult> datas);
        void onFailed();
    }
    //销量（total_sales）, 淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi）
    @Override
    public void getTbkItemByCategoryAndOrder(String keyword, String order, String sort,int page, OnTbkItemRequestListener listener) {
        Observable<TbkResult>  results = RetrofitHelper
                .getTaoMaoAPI()
                .getUserInfoByCatAndOrder(keyword,page,sort,order);
        listener.onSuccess(results);
    }


    @Override
    public void getTbkItemByCategoryAndFilter(String keyword, String order, String sort, int page, int isMall, int isOversea, int minVal, int maxVal , OnTbkItemRequestListener listener) {
        Observable<TbkResult>  results = RetrofitHelper
                .getTaoMaoAPI()
                .getUserInfoByCatAndFilter(keyword,page,sort,order,isMall,isOversea,minVal,maxVal);
        listener.onSuccess(results);
    }
}
