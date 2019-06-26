package com.example.test01.bmob;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Shop extends BmobObject  {

    public String shopName;
    public String shopTel;
    public String shopPassWord;
    public BmobFile shopLogo;
    public String shopAddress;
    public String shopLongitude;//经度
    public String shopLatitude;//纬度


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopPassWord() {
        return shopPassWord;
    }

    public void setShopPassWord(String shopPassWord) {
        this.shopPassWord = shopPassWord;
    }

    public BmobFile getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(BmobFile shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(String shopLongitude) {
        this.shopLongitude = shopLongitude;
    }

    public String getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(String shopLatitude) {
        this.shopLatitude = shopLatitude;
    }


/*
    public Shop(){}

    @Override
    public String toString(){
        return "Shop{" +
                "shopName='" + shopName + '\'' +
                ", tel='" + tel + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
    */

}