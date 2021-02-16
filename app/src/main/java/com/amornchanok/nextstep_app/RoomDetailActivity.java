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
import com.amornchanok.nextstep_app.partnerBottomNavigation.PartnerManageActivity;
import com.bumptech.glide.signature.ObjectKey;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class RoomDetailActivity extends AppCompatActivity {

    CustomCalendar customCalendar;

    TextView text_name;
    TextView text_url;
    TextView text_price;
    TextView text_capacity;
    TextView tvResult;

    ImageView imageView;
    Button bt_book;
    Button btStatusEmpty2;

    public static final String Firebase_Server_URL = "https://nextstepapp-740cf.firebaseio.com/";

    String studioId;
    String room_name;
    String room_image;
    String room_price;
    String room_capacity;
    String room_imageurl;
    int result;

    int status = 3;

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

        btStatusEmpty2 = findViewById(R.id.btStatusEmpty2);
        tvResult = findViewById(R.id.tvResult);

        customCalendar = findViewById(R.id.custom_calendar);
        HashMap<Object, Property> descHashMap = new HashMap<>();
        Property defaultProperty = new Property();
        defaultProperty.layoutResource = R.layout.default_view;
        defaultProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("default",defaultProperty);

        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.current_view;
        currentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("current",currentProperty);

        Property presentProperty = new Property();
        presentProperty.layoutResource = R.layout.present_view;
        presentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("present",presentProperty);

        Property absentProperty = new Property();
        absentProperty.layoutResource = R.layout.absent_view;
        absentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("absent",absentProperty);

        customCalendar.setMapDescToProp(descHashMap);

        HashMap<Integer, Object> dateHashMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        dateHashMap.put(calendar.get(Calendar.DAY_OF_MONTH),"current");
//        dateHashMap.put(1,"present");
//        dateHashMap.put(2,"absent");
//        dateHashMap.put(3,"present");
//        dateHashMap.put(4,"absent");
//        dateHashMap.put(20,"present");
//        dateHashMap.put(24,"absent");

        customCalendar.setDate(calendar,dateHashMap);
        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                String sDate = selectedDate.get(Calendar.DAY_OF_MONTH)
                        + "/" + (selectedDate.get(Calendar.MONTH) + 1)
                        + "/" + selectedDate.get(Calendar.YEAR);

                Toast.makeText(getApplicationContext(),sDate,Toast.LENGTH_SHORT).show();
            }
        });


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
        room_price= intent.getStringExtra("roomPrice");
        room_name = intent.getStringExtra("roomName");
        room_capacity = intent.getStringExtra("roomCapacity");
        room_imageurl = intent.getStringExtra("roomImage");

        text_url.setText("" + room_imageurl);
        text_name.setText("" + room_name);
        text_price.setText("" + room_price + " บาท / ชั่วโมง ");
        text_capacity.setText("" + room_capacity);

        Picasso.get().load("" + room_imageurl).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);

        studioId = getIntent().getStringExtra("Studio_id");
        roomId = getIntent().getStringExtra("roomId");
        room_price = getIntent().getStringExtra("roomPrice");
        room_image = getIntent().getStringExtra("roomImage");
        room_capacity = getIntent().getStringExtra("roomCapacity");

        btStatusEmpty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(room_price);
                result = status*i;
                String s = String.valueOf(result);
                tvResult.setText(s + " บาท");

            }
        });

// Booking

        bt_book = (Button) findViewById(R.id.bt_book);
        bt_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.valueOf(room_price);
                result = status*i;
                String s = String.valueOf(result);
                tvResult.setText(s + " บาท");

                Booking data_input = new Booking();
                GetDataFromText();

                Calendar calendar = Calendar.getInstance();
                currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
                currentTime=format.format(calendar.getTime());

                data_input.setStudio_id(studioId);
                data_input.setRoom_id(roomId);
                data_input.setRoom_id(roomId);
                data_input.setRoom_name(room_name);
                data_input.setRoom_img(room_image);
                data_input.setRoom_price(s);
                data_input.setUser_id(userID);
                data_input.setDate(currentDate);
                data_input.setTime(currentTime);

                String StudentRecordIDFromServer = databaseReference.push().getKey();
                databaseReference.child(StudentRecordIDFromServer).setValue(data_input);

//                Toast.makeText(RoomDetailActivity.this, "การจองสำเร็จ! ตรวจสอบการจองได้ที่ การจองของฉัน", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), CheckDetailBookingActivity.class);
                startActivity(intent);

            }
        });
    }

    public void GetDataFromText() {
        room_name = text_name.getText().toString().trim();

    }
}
