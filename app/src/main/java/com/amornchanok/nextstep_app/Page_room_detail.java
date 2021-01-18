package com.amornchanok.nextstep_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.modelStudioList.Booking;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Page_room_detail extends AppCompatActivity {


    TextView text_Category_id;
    TextView text_id;
    TextView text_name;
    TextView text_url;
    TextView text_describtion;


    ImageView imageView;
    Button b_next_1;
    String s_url;

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://nextstepapp-740cf.firebaseio.com/";

    // Declaring String variables to store name & phone number get from EditText.
    String categoryid;
    String s_room_name;
    String s_room_image;

    Firebase firebase;

    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Booking";


    ////////////////////////////////////////////////////////
    FirebaseUser firebaseUser;
    String userID;
    TextView text_user_id;
    ////////////////////////////////////////////////////////
    public String currentDate,currenttime;
    String roomId;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.page_room_detail);
///////////////////////////////////////////////////////////////////////////////////////
        Firebase.setAndroidContext(Page_room_detail.this);
        firebase = new Firebase(Firebase_Server_URL);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        ///////////////////////////////////////////////////////////
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid();
        /////////////////////////////////////////////////////////
        imageView = (ImageView) findViewById(R.id.imageView);


        text_Category_id = (TextView) findViewById(R.id.text_Category_id);
        text_id = (TextView) findViewById(R.id.text_id);
        text_name = (TextView) findViewById(R.id.text_name);
        text_describtion = (TextView) findViewById(R.id.text_describtion);
        text_url = (TextView) findViewById(R.id.text_url);


        Intent intent = getIntent();
        //String s_id = intent.getStringExtra("id");
        //String s_id = intent.getStringExtra(INTENT_STUDIO_ID);

        String s_Category_id = intent.getStringExtra("Category_id");
        String s_id= intent.getStringExtra("roomId");
        String s_name = intent.getStringExtra("roomName");
        String s_FoodDescribtion = intent.getStringExtra("roomDescribtion");
        s_url = intent.getStringExtra("roomImage");


        text_Category_id.setText("" + s_Category_id);
        text_id.setText("" + s_id);
        text_url.setText("" + s_url);
        text_name.setText("" + s_name);
        text_describtion.setText("" + s_FoodDescribtion);

        Picasso.get()
                //.load(get(s_url).getI_url())
                .load("" + s_url)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(imageView);

        categoryid=getIntent().getStringExtra("Category_id");
        roomId=getIntent().getStringExtra("roomId");
        s_room_image=getIntent().getStringExtra("roomImage");


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
//



                // Adding name into class function object.
                data_input.setCategory_id(categoryid);
                data_input.setRoom_id(roomId);
                data_input.setRoom_name(s_room_name);
                data_input.setRoom_img(s_room_image);
                data_input.setUser_id(userID);

                data_input.setDate(currentDate);
                data_input.setTime(currenttime);

//
//                roompricestr = getIntent().getStringExtra("roomPrice");
//                roomquantitystr = getIntent().getStringExtra("roomQuantity");
//                roomdescriptionstr = getIntent().getStringExtra("roomDescribtion");
//                room_name.setText("Name: " + getIntent().getStringExtra("roomName"));
//                room_description.setText("Description: " + getIntent().getStringExtra("roomDescribtion"));
//                room_price.setText("Price = " + getIntent().getStringExtra("roomPrice"));
//                room_quantity.setText("Quantity: " + getIntent().getStringExtra("roomQuantity"));
//                Picasso.get().load(getIntent().getStringExtra("roomImage")).into(room_image);


                // Getting the ID from firebase database.
                String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(StudentRecordIDFromServer).setValue(data_input);

                // Showing Toast message after successfully data submit.
                Toast.makeText(Page_room_detail.this, "การจองสำเร็จ! ตรวจสอบการจองได้ที่ การจองของฉัน", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void GetDataFromText() {

        s_room_name = text_name.getText().toString().trim();

        // NumberHolder = editText_number.getText().toString().trim();

    }
}
