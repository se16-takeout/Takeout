package com.example.test01.bmob;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject {

    String orderId;
    String shopName;
    String shopTel;
    String dinerName;
    String dinerTel;
    String dinerAddress;
    String orderContent;
    String orderState;
    int orderTotalPrice;
    int orderTotal;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public String getDinerName() {
        return dinerName;
    }

    public void setDinerName(String dinerName) {
        this.dinerName = dinerName;
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

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

}