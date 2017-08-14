package com.example.jacciik.mytaomaoduobao.Model;


/**
 * Created by wanghengjie on 2017/4/12 0012.
 * mail:522716844@qq.com
 */

public interface ITbkItemModel {
   void getTbkItemByCatgory(String keyword, int page, TbkItemModelImpl.OnTbkItemRequestListener listener);
   void getTbkItemByCategoryAndOrder(String keyword, String order, String sort, int page, TbkItemModelImpl.OnTbkItemRequestListener listener);
   void getTbkItemByCategoryAndFilter(String keyword, String order, String sort, int page, int isMall, int isOversea, int minVal, int maxVal, TbkItemModelImpl.OnTbkItemRequestListener listener);
}
