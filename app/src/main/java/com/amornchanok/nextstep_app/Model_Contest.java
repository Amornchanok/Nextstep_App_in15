package com.amornchanok.nextstep_app;


public class Model_Contest {

    public String id;
    public String name;
    public String image;
    public String tag;


    public Model_Contest() { }

    public Model_Contest(String name, String image, String id, String tag) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tag = tag;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}