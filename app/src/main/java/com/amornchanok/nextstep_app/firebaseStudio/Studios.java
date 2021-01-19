package com.amornchanok.nextstep_app.firebaseStudio;

public class Studios {
    private String ID;
    private String Name;
    private String Image;
    private String Location;

    public Studios() { }

    public Studios(String Name2, String Image, String ID, String Location) {
        Name = Name2;
        this.ID = ID;
        this.Location = Location;
        this.Image = Image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID2) {
        ID = ID2;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name2) {
        Name = Name2;
    }

    public String getLocation() { return Location;}

    public void setLocation(String location) { Location = location; }
}
