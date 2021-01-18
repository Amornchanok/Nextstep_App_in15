package com.amornchanok.nextstep_app.searchStudio;

import com.amornchanok.nextstep_app.modelProfileStudio.Studios;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

interface SearchRoom {

    interface View{
        void onStudiosSuccess(ArrayList<Studios> studios);
        void onStudiosFilterSuccess(ArrayList<Studios> studios);
    }

    interface Presenter{
        void getStudios();
        void getStudiosFilter(LatLng latLng);
    }
}
