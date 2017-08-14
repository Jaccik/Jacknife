package com.example.jacciik.mytaomaoduobao.Present;

import android.content.Context;
import android.util.Log;

import com.example.jacciik.mytaomaoduobao.Bean.TbkResult;
import com.example.jacciik.mytaomaoduobao.Model.TbkItemModelImpl;
import com.example.jacciik.mytaomaoduobao.View.TbkItemFragmentView;
import com.example.jacciik.mytaomaoduobao.Model.TbkItemModelImpl;
import com.example.jacciik.mytaomaoduobao.View.TbkItemFragmentView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by wanghengjie on 2017/4/12 0012.
 * mail:522716844@qq.com
 */

public class TbkItemPresenterImpl implements  ITbkItemPresenter {
    Context mContext;
    private TbkItemModelImpl tbkItemModel;
    private TbkItemFragmentView tbkItemFragmentView;
    private String TAG = "TbkItemPresenterImpl";
    private String errorMessage = "获取商品出错";


    public TbkItemPresenterImpl(Context mContext, TbkItemFragmentView tbkItemFragmentView){
        this.mContext = mContext;
        this.tbkItemFragmentView = tbkItemFragmentView;
        tbkItemModel = new TbkItemModelImpl();
    }

    @Override
    public void createFragment(String tag ,String keyword, String order , String sort , int page ,int isMall ,int isOversea,int minVal,int maxVal) {
        tbkItemModel = new TbkItemModelImpl();
        //定义观察者，观察获取的数据
        Observer<TbkResult> observer = new Observer<TbkResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(TbkResult value) {
                tbkItemFragmentView.addToFragment(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };
        //建立连接

        if(("total").equals(tag)){
            tbkItemModel.getTbkItemByCatgory(keyword, page ,new TbkItemModelImpl.OnTbkItemRequestListener() {
                @Override
                public void onSuccess(Observable<TbkResult> datas) {
                    datas.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(observer);
                }
                @Override
                public void onFailed() {
                    Log.d(TAG ,errorMessage);
                }
            });
        }else if(("priceOrSale").equals(tag)){
            tbkItemModel.getTbkItemByCategoryAndOrder(keyword,order,sort, page ,new TbkItemModelImpl.OnTbkItemRequestListener() {
                @Override
                public void onSuccess(Observable<TbkResult> datas) {
                    datas.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(observer);
                }
                @Override
                public void onFailed() {
                    Log.d(TAG , errorMessage);
                }
            });
        }else {
            tbkItemModel.getTbkItemByCategoryAndFilter(keyword, order, sort, page, isMall, isOversea, minVal, maxVal, new TbkItemModelImpl.OnTbkItemRequestListener() {
                @Override
                public void onSuccess(Observable<TbkResult> datas) {
                    datas.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(observer);
                }

                @Override
                public void onFailed() {
                    Log.d(TAG , errorMessage);
                }
            });
        }
    }
}
