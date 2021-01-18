package com.amornchanok.nextstep_app.introScreen;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    Timer timer;
    VideoView vdolanding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, IntroScreenActivity.class);
                startActivity(intent);
            }
        },5000);

        vdolanding = (VideoView) findViewById(R.id.vdolanding);
        String path ="android.resource://com.amornchanok.nextstep_app/"+R.raw.vdolanding;
        Uri u = Uri.parse(path);
        vdolanding.setVideoURI(u);
        vdolanding.start();

        vdolanding.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

//    public void openConditionPartner() {
//        Intent VdoLanding = new Intent(LandingPage.this, HomeActivity.class);
//        VdoLanding.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(VdoLanding);
//    }

    @Override
    protected void onResume() {
        vdolanding.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        vdolanding.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        vdolanding.stopPlayback();
        super.onDestroy();
    }
}
