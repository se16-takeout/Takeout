package com.example.test01.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.test01.R;


import com.example.test01.activity.Diner_Activity_Fragment;
import com.example.test01.fragment.Fragment_Diner_Home;
import com.example.test01.fragment.Fragment_Diner_Menu;

public class Fragment_Diner_Menu extends Fragment {
    View view;

    Button bt_diner_home,bt_diner_order,bt_diner_mine;
    int state_button=1;        //按钮状态 1 2 3  判断，当前状态按钮无反应

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_diner_menu,container,false);

        bt_diner_home=(Button)view.findViewById(R.id. bt_diner_home);
        bt_diner_order=(Button)view.findViewById(R.id.bt_diner_order);
        bt_diner_mine=(Button)view.findViewById(R.id.bt_diner_mine);


        bt_diner_home.setOnClickListener(new MyButton() );
        bt_diner_order.setOnClickListener(new MyButton() );
        bt_diner_mine.setOnClickListener(new MyButton() );

        return view;
    }

    class MyButton implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId() ){
                case R.id.bt_diner_home:  //首页
                    if(state_button != 1 )
                    {
                        state_button=1;
                        Diner_Activity_Fragment activity01 = (Diner_Activity_Fragment) getActivity();
                        Fragment_Diner_Home fragment_diner_Home = new Fragment_Diner_Home();
                        activity01.beginTransaction = getFragmentManager().beginTransaction();
                        activity01.beginTransaction.replace(R.id.fLayout_diner_content, fragment_diner_Home);
                        activity01.beginTransaction.commit();
                    }
                    break;
                case R.id.bt_diner_order:  //首页
                    if(state_button != 2 )
                    {
                        state_button=2;
                        Diner_Activity_Fragment activity02 = (Diner_Activity_Fragment) getActivity();
                        Fragment_Diner_Order fragment_diner_Order = new Fragment_Diner_Order();
                        activity02.beginTransaction = getFragmentManager().beginTransaction();
                        activity02.beginTransaction.replace(R.id.fLayout_diner_content, fragment_diner_Order);
                        activity02.beginTransaction.commit();
                    }
                    break;
                case R.id.bt_diner_mine:  //我的
                    if(state_button != 3 )
                    {
                        state_button=3;
                        Diner_Activity_Fragment activity04 = (Diner_Activity_Fragment) getActivity();
                        Fragment_Diner_Mine fragment_diner_Mine = new Fragment_Diner_Mine();
                        activity04.beginTransaction = getFragmentManager().beginTransaction();
                        activity04.beginTransaction.replace(R.id.fLayout_diner_content, fragment_diner_Mine);
                        activity04.beginTransaction.commit();
                    }
                    break;
            }
        }
    }
}
