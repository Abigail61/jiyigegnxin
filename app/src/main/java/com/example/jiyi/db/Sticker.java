package com.example.jiyi.db;

//表示便利贴的类,简约版
public class Sticker {
    int stickerID;
    String username;//发布者
    String sdate; // 发布时间
    String place; //发布地点
    String stickerText;
    String tag1,tag2,tag3; // 我在界面设置了最多三个tag，所以就设置三个变量吧
    String imagepath;
    int year; //手动选择的年
    int month;
    int day;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStickerID() {
        return stickerID;
    }

    public void setStickerID(int stickerID) {
        this.stickerID = stickerID;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStickerText() {
        return stickerText;
    }

    public void setStickerText(String stickerText) {
        this.stickerText = stickerText;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag) {
        this.tag1 = tag;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag) {
        this.tag2 = tag;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag) {
        this.tag3 = tag;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Sticker() {
    }

    public Sticker(String username, int stickerID, String sdate, String place, String stickerText, String tag1, String tag2, String tag3, String imagepath, int year, int month, int day) {
        this.stickerID = stickerID;
        this.username = username;
        this.sdate = sdate;
        this.place = place;
        this.stickerText = stickerText;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.imagepath = imagepath;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}


