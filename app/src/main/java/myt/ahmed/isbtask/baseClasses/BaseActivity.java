package myt.ahmed.isbtask.baseClasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;
import myt.ahmed.isbtask.R;
import myt.ahmed.isbtask.dagger.DaggerApplication;

/**
 * Created by ahmed shabaan and  eddit form new lap topon 7/8/2017.
 * 4-check inter net with rerofit done
 * 1- create  animation for fragments done
 * 2-login function
 * 3-reset and forget password
 */

public class BaseActivity extends AppCompatActivity {

    FlipProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);


        init();
    }

    void init() {
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.ic_hourglass_empty_white_24dp);
        imageList.add(R.drawable.ic_hourglass_full_white_24dp);
        progressDialog = new FlipProgressDialog();
        progressDialog.setImageList(imageList);
        progressDialog.setOrientation("rotationY");
        progressDialog.setCancelable(false);
        progressDialog.setDimAmount(0.8f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressDialog.setBackgroundColor(getColor(R.color.colorAccent));
        } else {
            progressDialog.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        }
    }

    public void showProgress() {
        progressDialog.show(getFragmentManager(), "l");
    }

    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void getSuccess(String message, final int type) {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(getString(R.string.success))
                .setContentText(message)
                .setConfirmText(getString(R.string.ok1))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();



                    }
                })
                .show();
    }

    public void getWaring(String message, final int type) {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.warning))
                .setContentText(message)
                .setConfirmText(getString(R.string.ok1))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();


                    }
                })
                .show();
    }


    public void getInternetErrorMessage() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.warning))
                .setContentText(getString(R.string.internet_error))
                .setConfirmText(getString(R.string.ok1))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                    }
                })
                .show();
    }

    // show gps dialog to get the permission
    public void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent); // start gps settings to get the permission
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    public boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && email.contains(".");
    }

    boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 8;
    }


}
