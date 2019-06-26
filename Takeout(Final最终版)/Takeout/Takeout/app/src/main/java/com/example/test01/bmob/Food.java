package com.example.test01.bmob;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Food extends BmobObject {

    public String foodName;
    public String foodPrice;
    public BmobFile foodPic;
    public String shopTel;




    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public BmobFile getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(BmobFile foodPic) {
        this.foodPic = foodPic;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shoptel) {
        this.shopTel = shoptel;
    }
}
