package com.amornchanok.nextstep_app.studioProfile;

import com.amornchanok.nextstep_app.modelProfileStudio.Studios;

interface StudioProfile {

    interface View{
        void onStudiosSuccess(Studios studio);
    }

    interface Presenter{
        void getStudio(String id);
    }
}
