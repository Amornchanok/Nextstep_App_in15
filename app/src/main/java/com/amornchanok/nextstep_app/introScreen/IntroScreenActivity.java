package com.amornchanok.nextstep_app.introScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.amornchanok.nextstep_app.bottomNavigation.HomeActivity;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.userRegister.LoginActivity;

public class IntroScreenActivity extends AppCompatActivity {
    ViewPager viewPager;
    TextView tvSkip;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        viewPager = findViewById(R.id.viewPager);

        IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tvSkip = findViewById(R.id.tvSkip);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent skipToHome = new Intent(IntroScreenActivity.this, HomeActivity.class);
                startActivity(skipToHome);
            }
        });

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToLogin = new Intent(IntroScreenActivity.this, LoginActivity.class);
                startActivity(goToLogin);
            }
        });
    }
}
