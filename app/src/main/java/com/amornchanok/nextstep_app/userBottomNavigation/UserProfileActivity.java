package com.amornchanok.nextstep_app.userBottomNavigation;

import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.MyApplication;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.firebaseConnect.UserInformation;
import com.amornchanok.nextstep_app.partnerRegister.PartnerConditionActivity;
import com.amornchanok.nextstep_app.userRegister.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    TextView textViewEmail;
    Button editProfile;
    TextView textViewName;
    TextView textViewPhone;
    TextView textViewDOB;

    String userID,userName,userEmail,userPhone;
    TextView tvUserName;
    ImageView imageProfile;
    Button btPhone,btMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        if(MyApplication.userId.equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(LoginActivity.IS_ANOTHER_WAY,true);
            startActivityForResult(intent,LoginActivity.CODE_LOGIN);
        }


        BottomNavigationView bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setSelectedItemId(R.id.profile);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myBooking:
                        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.noti:
                        startActivity(new Intent(getApplicationContext(), NotiActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });


//        Button b_next_1 = (Button) findViewById(R.id.b_next_1);
//        b_next_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(UserProfileActivity.this, Page_studio.class);
//                startActivity(i);
//            }
//        });

//        Button b_next_2 = (Button) findViewById(R.id.b_next_2);
//        b_next_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(UserProfileActivity.this, BookingActivity.class);
//                startActivity(i);
//            }
//        });



        Button b_action_1 = (Button) findViewById(R.id.b_action_1);
        b_action_1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {
                // Logout();

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserProfileActivity.this, HomeActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
                startActivity(intent);
            }
        });

        Button btnPartnerMode = (Button) findViewById(R.id.btnPartnerMode);
        btnPartnerMode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {

                Intent partnerMode = new Intent(UserProfileActivity.this, PartnerConditionActivity.class);
                startActivity(partnerMode);
            }

        });

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        userEmail = firebaseUser.getEmail();
//        btMail = (Button) findViewById(R.id.btMail);
//        btMail.setText("" + userEmail);
//        เอามาจาก CurrentUser

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        databaseReference.child("Users/"+userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private  void showData(DataSnapshot snapshot) {

        UserInformation uInfo = snapshot.getValue(UserInformation.class);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserName.setText(uInfo.getName() + " " + " " + uInfo.getLastName());
        btMail = (Button) findViewById(R.id.btMail);
        btMail.setText(uInfo.getEmail());
        btPhone = (Button) findViewById(R.id.btPhone);
        btPhone.setText(uInfo.getPhone());
        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        Picasso.get().load(uInfo.getImageProfile()).into(imageProfile);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == LoginActivity.CODE_LOGIN && requestCode == RESULT_OK){
        }
    }
}