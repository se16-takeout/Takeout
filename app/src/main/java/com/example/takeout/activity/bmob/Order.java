package com.example.takeout.activity.bmob;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject {

    private String shopname;
    private String tel;


    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
