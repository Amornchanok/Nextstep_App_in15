package com.amornchanok.nextstep_app;


public class Model_Studios {

    public String id;
    public String location;
    public String name;
    public String image;
    public String logo;
    public String tag;
    public double lat;
    public double lng;


    public Model_Studios() {
    }


    public Model_Studios(String id, String location, String name, String image, String logo, String tag, double lat, double lng) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.image = image;
        this.logo = logo;
        this.tag = tag;
        this.lat = lat;
        this.lng = lng;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}