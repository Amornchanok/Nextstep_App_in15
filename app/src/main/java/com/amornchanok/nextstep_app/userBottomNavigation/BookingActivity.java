package com.amornchanok.nextstep_app.userBottomNavigation;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.amornchanok.nextstep_app.MyBookingAdapter;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.firebaseConnect.MyBooking;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    private String userid;

    TextView text_user_id;
    DatabaseReference databaseReference;
    MyBookingAdapter myAdapter;
    List<MyBooking> uploadList;
    RecyclerView recycleView;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        BottomNavigationView bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setSelectedItemId(R.id.myBooking);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myBooking:
                        return true;
                    case R.id.noti:
                        startActivity(new Intent(getApplicationContext(), NotiActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getUid();
        text_user_id = (TextView) findViewById(R.id.text_user_id);
        text_user_id.setText(userid);

        recycleView=findViewById(R.id.recycleviewId);
        recycleView.setHasFixedSize(true);
        progressBar=findViewById(R.id.imageProgressBar);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        uploadList=new ArrayList<>();

        firebaseStorage= FirebaseStorage.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("Booking");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //duplicate value remove
                uploadList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MyBooking upload=dataSnapshot.getValue(MyBooking.class);
                    assert upload != null;
                    upload.setRoom_id(dataSnapshot.getKey());
                    uploadList.add(upload);
                }
                myAdapter=new MyBookingAdapter(BookingActivity.this,uploadList);
                recycleView.setAdapter(myAdapter);

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(BookingActivity.this, "Error: "+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
