package com.example.takeout.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.takeout.R;
import com.example.takeout.activity.Dinner_shops;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class FragmentHome extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_user_main_frament, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @BindView(R.id.fun1)
    LinearLayout fun1;
    @OnClick({
            R.id.fun1,
    })

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fun1:
                Intent intent1=new Intent(getActivity(), Dinner_shops.class);
                startActivity(intent1);
                break;
        }
    }
}
