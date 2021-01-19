package com.amornchanok.nextstep_app.firebaseStudio;

public class Rooms {
    private String Capacity;
    private String Name;
    private String Image;
    private String StudioId;
    private String ID;

    public Rooms() {
    }

    public Rooms(String capacity, String name, String image, String studioId, String id) {
        Capacity = capacity;
        Name = name;
        Image = image;
        StudioId = studioId;
        ID=id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID2) {
        this.ID = ID2;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getStudioId() {
        return StudioId;
    }

    public void setStudioId(String StudioId) { this.StudioId = StudioId;}


}
