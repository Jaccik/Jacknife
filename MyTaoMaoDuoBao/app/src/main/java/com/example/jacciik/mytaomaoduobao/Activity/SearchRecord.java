package com.example.jacciik.mytaomaoduobao.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacciik.mytaomaoduobao.Bean.RecordBean;
import com.example.jacciik.mytaomaoduobao.DAO.RecordBeanDao;
import com.example.jacciik.mytaomaoduobao.DAO.GreenDaoManager;
import com.example.jacciik.mytaomaoduobao.R;
import com.google.android.flexbox.FlexboxLayout;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class SearchRecord extends Activity {
    EditText search_edit;
    FlexboxLayout mFlexboxLayout;
    ImageView deleteImg;
    RecordBeanDao recordBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_record);
        search_edit=(EditText)findViewById(R.id.search_edit);
        mFlexboxLayout =(FlexboxLayout)findViewById(R.id.flexbox_layout);
        deleteImg=(ImageView)findViewById(R.id.deleteImg);
        HandleHistory();
        setupSearchBar();
    }
    //dp转pixel
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    private void setupSearchBar() {
        search_edit.setOnEditorActionListener((TextView textView, int i, KeyEvent keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                String query = search_edit.getText().toString();
                RecordBean recordBean = new RecordBean();
                recordBean.setName(query);
                QueryBuilder qb = recordBeanDao.queryBuilder();
                qb.where(RecordBeanDao.Properties.Name.eq(query));
                List list = qb.list();
                if (list != null && list.isEmpty()) {
                    recordBeanDao.insert(recordBean);
                }
                Intent intent = new Intent(SearchRecord.this, TbItemTab.class);
                intent.putExtra("keyword", query);
                startActivity(intent);
            }
            return false;
        });
    }

    private void HandleHistory() {
        mFlexboxLayout.removeAllViews();
        recordBeanDao = GreenDaoManager.getInstance().getmDaoSession().getRecordBeanDao();
        List<RecordBean> records = recordBeanDao.loadAll();
        if (records != null) {
            for (int i = 0; i < records.size(); i++) {
                RecordBean recordBean = records.get(i);
                TextView textView = new TextView(this);

                textView.setBackground(getResources().getDrawable(R.drawable.label_bg_shape));
                textView.setText(recordBean.getName());
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(12, 0, 12, 0);
                textView.setMinWidth(dip2px(getApplicationContext(), 30));
                textView.setTextColor(getResources().getColor(R.color.textColor));
                textView.setOnClickListener((View view) -> {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    Intent intent = new Intent(SearchRecord.this, TbItemTab.class);
                    intent.putExtra("keyword", recordBean.getName());
                    startActivity(intent);
                });
                mFlexboxLayout.addView(textView);
                ViewGroup.LayoutParams params = textView.getLayoutParams();
                if (params instanceof FlexboxLayout.LayoutParams) {
                    FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
                    int marginValue = dip2px(getApplicationContext(), 5);
                    layoutParams.setMargins(marginValue, marginValue, marginValue, marginValue);
                    layoutParams.height = dip2px(getApplicationContext(), 30);
                }
            }
        }
        deleteImg.setOnClickListener((View view) -> {
            Dialog alertDialog = new AlertDialog.Builder(SearchRecord.this).
                    setMessage("确认删除历史记录?").
                    setPositiveButton("确定", (DialogInterface dialog, int which) -> {
                        recordBeanDao.deleteAll();
                        dialog.dismiss();
                        mFlexboxLayout.removeAllViews();
                    }).setNegativeButton("取消", (DialogInterface dialogInterface, int i) ->
                    dialogInterface.dismiss()
            ).create();

            alertDialog.show();
        });
    }
}
