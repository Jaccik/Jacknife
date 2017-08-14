package com.example.jacciik.mytaomaoduobao.NetWork;


import com.example.jacciik.mytaomaoduobao.Bean.AliUpdateBean;
import com.example.jacciik.mytaomaoduobao.Bean.VersionBean;
import com.example.jacciik.mytaomaoduobao.Bean.TbkResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wanghengjie on 2017/4/1 0001.
 * mail:522716844@qq.com
 */

public interface GetTbkItemService {
    /*
    * 根据关键词获取淘宝客商品
    * */
    @GET("getItem/")
    Observable<TbkResult> getUserInfoById(@Query("keyword") String keyword);

    //http://localhost:8080/TaoMaoDuBao/getItem/catPage?category=xx+&pageno=1
    @GET("getItem/catPage")
    Observable<TbkResult> getUserInfoByCatAndPage(@Query("category") String category, @Query("pageno") int pageno);

    @GET("getItem/orderPage")
    Observable<TbkResult> getUserInfoByCatAndOrder(@Query("category") String category, @Query("pageno") int pageno, @Query("sort") String sort, @Query("order") String order);

    @GET("getItem/filterPage")
    Observable<TbkResult> getUserInfoByCatAndFilter(@Query("category") String category, @Query("pageno") int pageno, @Query("sort") String sort, @Query("order") String order, @Query("isMall") int isMall, @Query("isOversea") int isOversea, @Query("minVal") int minVal, @Query("maxVal") int maxVal);

    @GET("getToken/updateUser")
    Observable<AliUpdateBean> updateAccount(@Query("id") String id, @Query("name") String name, @Query("pass") String pass, @Query("nick") String nick);

    @GET("getVersion/version")
    Observable<VersionBean> getAppVersion();

}
