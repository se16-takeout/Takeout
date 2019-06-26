package com.example.test01.bmob;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Diner extends BmobObject {
    public String dinerName;
    public BmobFile dinerLogo;
    public String dinerTel;
    public String dinerAddress;
    public String dinerLatitude;
    public String dinerLongitude;

    public String getDinerPassWord() {
        return dinerPassWord;
    }

    public void setDinerPassWord(String dinerPassWord) {
        this.dinerPassWord = dinerPassWord;
    }

    public String dinerPassWord;


    public String getDinerName() {
        return dinerName;
    }

    public void setDinerName(String dinerName) {
        this.dinerName = dinerName;
    }

    public BmobFile getDinerLogo() {
        return dinerLogo;
    }

    public void setDinerLogo(BmobFile dinerLogo) {
        this.dinerLogo = dinerLogo;
    }

    public String getDinerTel() {
        return dinerTel;
    }

    public void setDinerTel(String dinerTel) {
        this.dinerTel = dinerTel;
    }

    public String getDinerAddress() {
        return dinerAddress;
    }

    public void setDinerAddress(String dinerAddress) {
        this.dinerAddress = dinerAddress;
    }

    public String getDinerLatitude() {
        return dinerLatitude;
    }

    public void setDinerLatitude(String dinerLatitude) {
        this.dinerLatitude = dinerLatitude;
    }

    public String getDinerLongitude() {
        return dinerLongitude;
    }

    public void setDinerLongitude(String dinerLongitude) {
        this.dinerLongitude = dinerLongitude;
    }

}
