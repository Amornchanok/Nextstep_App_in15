package com.amornchanok.nextstep_app.partnerRegister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.MyApplication;
import com.amornchanok.nextstep_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class PartnerRegisterProfileActivity extends AppCompatActivity  {
    EditText edt_firstname,edt_lastname,edt_email,edt_password,edt_birthday,edt_gender;
    Button bt_regPartnerNext;
    int i = 0;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_register_studio);

//        fAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//
//        edt_firstname = findViewById(R.id.edt_firstname);
//        edt_lastname = findViewById(R.id.edt_lastname);
//        edt_email = findViewById(R.id.edt_email);
//        edt_password = findViewById(R.id.edt_password);
//        edt_birthday = findViewById(R.id.edt_birthday);
//        bt_regPartnerNext = findViewById(R.id.bt_regPartner_next);
//
//        bt_regPartnerNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkField(edt_firstname);
//                checkField(edt_lastname);
//                checkField(edt_email);
//                checkField(edt_password);
//                checkField(edt_birthday);
//
//        if(valid){
//            MyApplication.partner.setFirstname(edt_firstname.getText().toString());
//            MyApplication.partner.setLastname(edt_lastname.getText().toString());
//            MyApplication.partner.setEmail(edt_email.getText().toString());
//            MyApplication.partner.setPassword(edt_password.getText().toString());
//            MyApplication.partner.setBirthday(edt_birthday.getText().toString());
//
//            startActivity(new Intent(PartnerRegisterProfileActivity.this, PartnerRegisterOtpActivity.class));
//            finish();
//
//            // start user activity_user_register
//            fAuth.createUserWithEmailAndPassword(edt_email.getText().toString(),edt_password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                @Override
//                public void onSuccess(AuthResult authResult) {
//                    FirebaseUser user = fAuth.getCurrentUser();
//                    Toast.makeText(PartnerRegisterProfileActivity.this,"สร้างบัญชีผู้ใช้",Toast.LENGTH_SHORT).show();
//                    DocumentReference df = fStore.collection("Users").document(user.getUid());
//                    Map<String,Object> userinfo = new HashMap<>();
//                    userinfo.put("Firstname",edt_firstname.getText().toString());
//                    userinfo.put("Lastname",edt_lastname.getText().toString());
//                    userinfo.put("UserEmail",edt_email.getText().toString());
//                    userinfo.put("Password",edt_password.getText().toString());
////
////                    if  (reg_partner.isChecked()) {
////                        userinfo.put("isPartner","1");
////                    }else if (reg_user.isChecked()) {
////                        userinfo.put("isUser", "0");
////                    }
//                    df.set(userinfo);
//
//                    startActivity(new Intent(getApplicationContext(), PartnerRegisterImageActivity.class));
//                    finish();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(PartnerRegisterProfileActivity.this, "ไม่สามารถสร้างบัญชีได้", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });
//
//    }
//
//    public boolean checkField(EditText textField) { {
//            if (textField.getText().toString().trim().isEmpty()){
//                textField.setError("Error");
//                valid = false;
//            }else{
//                valid = true;
//            }
//        }
//        return valid;
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
