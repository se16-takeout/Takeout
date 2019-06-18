package com.example.takeout.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.example.takeout.R;
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
}
