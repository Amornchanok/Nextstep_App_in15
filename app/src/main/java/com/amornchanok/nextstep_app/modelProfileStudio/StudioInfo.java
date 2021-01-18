package com.amornchanok.nextstep_app.modelProfileStudio;

import java.util.ArrayList;

public class StudioInfo {
    private ArrayList <Convenient> convenient;
    private TimeService timeService;

    public ArrayList<Convenient> getConvenient() {
        return convenient;
    }

    public void setConvenient(ArrayList<Convenient> convenient) {
        this.convenient = convenient;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

}
