package com.amornchanok.nextstep_app.firebaseStudio;

public class Rooms {
    private String Capacity;
    private String Name;
    private String Image;
    private String StudioId;
    private String ID;
    private String Price;

    public Rooms() { }

    public Rooms(String capacity, String name, String image, String studioId, String id,String price) {
        Capacity = capacity;
        Name = name;
        Image = image;
        StudioId = studioId;
        ID=id;
        Price = price;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
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

    public String getPrice() { return Price; }

    public void setPrice(String price) { this.Price = price; }
}
