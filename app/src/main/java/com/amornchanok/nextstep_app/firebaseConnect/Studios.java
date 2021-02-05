package com.amornchanok.nextstep_app.firebaseConnect;

public class Studios {
    private String ID;
    private String Name;
    private String Image;
    private String Logo;
    private String Location;
    private String TimeOpen;
    private String TimeClose;

    private String cbAir;
    private String cbSpeaker;
    private String cbToilet;
    private String cbInternet;
    private String cbCarpark;
    private String cbOther;

    public Studios() { }

    public Studios(String Name, String Image, String ID, String Location, String Logo ,String TimeOpen ,String TimeClose) {
        this.Name = Name;
        this.ID = ID;
        this.Location = Location;
        this.Logo = Logo;
        this.Image = Image;
        this.TimeOpen = TimeOpen;
        this.TimeClose = TimeClose;
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
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLocation() { return Location;}
    public void setLocation(String location) { this.Location = location; }

    public String getLogo() { return Logo; }
    public void setLogo(String logo) { this.Logo = logo; }

    public String getTimeOpen() { return TimeOpen; }
    public void setTimeOpen(String timeOpen) { this.TimeOpen = timeOpen; }

    public String getTimeClose() { return TimeClose; }
    public void setTimeClose(String timeClose) { TimeClose = timeClose; }

    public String getCbAir() {
        return cbAir;
    }

    public void setCbAir(String cbAir) {
        this.cbAir = cbAir;
    }

    public String getCbSpeaker() {
        return cbSpeaker;
    }

    public void setCbSpeaker(String cbSpeaker) {
        this.cbSpeaker = cbSpeaker;
    }

    public String getCbToilet() {
        return cbToilet;
    }

    public void setCbToilet(String cbToilet) {
        this.cbToilet = cbToilet;
    }

    public String getCbInternet() {
        return cbInternet;
    }

    public void setCbInternet(String cbInternet) {
        this.cbInternet = cbInternet;
    }

    public String getCbCarpark() {
        return cbCarpark;
    }

    public void setCbCarpark(String cbCarpark) {
        this.cbCarpark = cbCarpark;
    }

    public String getCbOther() {
        return cbOther;
    }

    public void setCbOther(String cbOther) {
        this.cbOther = cbOther;
    }
}
