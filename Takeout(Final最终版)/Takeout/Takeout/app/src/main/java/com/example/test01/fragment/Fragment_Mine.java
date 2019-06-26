package com.example.test01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.test01.R;
import com.example.test01.activity.Activity_ShopInfo;
import com.example.test01.activity.MainActivity;
import com.example.test01.others.Infor;

import static cn.bmob.v3.Bmob.getApplicationContext;


public class Fragment_Mine extends Fragment {

    View view;
    private ImageView iv_photo;
    private TextView tv_name;
    private TextView tv_time;
    private Button bt_modify_info;
    private Button bt_exit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_mine,container,false);

        iv_photo= (ImageView) view.findViewById(R.id.iv_photo);
        tv_name=(TextView) view.findViewById(R.id.tv_name);
        tv_time= (TextView) view.findViewById(R.id.tv_time);
        bt_modify_info=(Button) view.findViewById(R.id.bt_modify_infor);
        bt_exit=(Button) view.findViewById(R.id.bt_exit);

        iv_photo.setImageBitmap( Infor.shopLogo);    //Infor.shopLogo是Bitmap类型
        bt_modify_info.setOnClickListener(new MyButton());
        bt_exit.setOnClickListener(new MyButton());

        tv_name.setText(Infor.shopName);

        return view;
    }

    @Override       //接收回传的数据 userName
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==1)
        {
            tv_name.setText(Infor.shopName);
            iv_photo.setImageBitmap( Infor.shopLogo);    //Infor.shopLogo是Bitmap类型
        }

    }


    class MyButton implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId() ){
                case R.id.bt_exit:
                    Infor.tel=null;
                    Infor.shopName=null;
                    Infor.isLogin=false;
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bt_modify_infor:
                    Intent intent1=new Intent(getApplicationContext(), Activity_ShopInfo.class);
                    //intent1.putExtra("extra_shopName",)
                    startActivityForResult(intent1,1);
                    break;
            }
        }
    }
}
