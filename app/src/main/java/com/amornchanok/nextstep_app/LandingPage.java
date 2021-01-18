package com.amornchanok.nextstep_app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.partnerRegister.PartnerConditionActivity;

public class LandingPage extends AppCompatActivity {

    Button bt_start;
    VideoView vdolanding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        bt_start = (Button) findViewById(R.id.bt_start);
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
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConditionPartner();
            }
        });
    }

    public void openConditionPartner() {
        Intent VdoLanding = new Intent(LandingPage.this, PartnerConditionActivity.class);
        VdoLanding.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(VdoLanding);
    }

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
