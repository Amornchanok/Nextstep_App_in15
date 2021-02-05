package com.amornchanok.nextstep_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.firebaseConnect.Booking;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RoomDetailActivity extends AppCompatActivity {

    TextView text_name;
    TextView text_url;
    TextView text_price;
    TextView text_capacity;

    ImageView imageView;
    Button bt_book;

    public static final String Firebase_Server_URL = "https://nextstepapp-740cf.firebaseio.com/";

    String studioId;
    String room_name;
    String room_image;
    String room_price;
    String room_capacity;
    String room_imageurl;

    Firebase firebase;
    DatabaseReference databaseReference;

    public static final String Database_Path = "Booking";

    FirebaseUser firebaseUser;
    String userID;

    public String currentDate,currentTime;
    String roomId;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_room_detail);

        Firebase.setAndroidContext(RoomDetailActivity.this);
        firebase = new Firebase(Firebase_Server_URL);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid();

        imageView = (ImageView) findViewById(R.id.imageView);
        text_name = (TextView) findViewById(R.id.text_name);
        text_price = (TextView) findViewById(R.id.text_price);
        text_capacity = (TextView) findViewById(R.id.text_capacity);
        text_url = (TextView) findViewById(R.id.text_url);

        Intent intent = getIntent();
//        String s_Studio_id = intent.getStringExtra("Studio_id");
//        String s_id= intent.getStringExtra("roomId");
        room_price= intent.getStringExtra("roomPrice");
        room_name = intent.getStringExtra("roomName");
        room_capacity = intent.getStringExtra("roomCapacity");
        room_imageurl = intent.getStringExtra("roomImage");

        text_url.setText("" + room_imageurl);
        text_name.setText("" + room_name);
        text_price.setText("" + room_price + "บาท/ชั่วโมง");
        text_capacity.setText("" + room_capacity);

        Picasso.get().load("" + room_imageurl).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);

        studioId = getIntent().getStringExtra("Studio_id");
        roomId = getIntent().getStringExtra("roomId");
        room_price = getIntent().getStringExtra("roomPrice");
        room_image = getIntent().getStringExtra("roomImage");
        room_capacity = getIntent().getStringExtra("roomCapacity");


// Booking

        bt_book = (Button) findViewById(R.id.bt_book);
        bt_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Booking data_input = new Booking();
                GetDataFromText();

                Calendar calendar = Calendar.getInstance();
                currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
                currentTime=format.format(calendar.getTime());

                // Adding name into class function object.
                data_input.setStudio_id(studioId);
                data_input.setRoom_id(roomId);
                data_input.setRoom_name(room_name);
                data_input.setRoom_img(room_image);
//                data_input.setRoom_price(room_price);
                data_input.setUser_id(userID);
                data_input.setDate(currentDate);
                data_input.setTime(currentTime);

                // Getting the ID from firebase database.
                String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(StudentRecordIDFromServer).setValue(data_input);

                // Showing Toast message after successfully data submit.
                Toast.makeText(RoomDetailActivity.this, "การจองสำเร็จ! ตรวจสอบการจองได้ที่ การจองของฉัน", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void GetDataFromText() {
        room_name = text_name.getText().toString().trim();

    }
}
