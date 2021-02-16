package com.amornchanok.nextstep_app;


public class Model_Studios {

    public String id;
    public String location;
    public String name;
    public String image;
    public String logo;
    public String tag;


    public Model_Studios() {
    }

    public Model_Studios(String name, String location, String logo, String image, String id, String tag) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.image = image;
        this.logo = logo;
        this.tag = tag;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}