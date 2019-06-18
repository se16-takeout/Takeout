package com.example.takeout.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeout.R;

import butterknife.ButterKnife;


public class FragmentOrder extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_user_main_order, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
