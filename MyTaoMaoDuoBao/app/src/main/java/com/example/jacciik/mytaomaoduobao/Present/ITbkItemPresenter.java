package com.example.jacciik.mytaomaoduobao.Present;

/**
 * Created by wanghengjie on 2017/4/12 0012.
 * mail:522716844@qq.com
 */

/*
*
* tag:Fragment tag,order:排序,sort:排序种类,page：页码
* */
public interface ITbkItemPresenter {
    void createFragment(String tag, String keyword, String order, String sort, int page, int isMall, int isOversea, int minVal, int maxVal);
}
