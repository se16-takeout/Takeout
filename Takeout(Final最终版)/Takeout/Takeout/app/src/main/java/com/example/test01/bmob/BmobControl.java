package com.example.test01.bmob;

import android.content.Intent;
import android.graphics.BitmapFactory;

import com.example.test01.activity.Activity_Fragment;
import com.example.test01.activity.Register;
import com.example.test01.others.Infor;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class BmobControl  {

/*
    public void z(){
        Person p2 = new Person();
        p2.setName("hgy");
        p2.setAddress("opu");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    System.out.println("添加数据成功，返回objectId为："+objectId);
                }else{
                    System.out.println("创建数据失败：" + e.getMessage());
                }
            }
        });
    }
    public void z02(String shopName){
        Shop shop = new Shop();
        shop.setShopName(st1);
        shop.shopName=
        shop.setShopLogo(bmobFile01);
        shop.setShopAddress(address);
        shop.setShopTel(tel);
        shop.setShopPassWord(passWord);
        shop.shopAddress="defqfe";

        shop.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Infor.tel=tel;  //保存信息
                    Infor.isLogin=true;
                    Infor.shopName=shopName;
                    Infor.shopLogo = BitmapFactory.decodeFile(path);                        //把头像保存到Infor里面
                    Intent intent = new Intent(Register.this, Activity_Fragment.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

*/

}
