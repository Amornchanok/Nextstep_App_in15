package com.amornchanok.nextstep_app.searchStudio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.amornchanok.nextstep_app.MapsActivity;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.StudioListActivity;
import com.amornchanok.nextstep_app.map.MapActivity;
import com.amornchanok.nextstep_app.searchStudio.SearchStudioActivity;

import java.util.Calendar;

public class SearchStudioActivity extends AppCompatActivity {

    private Button btSearch,btnLoction,btnTime,btnAllStudio;
    private EditText edtPersons,btnDate;
    DatePickerDialog.OnDateSetListener setListener;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_studio);

        btSearch = findViewById(R.id.btSearch);
        btnLoction = findViewById(R.id.btnLoction);
        btnDate = findViewById(R.id.btnDate);
//        btnTime = findViewById(R.id.btnTime);
        edtPersons = findViewById(R.id.edtPersons);

        Calendar calendar = Calendar.getInstance();
         int year = calendar.get(Calendar.YEAR);
         int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SearchStudioActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day +"/"+ month + "/" + year;
                        btnDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });





//        btnLoction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent map = new Intent(SearchStudioActivity.this, MapsActivity.class);
//                startActivity(map);
//            }
//        });

        btnLoction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map = new Intent(SearchStudioActivity.this, MapActivity.class);
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