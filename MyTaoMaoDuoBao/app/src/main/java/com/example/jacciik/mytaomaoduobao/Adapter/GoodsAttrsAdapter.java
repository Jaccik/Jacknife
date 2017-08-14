package com.example.jacciik.mytaomaoduobao.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jacciik.mytaomaoduobao.R;
import com.example.jacciik.mytaomaoduobao.SaleAttributeVo;

import java.util.ArrayList;
import java.util.List;


/**
 * 子属性GridView的适配器
 */
public class GoodsAttrsAdapter extends BaseAdapter {

    private Context context;
    private List<SaleAttributeVo> data = new ArrayList<>();
 
    public GoodsAttrsAdapter(Context context) {
        this.context = context;
    }
 
    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }
 
    @Override
    public Object getItem(int position) {
        return null;
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final AttrTextView attrTextView;
        if (v == null) {
            attrTextView = new AttrTextView();
            v = View.inflate(context, R.layout.item_goods_attrs, null);
            attrTextView.attr = (TextView) v.findViewById(R.id.attr_name);
            v.setTag(attrTextView);
        } else {
            attrTextView = (AttrTextView) v.getTag();
        }
        attrTextView.attr.setText(data.get(position).getValue());
        /**
         * 根据选中状态来设置item的背景和字体颜色
         */
        if (data.get(position).isChecked()) {
            attrTextView.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            attrTextView.attr.setTextColor(Color.WHITE);
        } else {
            attrTextView.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
            attrTextView.attr.setTextColor(Color.GRAY);
        }
        return v;
    }
 
    static class AttrTextView {
        public TextView attr;
    }
 

    public void notifyDataSetChanged(boolean isUnfold,
                                     final List<SaleAttributeVo> tempData) {
        if (tempData == null || tempData.isEmpty()) {
            return;
        }
        data.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            data.addAll(tempData);
        } else {
            data.add(tempData.get(0));
            data.add(tempData.get(1));
            data.add(tempData.get(2));
        }
        notifyDataSetChanged();
    }
 
}