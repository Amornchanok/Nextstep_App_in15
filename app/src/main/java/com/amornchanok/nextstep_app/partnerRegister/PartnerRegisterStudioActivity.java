package com.amornchanok.nextstep_app.partnerRegister;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.Model_Studios;
import com.amornchanok.nextstep_app.R;
import com.amornchanok.nextstep_app.partnerBottomNavigation.PartnerManageActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class PartnerRegisterStudioActivity extends AppCompatActivity {
    private String Price, Name, saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef, sellersRef;
    private ProgressDialog loadingBar;


    String CategoryName = "1";
    String sID = "1";
    String sName = "1";
    String sAddres = "1";
    String sPhone = "1";
    String sEmail = "1";

    String s_orderid;
    String s_orderdate;
    String s_orderstatus;
    String s_ordertotalprice;
    String s_orderuserupimage;
    String s_rentuser;

//    TextView text_orderid;
//    TextView text_orderdate;
//    TextView text_orderstatus;
//    TextView text_ordertotalprice;
//    TextView text_orderuserupimage;
//    TextView text_rentuser;


    EditText text_orderid;
    EditText text_orderdate;
    EditText text_orderstatus;
    EditText text_ordertotalprice;
    EditText text_orderuserupimage;
    EditText text_rentuser;

    Spinner spin;

    String spin_val;

    String[] gender = { "promotion", "suggest" };
    //array of strings used to populate the spinner
EditText text_name;
    EditText text_location;

   // FirebaseDatabase database;
    DatabaseReference reference;
    //Studios studios;
    int id = 0;

    FirebaseDatabase database;
    DatabaseReference ref;

    int maxid = 0;
    Model_Studios member;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_register_studio);


        member = new Model_Studios();
        ref = database.getInstance().getReference().child("Studios");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (int) dataSnapshot.getChildrenCount();

                }
                else
                {
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

////////////////////////////////////////////////////////////////////////////////////////////
        spin = (Spinner) findViewById(R.id.spinner);//fetching view’s id
           //Register a callback to be invoked when an item in this AdapterView has been selected
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                spin_val = gender[position];//saving the value selected
               // Toast.makeText(getApplicationContext(),gender[position] , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {//
                // Toast.makeText(getApplicationContext(),gender[position] , Toast.LENGTH_LONG).show();
            }
        });
            //setting array adaptors to spinners
      //ArrayAdapter is a BaseAdapter that is backed by an array of arbitrary objects
        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(PartnerRegisterStudioActivity.this, android.R.layout.simple_spinner_item, gender);
         // setting adapter to spinner
        spin.setAdapter(spin_adapter);

/////////////////////////////////////////////////////////////////////////////////////////////////////////


//        CategoryName = getIntent().getExtras().get("category").toString();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("image_upload");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Studios");
//        sellersRef = FirebaseDatabase.getInstance().getReference().child("Sellers");


        text_name = (EditText) findViewById(R.id.text_name);
        text_location= (EditText) findViewById(R.id.text_location);


//        Intent intent = getIntent();
//        s_orderid = intent.getStringExtra("orderid");
//        s_orderdate = intent.getStringExtra("orderdate");
//        s_orderstatus = intent.getStringExtra("orderstatus");
//        s_ordertotalprice = intent.getStringExtra("ordertotalprice");
//        s_orderuserupimage = intent.getStringExtra("orderuserupimage");
//        s_rentuser = intent.getStringExtra("rentuser");
//
//        text_orderid.setText("" + s_orderid);
//        text_orderdate.setText("" + s_orderdate);
//        text_orderstatus.setText("" + s_orderstatus);
//        text_ordertotalprice.setText("" + s_ordertotalprice);
//        text_orderuserupimage.setText("" + s_orderuserupimage);
//        text_rentuser.setText("" + s_rentuser);


//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    id = (int) snapshot.getChildrenCount();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        AddNewProductButton = (Button) findViewById(R.id.add_new_product);
        InputProductImage = (ImageView) findViewById(R.id.select_product_image);
        loadingBar = new ProgressDialog(this);
        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });


        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });

        Intent intent = new Intent(getApplicationContext(), PartnerManageActivity.class);
        startActivity(intent);

//        sellersRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            sName = dataSnapshot.child("name").getValue().toString();
//                            sAddres = dataSnapshot.child("address").getValue().toString();
//                            sPhone = dataSnapshot.child("phone").getValue().toString();
//                            sID = dataSnapshot.child("sid").getValue().toString();
//                            sEmail = dataSnapshot.child("email").getValue().toString();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
    }


    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);

        }
    }


    private void ValidateProductData() {

       // Price = text_ordertotalprice.getText().toString();
        Name = text_name.getText().toString();


        if (ImageUri == null) {
            Toast.makeText(this, "input image...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "input text...", Toast.LENGTH_SHORT).show();
        } else {
            StoreProductInformation();
        }
    }


    private void StoreProductInformation() {
        loadingBar.setTitle("เพิ่ม ");
        loadingBar.setMessage("โปรดรอสักครู่");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        productRandomKey = saveCurrentDate + saveCurrentTime;


        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(PartnerRegisterStudioActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
            @Override
            public void onSuccess(TaskSnapshot taskSnapshot) {
                Toast.makeText(PartnerRegisterStudioActivity.this, "อัปโหลดรูปภาพ เรียบร้อยแล้ว...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(PartnerRegisterStudioActivity.this, "get URL...", Toast.LENGTH_SHORT).show();
                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
    }


    private void SaveProductInfoToDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();

        // Getting the ID from firebase database.
        //String idFromServer = databaseReference_1.push().getKey();
        String idFromServer = ProductsRef.push().getKey();

        String numberAsString = Integer.toString(maxid+1);



        //productMap.put("id", String.valueOf(id));
        productMap.put("id", numberAsString);
        productMap.put("name", text_name.getText().toString().trim());
        productMap.put("location", text_location.getText().toString().trim());
        productMap.put("image", downloadImageUrl);
        productMap.put("logo", downloadImageUrl);
        productMap.put("tag", spin_val);


       ProductsRef.child(idFromServer).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Intent intent = new Intent(Admin_add_product.this, SellerHomeActivity.class);
                            // Intent intent = new Intent(Page_user_my_order_upload.this, Admin_product.class);
                            // startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(PartnerRegisterStudioActivity.this, "บันทึกข้อมูลสตูดิโอแล้ว", Toast.LENGTH_SHORT).show();
                        } else {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(PartnerRegisterStudioActivity.this, "มีข้อผิดพลาด: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}

