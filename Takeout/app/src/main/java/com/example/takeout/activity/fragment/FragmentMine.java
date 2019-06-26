package com.example.takeout.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.takeout.R;

import butterknife.ButterKnife;

import static cn.bmob.v3.Bmob.getApplicationContext;


public class FragmentMine extends Fragment{
View view;
private ImageView iv_photo;
private TextView  tv_name;
private TextView  tv_info;
private TextView tv_caddress;
private Button bt_exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_user_main_mine, container, false);
//        ButterKnife.bind(this, view);
        iv_photo= (ImageView) view.findViewById(R.id.dinerpic);
        tv_name=(TextView) view.findViewById(R.id.myname);
        tv_info= (TextView) view.findViewById(R.id.myinfo);

       bt_exit = view.findViewById(R.id.loginout);

//        bt_modify_info.setOnClickListener(new MyButton());
//        bt_exit.setOnClickListener(new MyButton());


        return view;
    }
//    class MyButton implements View.OnClickListener{
//        @Override
//        public void onClick(View v){
//            switch (v.getId() ){
//                case R.id.loginout:
//                    Infor.tel=null;
//                    Infor.shopName=null;
//                    Infor.isLogin=false;
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    break;
//                case R.id.bt_modify_infor:
//                    Intent intent1=new Intent(getApplicationContext(),Activity_ShopInfo.class);
//                    startActivityForResult(intent1,1);
//                    break;
//            }
//        }
//    }
}
