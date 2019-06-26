package com.example.takeout.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.takeout.R;
import com.example.takeout.activity.fragment.FragmentHome;
import com.example.takeout.activity.fragment.FragmentMine;
import com.example.takeout.activity.fragment.FragmentOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//李楷 2016051604109 软件工程 2016级

public class Dinner_main extends FragmentActivity {

    private List<Fragment> fragmentList;
    public int currentTabPosition;
    private ViewPager vp;
    private FragAdapter adapter;

    @BindView(R.id.radiogroup)
    RadioGroup  radio;

    @BindView(R.id.radio_one)
    RadioButton one;
    @BindView(R.id.radio_two)
    RadioButton two;
    @BindView(R.id.radio_three)
    RadioButton three;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        ButterKnife.bind(this);
        change();
        setListener();
    }

    private FragmentHome home;
    private  FragmentOrder order;

    public void  change(){
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentOrder());
        fragments.add(new FragmentMine());
        adapter = new FragAdapter(getSupportFragmentManager(),
                fragments);
        //设定适配器
        vp = (ViewPager) findViewById(R.id.frament);
        vp.setAdapter(adapter);
    }

    @OnClick({
            R.id.radio_one,
            R.id.radio_two,
            R.id.radio_three,
    })
    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.radio_one:
                vp.setCurrentItem(0);
                break;
            case R.id.radio_two:
                vp.setCurrentItem(1);
                break;
            case R.id.radio_three:
                vp.setCurrentItem(2);
                break;
        }
    }

    public class FragAdapter extends FragmentPagerAdapter {
        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentList.get(arg0);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    public void setListener(){
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        one.setChecked(true);
                        break;
                    case 1:
                        two.setChecked(true);
                        break;
                    case 2:
                        three.setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
