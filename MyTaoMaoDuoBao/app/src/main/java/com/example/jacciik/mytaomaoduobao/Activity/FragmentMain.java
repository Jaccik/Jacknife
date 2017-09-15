package com.example.jacciik.mytaomaoduobao.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jacciik.mytaomaoduobao.R;

/**
 * Created by Jacciik on 2017/8/24.
 */

public class FragmentMain extends Fragment {
    private static FragmentMain instance = null;
    public static FragmentMain newInstance() {
        if (instance == null) {
            instance = new FragmentMain();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,
                false);
        EditText editText=(EditText) view.findViewById(R.id.search_edit);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Intent intent = new Intent(getActivity(), SearchRecord.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
