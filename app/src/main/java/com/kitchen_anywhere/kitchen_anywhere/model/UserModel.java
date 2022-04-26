package com.kitchen_anywhere.kitchen_anywhere.model;

public class UserModel {
    private String userID;
    private String email;
    private String fullName;
    private String address;
    private String postal_code;
    private String phoneNo;
    private boolean isChef;

    public UserModel(String userID, String email, String fullName, String address, String postal_code, String phoneNo,boolean isChef) {
        this.userID = userID;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.postal_code = postal_code;
        this.phoneNo = phoneNo;
        this.isChef = isChef;
    }
    // Getter Methods

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public boolean getIsChef() {
        return isChef;
    }

    // Setter Methods

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setIsChef(boolean isChef) {
        this.isChef = isChef;
    }
}
