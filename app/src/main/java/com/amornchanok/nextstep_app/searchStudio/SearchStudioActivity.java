package com.amornchanok.nextstep_app.searchStudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amornchanok.nextstep_app.MapsActivity;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.StudioListActivity;
import com.amornchanok.nextstep_app.searchStudio.SearchStudioActivity;

public class SearchStudioActivity extends AppCompatActivity {

    private Button btSearch,btnLoction,btnDate,btnTime,btnAllStudio;
    EditText edtPersons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_studio);

        btSearch = findViewById(R.id.btSearch);
        btnLoction = findViewById(R.id.btnLoction);
        btnDate = findViewById(R.id.btnDate);
//        btnTime = findViewById(R.id.btnTime);
        edtPersons = findViewById(R.id.edtPersons);


        btnLoction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map = new Intent(SearchStudioActivity.this, MapsActivity.class);
                startActivity(map);
            }
        });

        btnAllStudio = (Button) findViewById(R.id.btnAllStudio);
        btnAllStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SearchStudioActivity.this, StudioListActivity.class);
                startActivity(i);
            }
        });
    }
}