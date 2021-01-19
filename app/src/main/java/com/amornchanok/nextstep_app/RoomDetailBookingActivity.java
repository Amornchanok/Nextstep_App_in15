package com.amornchanok.nextstep_app;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.amornchanok.nextstep_app.firebaseStudio.Booking;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RoomDetailBookingActivity extends AppCompatActivity {
    TextView room_name, room_description, room_price, room_quantity;
    ImageView room_image;

    private String userid;

    String roompricestr, roomquantitystr, roomnamestr, roomid, roomdescriptionstr;
    int roompriceint, roomquantityint;


    Button b_next_1;

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://nextstepapp-740cf.firebaseio.com/";

    // Declaring String variables to store name & phone number get from EditText.
    String studioId;
    String s_room_name;
    String s_room_image;

    Firebase firebase;
    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Booking";

    FirebaseUser firebaseUser;
    String userID;
    TextView text_user_id;

    public String currentDate,currenttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_booking);

        Firebase.setAndroidContext(RoomDetailBookingActivity.this);
        firebase = new Firebase(Firebase_Server_URL);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid();



        // btnCart=(FloatingActionButton)findViewById(R.id.btncart);
        room_name = (TextView) findViewById(R.id.room_name);
        room_description = (TextView) findViewById(R.id.room_description);
        room_price = (TextView) findViewById(R.id.room_price);
        room_quantity = (TextView) findViewById(R.id.room_Quantity);
        room_image = (ImageView) findViewById(R.id.img_room);
        //collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        //collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        //collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        studioId = getIntent().getStringExtra("studioId");
        roomid=getIntent().getStringExtra("roomId");
        s_room_image=getIntent().getStringExtra("roomImage");

        if (getIntent() != null) {
            roompricestr = getIntent().getStringExtra("roomPrice");
            roomquantitystr = getIntent().getStringExtra("roomQuantity");
            roomdescriptionstr = getIntent().getStringExtra("roomDescribtion");
            room_name.setText("Name: " + getIntent().getStringExtra("roomName"));
            room_description.setText("Description: " + getIntent().getStringExtra("RoomDescribtion"));
            room_price.setText("Price = " + getIntent().getStringExtra("roomPrice"));
            room_quantity.setText("Quantity: " + getIntent().getStringExtra("roomQuantity"));
            Picasso.get().load(getIntent().getStringExtra("roomImage")).into(room_image);
            //   foodnamestr=food_name.getText().toString();
            //  collapsingToolbarLayout.setTitle(foodnamestr);
        }
        roompriceint = Integer.parseInt(roompricestr);
        roomquantityint = Integer.parseInt(roomquantitystr);


        b_next_1 = (Button) findViewById(R.id.b_next_1);
        b_next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Booking data_input = new Booking();

                GetDataFromText();

                Calendar calendar = Calendar.getInstance();
                currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
                currenttime=format.format(calendar.getTime());
                // String yalhwy=String.valueOf(totalprice);




                // Adding name into class function object.
                data_input.setStudio_id(studioId);
                data_input.setRoom_id(roomid);
                data_input.setRoom_name(s_room_name);
                data_input.setRoom_img(s_room_image);
                data_input.setUser_id(userID);
                data_input.setDate(currentDate);
                data_input.setTime(currenttime);

//
//                foodpricestr = getIntent().getStringExtra("FoodPrice");
//                foodquantitystr = getIntent().getStringExtra("FoodQuantity");
//                fooddescriptionstr = getIntent().getStringExtra("FoodDescribtion");
//                food_name.setText("Name: " + getIntent().getStringExtra("FoodName"));
//                food_description.setText("Description: " + getIntent().getStringExtra("FoodDescribtion"));
//                food_price.setText("Price = " + getIntent().getStringExtra("FoodPrice"));
//                food_quantity.setText("Quantity: " + getIntent().getStringExtra("FoodQuantity"));
//                Picasso.get().load(getIntent().getStringExtra("FoodImage")).into(food_image);


                // Getting the ID from firebase database.
                String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(StudentRecordIDFromServer).setValue(data_input);

                // Showing Toast message after successfully data submit.
                Toast.makeText(RoomDetailBookingActivity.this, "บันทึกการจองแล้ว!", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void GetDataFromText() {

        s_room_name = room_name.getText().toString().trim();

        // NumberHolder = editText_number.getText().toString().trim();

    }
}
