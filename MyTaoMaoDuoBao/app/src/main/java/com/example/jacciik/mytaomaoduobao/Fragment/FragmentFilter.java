package com.example.jacciik.mytaomaoduobao.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacciik.mytaomaoduobao.R;

/**
 * Created by Jacciik on 2017/8/9.
 */

public class FragmentFilter extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_filter,null);
    }
    private static FragmentFilter instance = null;
    public static FragmentFilter newInstance() {
        if (instance == null) {
            instance = new FragmentFilter();
        }
        return instance;
    }
}
