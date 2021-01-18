package com.amornchanok.nextstep_app.tabProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.calendarBooking.CalendarActivity;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import static com.amornchanok.nextstep_app.studioHome.StudioHomeActivity.EXTRA_NAME;
import static com.amornchanok.nextstep_app.studioHome.StudioHomeActivity.EXTRA_PIC;

public class StudioProfileActivity extends AppCompatActivity {
    Button btnBooking;
    ImageView imgPreview;
    TextView stdNname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_profile);

        Intent intent = getIntent();
        String preview = intent.getStringExtra(EXTRA_PIC);
        String studioname = intent.getStringExtra(EXTRA_NAME);

        ImageView imgPreview = findViewById(R.id.imgPreview);
        TextView stdName = findViewById(R.id.studioname);

        Picasso.get().load(preview).fit().centerInside().into(imgPreview);
        stdName.setText(studioname);

        btnBooking = (Button) findViewById(R.id.btnBooking);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarActivity();
            }

            private void openCalendarActivity() {
                    Intent intent = new Intent(StudioProfileActivity.this, CalendarActivity.class);
                    startActivity(intent);
            }

        });

        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabLayout tabInfo = findViewById(R.id.tabInfo);
//        TabLayout tabRoom = findViewById(R.id.tabRoom);
        TabLayout tabReview = findViewById(R.id.tabReview);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

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
}
