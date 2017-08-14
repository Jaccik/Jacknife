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

public class FragmentSale extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sale,null);
    }
    private static FragmentSale instance = null;

    public static FragmentSale newInstance() {
        if (instance == null) {
            instance = new FragmentSale();
        }
        return instance;
    }
}
