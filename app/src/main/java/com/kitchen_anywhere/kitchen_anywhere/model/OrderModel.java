package com.kitchen_anywhere.kitchen_anywhere.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderModel implements Serializable
{

    private String chefId;
    private String contactOfFoodie;
    private List<FoodModel> dishList;
    private String nameOfFoodie;
    private Date orderDate;
    private String orderId;
    private String orderStatus;
    private String userId;

    public OrderModel(String chefId, String contactOfFoodie,List<FoodModel> dishList, String nameOfFoodie,Date orderDate,
                      String orderId,String orderStatus,String userId) {
        this.chefId = chefId;
        this.contactOfFoodie = contactOfFoodie;
        this.dishList = dishList;
        this.nameOfFoodie = nameOfFoodie;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.orderStatus = orderStatus ;
        this.userId = userId;
    }


    public String getchefId() {
        return chefId;
    }

    public void setchefId(String chefId) {
        this.chefId = chefId;
    }

    public String getcontactOfFoodie() {
        return contactOfFoodie;
    }

    public void setcontactOfFoodie(String contactOfFoodie) {
        this.contactOfFoodie = contactOfFoodie;
    }

    public ArrayList<FoodModel> getdishList() {
        return (ArrayList<FoodModel>) dishList;
    }

    public void setdishList(List<FoodModel> dishList) {
        this.dishList = dishList;
    }

    public String getnameOfFoodie() {
        return nameOfFoodie;
    }

    public void setnameOfFoodie(String nameOfFoodie) {
        this.nameOfFoodie = nameOfFoodie;
    }

    public Date getorderDate() {
        return orderDate;
    }

    public void setorderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getorderId() {
        return orderId;
    }

    public void setorderId(String orderId) {
        this.orderId = orderId;
    }

    public String getorderStatus() {
        return orderStatus;
    }

    public void setorderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

}
