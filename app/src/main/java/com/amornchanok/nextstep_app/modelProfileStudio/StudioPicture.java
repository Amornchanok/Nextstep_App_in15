package com.amornchanok.nextstep_app.modelProfileStudio;

import java.util.ArrayList;

public class StudioPicture {
    private String logo;
    private String preview;
    private ArrayList <String> profile;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public ArrayList<String> getProfile() {
        return profile;
    }

    public void setProfile(ArrayList<String> profile) {
        this.profile = profile;
    }
}
