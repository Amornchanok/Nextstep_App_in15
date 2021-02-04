package com.amornchanok.nextstep_app;

import com.google.firebase.database.Exclude;

public class Upload {
    private String name;
    private String imageUrl;
    String i_name;
    String i_url;
    String key;

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public Upload() { }

    public Upload(String name, String imageUrl,String i_name, String i_url) {

        if (name.trim().equals("")) {
            name = "No Name";
        }

        this.name = name;
        this.imageUrl = imageUrl;
        this.i_name = i_name;
        this.i_url = i_url;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getI_name() { return i_name; }
    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public String getI_url() {
        return i_url;
    }
    public void setI_url(String i_url) {
        this.i_url = i_url;
    }
}
