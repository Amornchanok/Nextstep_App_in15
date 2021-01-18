package com.amornchanok.nextstep_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.studioHome.StudioHomeActivity;

public class SearchRoom extends AppCompatActivity {
    private Button bt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_practiceroom);

        bt_search = findViewById(R.id.bt_search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchroomList();
            }
        });
    }
    public void openSearchroomList() {
        Intent intent = new Intent(SearchRoom.this, StudioHomeActivity.class);
        startActivity(intent);

    }
}
