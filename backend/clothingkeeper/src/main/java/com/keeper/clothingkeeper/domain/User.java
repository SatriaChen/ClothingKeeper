package com.keeper.clothingkeeper.domain;

public class User {
    private String userid;

    private String uName;

    private String uGender;

    private String uPassword;

    private byte[] uHead;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender == null ? null : uGender.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public byte[] getuHead() {
        return uHead;
    }

    public void setuHead(byte[] uHead) {
        this.uHead = uHead;
    }
}