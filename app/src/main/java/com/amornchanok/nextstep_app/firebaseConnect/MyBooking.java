package com.amornchanok.nextstep_app.firebaseConnect;

import com.google.firebase.database.Exclude;

public class MyBooking {

    private String Date;
    private String Time;

    private String studio_id;
    private String room_id;
    private String room_name;
    private String room_img;
    private String user_id;


    String key;
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public MyBooking() { }

    public String getStudio_id() {
        return studio_id;
    }
    public void setStudio_id(String studio_id) {
        this.studio_id = studio_id;
    }

    public String getRoom_id() {
        return room_id;
    }
    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }
    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_img() {
        return room_img;
    }
    public void setRoom_img(String room_img) {
        this.room_img = room_img;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }
    public void setTime(String time) {
        Time = time;
    }


}

