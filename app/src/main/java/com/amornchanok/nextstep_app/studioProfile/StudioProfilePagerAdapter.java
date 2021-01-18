package com.amornchanok.nextstep_app.studioProfile;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.amornchanok.nextstep_app.modelProfileStudio.Studios;
import com.amornchanok.nextstep_app.studioProfile.tab.InfoStudioFragment;
import com.amornchanok.nextstep_app.studioProfile.tab.ReviewStudioFragment;
import com.amornchanok.nextstep_app.studioProfile.tab.RoomStudioFragment;

public class StudioProfilePagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private Studios studio;

    public StudioProfilePagerAdapter(FragmentManager fm, Studios studio, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.studio = studio;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoStudioFragment(studio);
            case 1:
                return new RoomStudioFragment();
            case 2:
                return new ReviewStudioFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
