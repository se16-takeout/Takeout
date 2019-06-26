package com.example.test01.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.test01.R;
import com.example.test01.activity.Activity_Fragment;

public class Fragment_Menu extends Fragment {

    View view;

    Button bt_home,bt_order,bt_food,bt_mine;
    int state_button=1;        //按钮状态 1 2 3 4  判断，当前状态按钮无反应

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_menu,container,false);

        bt_home=(Button)view.findViewById(R.id. bt_home);
        bt_order=(Button)view.findViewById(R.id.bt_order);
        bt_food=(Button)view.findViewById(R.id.bt_food);
        bt_mine=(Button)view.findViewById(R.id.bt_mine);
        bt_home.setOnClickListener(new MyButton() );
        bt_order.setOnClickListener(new MyButton() );
        bt_food.setOnClickListener(new MyButton() );
        bt_mine.setOnClickListener(new MyButton() );


        return view;
    }

    class MyButton implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId() ){
                case R.id.bt_home:  //首页
                    if(state_button != 1 )
                    {
                        state_button=1;
                        Activity_Fragment activity01 = (Activity_Fragment) getActivity();
                        Fragment_Home fragment_Home = new Fragment_Home();
                        //activity01.beginTransaction = getFragmentManager().beginTransaction();
                        activity01.beginTransaction = getFragmentManager().beginTransaction();
                        activity01.beginTransaction.replace(R.id.fLayout_content, fragment_Home);
                        activity01.beginTransaction.commit();
                    }
                    break;
                case R.id.bt_order:  //首页
                    if(state_button != 2 )
                    {
                        state_button=2;
                        Activity_Fragment activity02 = (Activity_Fragment) getActivity();
                        Fragment_Order fragment_Order = new Fragment_Order();
                        //activity01.beginTransaction = getFragmentManager().beginTransaction();
                        activity02.beginTransaction = getFragmentManager().beginTransaction();
                        activity02.beginTransaction.replace(R.id.fLayout_content, fragment_Order);
                        activity02.beginTransaction.commit();
                    }
                    break;
                case R.id.bt_food:  //菜品
                    if(state_button != 3 )
                    {
                        state_button=3;
                        Activity_Fragment activity03 = (Activity_Fragment) getActivity();
                        Fragment_Food fragment_Food = new Fragment_Food();
                        activity03.beginTransaction = getFragmentManager().beginTransaction();
                        activity03.beginTransaction.replace(R.id.fLayout_content, fragment_Food);
                        activity03.beginTransaction.commit();
                    }
                    break;
                case R.id.bt_mine:  //我的
                    if(state_button != 4 )
                    {
                        state_button=4;
                        Activity_Fragment activity04 = (Activity_Fragment) getActivity();
                        Fragment_Mine fragment_Mine = new Fragment_Mine();
                        activity04.beginTransaction = getFragmentManager().beginTransaction();
                        activity04.beginTransaction.replace(R.id.fLayout_content, fragment_Mine);
                        activity04.beginTransaction.commit();
                    }
                    break;

            }
        }
    }


}
