package com.example.jacciik.mytaomaoduobao.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;


import com.example.jacciik.mytaomaoduobao.Adapter.TabViewPagerAdapter;
import com.example.jacciik.mytaomaoduobao.FilterPopupWindow;
import com.example.jacciik.mytaomaoduobao.Fragment.FragmentFilter;
import com.example.jacciik.mytaomaoduobao.Fragment.FragmentPrice;
import com.example.jacciik.mytaomaoduobao.Fragment.FragmentSale;
import com.example.jacciik.mytaomaoduobao.Fragment.FragmentTotal;
import com.example.jacciik.mytaomaoduobao.R;

import java.text.BreakIterator;
import java.util.Map;


public class TbItemTab extends FragmentActivity {
    FragmentTotal fragmentTotal;
    FragmentPrice fragmentPrice;
    FragmentFilter fragmentFilter;
    FragmentSale fragmentSale;
    private Map<String, String> exParams;//yhhpass参数
    private final static String TAG = "TakItemTabActivity";
    private String mLastQuery = "";
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    EditText search_edit;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    View main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tb_item_tab);
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        search_edit=(EditText) findViewById(R.id.search_edit2);
        setupSearchBar();
        Bundle fragmentBundle = new Bundle();
        fragmentTotal = FragmentTotal.newInstance();
        fragmentTotal.setArguments(fragmentBundle);

        fragmentPrice = FragmentPrice.newInstance();
        fragmentPrice.setArguments(fragmentBundle);

        fragmentSale = FragmentSale.newInstance();
        fragmentSale.setArguments(fragmentBundle);

        fragmentFilter = FragmentFilter.newInstance();
        fragmentFilter.setArguments(fragmentBundle);

        viewPagerAdapter.addFragment(fragmentTotal, "综合");
        viewPagerAdapter.addFragment(fragmentPrice, "折扣");
        viewPagerAdapter.addFragment(fragmentSale, "销量");
        viewPagerAdapter.addFragment(fragmentFilter, "筛选");

        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout=(TabLayout)findViewById(R.id.tabLayout);


        mTabLayout.addTab(mTabLayout.newTab().setText("综合"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("折扣"));
        mTabLayout.addTab(mTabLayout.newTab().setText("销量"));
        mTabLayout.addTab(mTabLayout.newTab().setText("筛选"));
        mTabLayout.setupWithViewPager(mViewPager);
        FilterPopupWindow popupWindow = new FilterPopupWindow(TbItemTab.this, fragmentFilter);
        main= findViewById(R.id.main);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 3) {
                    popupWindow.showFilterPopup(main);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Do Something
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 3) {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupWindow.showFilterPopup(main);
                    }
                }
            }
        });

    }


    private void setupSearchBar() {
        //点击搜索历史时
        search_edit=(EditText) findViewById(R.id.search_edit2);
        search_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onBackPressed();
                finish();
                return false;
            }
        });
    }


}


