package com.amornchanok.nextstep_app.base;

import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

 public abstract class BaseActivity extends AppCompatActivity implements BaseInterfaceUI {

  private ProgressDialog progressDialog;

  @Override
  public void showProcessDialog() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);
    progressDialog.setMessage("Loading");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setProgress(0);
    progressDialog.show();
  }

  @Override
  public void hideProcessDialog() {
    progressDialog.dismiss();
  }

 }
