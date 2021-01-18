package com.amornchanok.nextstep_app.studioProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.base.BaseActivity;
import com.amornchanok.nextstep_app.calendarBooking.CalendarActivity;
import com.amornchanok.nextstep_app.modelProfileStudio.Studios;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class StudioProfileActivity extends BaseActivity implements StudioProfile.View{

    Button btnBooking;
    ImageView imgPreview;
    TextView tvStdName,tvStdRating,tvStdLocation;
    TabLayout tabLayout,tabInfo,tabRoom,tabReview;
    ViewPager viewPager;

    public static String INTENT_STUDIO_ID = "studioId";

    private StudioProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_profile);

        initViewData();
        initControl();
        initPresenter();

    }

    @Override
    public void initControl() {

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarActivity();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initPresenter() {
        presenter = new StudioProfilePresenter(this,this);
        presenter.getStudio(getIntent().getStringExtra(INTENT_STUDIO_ID));
    }

    @Override
    public void initViewData() {
        imgPreview = findViewById(R.id.imgPreview);
        tvStdName = findViewById(R.id.tvStdName);
        tvStdRating = findViewById(R.id.tvStdRating);
        tvStdLocation = findViewById(R.id.tvStdLocation);
        btnBooking = findViewById(R.id.btnBooking);
        tabLayout = findViewById(R.id.tabBar);
        tabInfo = findViewById(R.id.tabInfo);
//        tabRoom = findViewById(R.id.tabRoom);
        tabReview = findViewById(R.id.tabReview);
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void onStudiosSuccess(Studios studio) {

        StudioProfilePagerAdapter pagerAdapter = new StudioProfilePagerAdapter(getSupportFragmentManager(), studio,tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        Picasso.get().load(studio.getPic().getPreview()).fit().centerInside().into(imgPreview);
        tvStdName.setText(studio.getStudioname());
        tvStdLocation.setText(studio.getLocation().getName());
    }

    private void openCalendarActivity() {
        Intent intent = new Intent(StudioProfileActivity.this, CalendarActivity.class);
        startActivity(intent);
    }
}
