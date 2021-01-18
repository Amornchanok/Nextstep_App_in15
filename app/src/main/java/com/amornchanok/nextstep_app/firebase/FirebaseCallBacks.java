package com.amornchanok.nextstep_app.firebase;

import com.google.firebase.database.DataSnapshot;

public interface FirebaseCallBacks {
    void onSuccess(DataSnapshot dataSnapshot);
    void onLoading();
    void onFailure();
}

