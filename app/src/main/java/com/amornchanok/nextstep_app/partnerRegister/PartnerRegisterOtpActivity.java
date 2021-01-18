package com.amornchanok.nextstep_app.partnerRegister;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amornchanok.nextstep_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PartnerRegisterOtpActivity extends AppCompatActivity {
    EditText edt_phone , edtOtp;
    Button btSendOtp , btOtpNext;
    TextView textCountdown;
    String phoneNumber, otp;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_register_otp);

        edt_phone = findViewById(R.id.edt_phone);
        edtOtp = findViewById(R.id.edtOtp);
        btSendOtp = findViewById(R.id.btSendOtp);
        btOtpNext = findViewById(R.id.btOtpNext);
        textCountdown = findViewById(R.id.textCountdown);

        StartFirebaseLogin();

        btSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                phoneNumber= "+66"+edt_phone.getText().toString();
                phoneNumber= "+66"+edt_phone.getText().toString().trim().substring(1);
                String f = phoneNumber;
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,                     // Phone number to verify
                        60,                           // Timeout duration
                        TimeUnit.SECONDS,                // Unit of timeout
                        PartnerRegisterOtpActivity.this,        // Activity (for callback binding)
                        mCallback);                      // OnVerificationStateChangedCallbacks
            }
        });


        btOtpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp=edtOtp.getText().toString();

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);

                SigninWithPhone(credential);
            }
        });

    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(PartnerRegisterOtpActivity.this,PartnerRegisterImageActivity.class));
                            finish();
                        } else {
                            Toast.makeText(PartnerRegisterOtpActivity.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void StartFirebaseLogin() {
        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(PartnerRegisterOtpActivity.this,"verification completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d("verify fail",e.toString());
                Toast.makeText(PartnerRegisterOtpActivity.this,"verification fialed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;

                countdownText();
                Toast.makeText(PartnerRegisterOtpActivity.this,"Code sent",Toast.LENGTH_SHORT).show();

            }
        };
    }

    private void countdownText() {
        btSendOtp.setVisibility(View.INVISIBLE);
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                textCountdown.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                textCountdown.setText("done!");
                btSendOtp.setVisibility(View.VISIBLE);
            }

        }.start();
    }
}