package com.amornchanok.nextstep_app;

import android.app.Application;

import com.amornchanok.nextstep_app.modelProfileStudio.Partners;

public class MyApplication extends Application {
    public static Partners partner = new Partners();
    public static String ACTIVITY_RESULT_DATA = "result";

    public static class BookingActivity {
    }
}
