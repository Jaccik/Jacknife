package com.example.jacciik.mytaomaoduobao;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jacciik.mytaomaoduobao.Adapter.GoodsAttrsAdapter;
import com.example.jacciik.mytaomaoduobao.Fragment.FragmentFilter;

import java.util.ArrayList;
import java.util.List;


/**
 * 筛选商品属性选择的popupwindow
 */
public class FilterPopupWindow extends PopupWindow {
    private View mContentView;
    private Context mContext;
    private View mGoodsNoView;

    private GridView mIsMallGrid; // 商城商品选择Grid
    private GridView mIsOverseaGrid; //海外商品选择Grid
    private TextView mFilterReset; //重置按钮
    private TextView mFilterSure; //确定按钮
    private EditText minVal; //最小值
    private EditText maxVal; //最大值
    private GoodsAttrsAdapter mIsMallAdapter; //天猫商品适配器
    private GoodsAttrsAdapter mIsOverseaAdapter;//海外商品适配器
    private List<SaleAttributeVo> mIsMallList;
    private List<SaleAttributeVo> mIsOverseaList;
    private String[] mIsMallStr = new String[]{"天猫", "淘宝"};
    private String[] mIsOverseaStr = new String[]{"海外商品", "国内商品"};

    /**
     * 商品属性选择的popupwindow
     */
    public FilterPopupWindow(final Activity context, final FragmentFilter fragmentFilter) {
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //加载布局文件
        mContentView = inflater.inflate(R.layout.popup_goods_details, null);
        mGoodsNoView = mContentView.findViewById(R.id.popup_goods_noview);
        mIsMallGrid = (GridView) mContentView.findViewById(R.id.yuguo_service);
        mIsOverseaGrid = (GridView) mContentView.findViewById(R.id.oversea_service);
        mFilterReset = (TextView) mContentView.findViewById(R.id.filter_reset);
        mFilterSure = (TextView) mContentView.findViewById(R.id.filter_sure);
        minVal = (EditText) mContentView.findViewById(R.id.minval);
        maxVal = (EditText) mContentView.findViewById(R.id.maxval);

        mGoodsNoView.setOnClickListener(new CancelOnClickListener());
        mContentView.setOnKeyListener((View v, int keyCode, KeyEvent event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss();
            }
            return true;
        });
        //生成数据
        mIsMallList = new ArrayList<>();
        for (int i = 0; i < mIsMallStr.length; i++) {
            SaleAttributeVo vo = new SaleAttributeVo();
            vo.setValue(mIsMallStr[i]);
            mIsMallList.add(vo);
        }
        mIsMallAdapter = new GoodsAttrsAdapter(context);
        mIsMallGrid.setAdapter(mIsMallAdapter);
        //为适配器添加数据
        mIsMallAdapter.notifyDataSetChanged(true, mIsMallList);
        //数据点击事件
        mIsMallGrid.setOnItemClickListener((AdapterView<?> arg0, View arg1, int arg2, long arg3) -> {
            //设置当前选中的位置的状态为非。
            mIsMallList.get(arg2).setChecked(!mIsMallList.get(arg2).isChecked());
            for (int i = 0; i < mIsMallList.size(); i++) {
                //跳过已设置的选中的位置的状态
                if (i == arg2) {
                    continue;
                }
                mIsMallList.get(i).setChecked(false);
            }
            mIsMallAdapter.notifyDataSetChanged(true, mIsMallList);
        });

        mIsOverseaList = new ArrayList<>();
        for (int i = 0; i < mIsOverseaStr.length; i++) {
            SaleAttributeVo vo = new SaleAttributeVo();
            vo.setValue(mIsOverseaStr[i]);
            mIsOverseaList.add(vo);
        }
        mIsOverseaAdapter = new GoodsAttrsAdapter(context);
        mIsOverseaGrid.setAdapter(mIsOverseaAdapter);
        mIsOverseaAdapter.notifyDataSetChanged(true, mIsOverseaList);
        mIsOverseaGrid.setOnItemClickListener((AdapterView<?> arg0, View arg1, int arg2, long arg3) -> {
            //设置当前选中的位置的状态为非。
            mIsOverseaList.get(arg2).setChecked(!mIsOverseaList.get(arg2).isChecked());
            for (int i = 0; i < mIsOverseaList.size(); i++) {
                //跳过已设置的选中的位置的状态
                if (i == arg2) {
                    continue;
                }
                mIsOverseaList.get(i).setChecked(false);
            }
            mIsOverseaAdapter.notifyDataSetChanged(true, mIsOverseaList);
        });

        // 重置的点击监听，将所有选项全设为false
        mFilterReset.setOnClickListener((View v) -> {
            //将数据全部设置为未选择状态
            for (int i = 0; i < mIsMallList.size(); i++) {
                mIsMallList.get(i).setChecked(false);
            }
            mIsMallAdapter.notifyDataSetChanged(true, mIsMallList);
            minVal.setText(""); //清空文本
            maxVal.setText("");
            for (int i = 0; i < mIsOverseaList.size(); i++) {
                mIsOverseaList.get(i).setChecked(false);
            }
            mIsOverseaAdapter.notifyDataSetChanged(true, mIsOverseaList);
        });
        // 确定的点击监听，将所有已选中项列出
        mFilterSure.setOnClickListener((View v) -> {
            int mallNumber = 0; //选中标识
            for (int i = 0; i < mIsMallList.size(); i++) {
                if (mIsMallList.get(i).isChecked()) {
                    if (("天猫").equals(mIsMallList.get(i).getValue())) {
                        mallNumber = 1;
                    } else {
                        mallNumber = 2;
                    }
                }
            }

            int overseaNumber = 0; //选中标识
            for (int i = 0; i < mIsOverseaList.size(); i++) {
                if (mIsOverseaList.get(i).isChecked()) {
                    if (("海外商品").equals(mIsOverseaList.get(i).getValue())) {
                        overseaNumber = 1;
                    } else {
                        overseaNumber = 2;
                    }
                }
            }

            String minValue = minVal.getText().toString();
            String maxValue = maxVal.getText().toString();
            int minInt = 0;
            int maxInt = 0;
            boolean flag = true;

            //上限要比下限大
            if (!("").equals(minValue) && !"".equals(maxValue)) {
                if (Integer.parseInt(minValue) >= Integer.parseInt(maxValue)) {
                    Toast.makeText(mContext, "请输入正确的价格区间", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else {
                    minInt = Integer.parseInt(minValue);
                    maxInt = Integer.parseInt(maxValue);
                    flag = true;
                }
            } else {
                if (!("".equals(minValue) && "".equals(maxValue))) {
                    Toast.makeText(mContext, "两者都要输入", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
            }
            //如果条件合格,进行筛选
            if (flag) {
                dismiss();
//                fragmentFilter.createFragment(mallNumber, overseaNumber, minInt, maxInt);
            }
        });

        this.setContentView(mContentView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(00000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.update();

    }


    public class CancelOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    public boolean onKeyDown(Context context, int keyCode, KeyEvent event) {
        this.mContext = context;
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
        }
        return true;
    }

    public void showFilterPopup(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

}