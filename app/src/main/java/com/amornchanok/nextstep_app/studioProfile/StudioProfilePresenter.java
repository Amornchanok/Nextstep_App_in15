package com.amornchanok.nextstep_app.studioProfile;

import android.util.Log;

import com.amornchanok.nextstep_app.firebase.FirebaseCallBacks;
import com.amornchanok.nextstep_app.firebase.FirebaseManager;
import com.amornchanok.nextstep_app.modelProfileStudio.Studios;
import com.google.firebase.database.DataSnapshot;

public class StudioProfilePresenter implements StudioProfile.Presenter {
    private StudioProfile.View view;
    private StudioProfileActivity activity;
    private FirebaseManager firebaseManager;

    public StudioProfilePresenter(StudioProfile.View mainView , StudioProfileActivity activity) {
        this.view = mainView;
        this.activity = activity;
        firebaseManager = new FirebaseManager();
    }

    @Override
    public void getStudio(final String id) {
        firebaseManager.getStudioById(id,new FirebaseCallBacks() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){
                    Studios studio = dataSnapshot.child(id).getValue(Studios.class);
                    activity.hideProcessDialog();
                    view.onStudiosSuccess(studio);
                }else {
                    Log.d("getStudio","error");
                }
            }

            @Override
            public void onLoading() {
                activity.showProcessDialog();
            }

            @Override
            public void onFailure() {
                Log.d("getStudio","onFailure");
            }
        });
    }
}
