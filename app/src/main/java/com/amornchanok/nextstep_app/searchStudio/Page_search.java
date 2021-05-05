package com.amornchanok.nextstep_app.searchStudio;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amornchanok.nextstep_app.Model_Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.firebase.FirebaseCallBacks;
import com.amornchanok.nextstep_app.firebase.FirebaseManager;
import com.amornchanok.nextstep_app.firebaseConnect.Rooms;
import com.amornchanok.nextstep_app.map.Location;
import com.amornchanok.nextstep_app.map.MapActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;

public class Page_search extends AppCompatActivity {

    private EditText mSearchField;
    Button search_btn;
    private RecyclerView mRecyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    private FirebaseManager firebaseManager;

    @Nullable
    private Location selectLocation = null;

    ArrayList<Model_Studios> arrayList = new ArrayList<>();
    ArrayList<Rooms> rooms = new ArrayList<>();


    TextView heading;
    //SearchAdapter searchAdapter;
    AdapterRent searchAdapter;
    Button btnLoction;
    EditText btnDate;
    String s_Date;
    EditText edtPersons;

    Button btSearch;

    DatePickerDialog.OnDateSetListener setListener;


    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_search_2);

        btnDate = findViewById(R.id.btnDate);
        btnLoction = findViewById(R.id.btnLoction);
        btnLoction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map = new Intent(Page_search.this, MapActivity.class);
                startActivityForResult(map,1000);
            }
        });

        btSearch = (Button) findViewById(R.id.btSearch);
        edtPersons = (EditText) findViewById(R.id.edtPersons);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mSearchField = (EditText) findViewById(R.id.search_field);
        search_btn = (Button) findViewById(R.id.search_btn);

        mRecyclerView = (RecyclerView) findViewById(R.id.result_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               // heading.setVisibility(View.INVISIBLE);
               // heading.setHeight(0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }
            }
        });


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Page_search.this, new DatePickerDialog.OnDateSetListener() {
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


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mSearchField.getText().toString();
                if (!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }

            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchStudio();
            }
        });

        initStudioData();

    }

    private void searchStudio() {
        ArrayList<Model_Studios> studiosFilter = new ArrayList<>();
        if (selectLocation != null && edtPersons.getText().toString().equals("")){
            studiosFilter = distanceLocation();

            searchAdapter = new AdapterRent(Page_search.this, studiosFilter, mSearchField);
            mRecyclerView.setAdapter(searchAdapter);
        }

        if(selectLocation != null && !edtPersons.getText().toString().equals("")){
            studiosFilter = distanceLocation();
            ArrayList<Model_Studios> tmp = new ArrayList<>();
           for (int i = 0; i < studiosFilter.size(); i++){
               for (int j = 0; j < rooms.size(); j++){
                   if (studiosFilter.get(i).getId().equals(rooms.get(j).getStudioId())
                       && Integer.parseInt(edtPersons.getText().toString()) <=  Integer.parseInt(rooms.get(j).getCapacity())
                           //if add status (boolean) && rooms.get(j).getStatus()
                   ){
                       tmp.add(studiosFilter.get(i));

                   }

               }
           }

            searchAdapter = new AdapterRent(Page_search.this, tmp, mSearchField);
            mRecyclerView.setAdapter(searchAdapter);

        }

    }

    private ArrayList<Model_Studios> distanceLocation(){
        ArrayList<Model_Studios> studiosFilter = new ArrayList<>();
        for (Model_Studios s: arrayList) {
            Double distance = distance(
                    s.getLat(),
                    s.getLng(),
                    selectLocation.getLatLng().latitude,
                    selectLocation.getLatLng().longitude);
            if (distance <= 10.0){
                studiosFilter.add(s);
            }
        }
        return studiosFilter;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    private void initStudioData() {
        firebaseManager = new FirebaseManager();
        firebaseManager.getStudios(new FirebaseCallBacks() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot child :dataSnapshot.getChildren()){
                    Model_Studios value = child.getValue(Model_Studios.class);
                    arrayList.add(value);
                }

                initRoomData();
            }

            @Override
            public void onLoading() { btSearch.setVisibility(View.GONE);}

            @Override
            public void onFailure() {
                Log.d("showAllRoom","onFailure");
            }
        });

    }

    private void initRoomData() {
        firebaseManager.getRooms(new FirebaseCallBacks() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot child :dataSnapshot.getChildren()){
                    Rooms value = child.getValue(Rooms.class);
                    rooms.add(value);
                }

                btSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoading() { btSearch.setVisibility(View.GONE);}

            @Override
            public void onFailure() {
                Log.d("showAllRoom","onFailure");
            }
        });

    }


    private void setAdapter(final String searchString) {
         databaseReference.child("Studios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //for every new search
                arrayList.clear();
                mRecyclerView.removeAllViews();

                int counter = 0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String name = snapshot.child("name").getValue(String.class);
                    String location = snapshot.child("location").getValue(String.class);
                    String logo = snapshot.child("logo").getValue(String.class);
                    String image = snapshot.child("image").getValue(String.class);
                    String id = snapshot.child("id").getValue(String.class);
                    String tag = snapshot.child("tag").getValue(String.class);

                    if (location.startsWith(searchString)){
//                        arrayList.add(new Model_Studios(name, location, logo, image, id, tag ));
                        counter++;
                    }

                    //for the top 15 results
                    if (counter  == 20){
                        break;
                    }
                }

                searchAdapter = new AdapterRent(Page_search.this, arrayList, mSearchField);
                mRecyclerView.setAdapter(searchAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK) {
            if (requestCode == 1000) {
                LatLng latlang =  new LatLng(
                        data.getDoubleExtra("lat",0.0),
                        data.getDoubleExtra("lng",0.0));
                selectLocation = new Location(latlang,data.getStringExtra("address"));

                btnLoction.setText(selectLocation.getAddress());

            }
        }
    }
}
