package com.amornchanok.nextstep_app.firebaseConnect;

public class PartnerPhoto {
    private String photoID;

    public PartnerPhoto(String photoID) {
        this.photoID = photoID;
    }

    public PartnerPhoto() { }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }
}
