package com.example.jacciik.mytaomaoduobao.Fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacciik.mytaomaoduobao.Activity.TbItemTab;
import com.example.jacciik.mytaomaoduobao.Adapter.TbItemAdapter;
import com.example.jacciik.mytaomaoduobao.Bean.NTbkItem;
import com.example.jacciik.mytaomaoduobao.Bean.TbkResult;
import com.example.jacciik.mytaomaoduobao.R;
import com.example.jacciik.mytaomaoduobao.View.TbkItemFragmentView;
import com.example.jacciik.mytaomaoduobao.Present.TbkItemPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jacciik on 2017/8/9.
 */

public class FragmentTotal extends BaseFragment{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private static FragmentTotal instance = null;
  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_total,null);
    }*/


/*

 */
   public static FragmentTotal newInstance() {
        if (instance == null) {
            instance = new FragmentTotal();
        }
        return instance;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_total;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

    /*  @Override
    public void addToFragment(TbkResult datas) {
        if(datas.getTbkItemGetResponse().getResults().getNTbkItem()!=null){
            mTbkItems = datas.getTbkItemGetResponse().getResults().getNTbkItem();
            if (mCurPage == 1) {
                adapter = new TbItemAdapter(mTbkItems, (TbItemTab) getActivity());
                mRecyclerView.setAdapter(adapter);
            } else {
                adapter.addData((mCurPage - 1) * 20, mTbkItems);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }






    @Override
    public void finishCreateView(Bundle state) {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        //设置refreshLayout监听
        mSwipeRefreshLayout.setOnRefreshListener(
                () -> mSwipeRefreshLayout.setRefreshing(false)
        );

        mTbkItems = new ArrayList<>();
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //滚动时间监听
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //如果滑动状态结束并且滑动到底部
                if (newState == RecyclerView.SCROLL_STATE_IDLE&&adapter!=null&&(adapter.getItemCount() == mCurPage*20)) {
                    //如果是最后一个商品,意味着往上滑动
                    if (mLastVisibleItem == mLayoutManager.getItemCount() - 1) {
                        mCurPage++;
                        tbkItemPresenter.createFragment("total", mKeyword, "des", "tk_rate", mCurPage, 0, 0, 0, 0);
                    } else if (mFirstVisibleItem == 0 && mCurPage != 1) {

                    } else {

                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                mFirstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
            }
        });

        Bundle bundle1 = getArguments();
        mKeyword = bundle1.getString("keyword");
        tbkItemPresenter = new TbkItemPresenterImpl(this.getContext(), this);
        tbkItemPresenter.createFragment("total", mKeyword, null, null, 1, 0, 0, 0, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //销毁Fragment时,重置页码
        mCurPage = 1;
    }

    //更新fragment
    public void refreshFragment(String keyword){
        mKeyword = keyword;
        //重新加载页面
        mCurPage = 1;
        tbkItemPresenter.createFragment("total", mKeyword, null, null, 1, 0, 0, 0, 0);
    }*/
}

