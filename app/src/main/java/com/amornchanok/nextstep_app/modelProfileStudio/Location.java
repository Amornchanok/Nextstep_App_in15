package com.amornchanok.nextstep_app.modelProfileStudio;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private LatLng latLng;
    private String address;

    public Location(LatLng latLng, String address) {
        this.latLng = latLng;
        this.address = address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
