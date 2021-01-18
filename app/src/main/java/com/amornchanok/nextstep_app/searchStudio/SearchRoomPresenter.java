package com.amornchanok.nextstep_app.searchStudio;

import android.util.Log;

import com.amornchanok.nextstep_app.firebase.FirebaseCallBacks;
import com.amornchanok.nextstep_app.firebase.FirebaseManager;
import com.amornchanok.nextstep_app.modelProfileStudio.Studios;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class SearchRoomPresenter implements SearchRoom.Presenter {
    private SearchRoom.View view;
    private SearchRoomActivity activity;
    private FirebaseManager firebaseManager;
    private ArrayList<Studios> studios = new ArrayList<>();

    public SearchRoomPresenter(SearchRoom.View mainView , SearchRoomActivity activity) {
        this.view = mainView;
        this.activity = activity;
        firebaseManager = new FirebaseManager();
    }

    @Override
    public void getStudios() {
        firebaseManager.getStudios(new FirebaseCallBacks() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                for (DataSnapshot child :dataSnapshot.getChildren()){
                    Studios value=child.getValue(Studios.class);
                    studios.add(value);
                }

                activity.hideProcessDialog();
                view.onStudiosSuccess(studios);
            }

            @Override
            public void onLoading() {
                activity.showProcessDialog();
            }

            @Override
            public void onFailure() {
                Log.d("showAllRoom","onFailure");
            }
        });
    }

    @Override
    public void getStudiosFilter(LatLng latLng) {
        ArrayList<Studios> studiosFilter = new ArrayList<>();
        for (Studios s: studios) {
            Double distance = distance(
                    Double.parseDouble(s.getLocation().getLat()),
                    Double.parseDouble(s.getLocation().getLng()),
                    latLng.latitude,
                    latLng.longitude);
            if (distance <= 10.0){
                studiosFilter.add(s);
            }
        }
        view.onStudiosFilterSuccess(studiosFilter);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
