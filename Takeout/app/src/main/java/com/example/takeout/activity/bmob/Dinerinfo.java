package com.example.takeout.activity.bmob;
//2016051604121 周鹏刚
import cn.bmob.v3.BmobObject;

public class Dinerinfo extends BmobObject {
    private String phonenumber;
    private String password;
    private String name;


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
