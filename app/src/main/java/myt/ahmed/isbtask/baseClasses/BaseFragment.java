package myt.ahmed.isbtask.baseClasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import myt.ahmed.isbtask.R;
import myt.ahmed.isbtask.dagger.DaggerApplication;

/**
 * Created by ahmed on 7/15/2017.
 */

public class BaseFragment extends Fragment {

    FlipProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerApplication)getActivity().getApplication()).getAppComponent().inject(this);

        init();

    }

    void init(){
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.ic_hourglass_empty_white_24dp);
        imageList.add(R.drawable.ic_hourglass_full_white_24dp);
        progressDialog = new FlipProgressDialog();
        progressDialog.setImageList(imageList);
        progressDialog.setOrientation("rotationY");
        progressDialog.setCancelable(false);
        progressDialog.setDimAmount(0.8f);
        progressDialog.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));

        //progressDialog.setBackgroundColor(Color.parseColor("#FFFFFF"));

    }
    public void showProgress(){
        progressDialog.show(getActivity().getFragmentManager(),"l");
    }

    public void hideProgress(){
        progressDialog.dismiss();
    }

    public void getSuccess(String message, final int type){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
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

    public void getWaring(String message, final int type){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
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


    public void getInternetErrorMessage(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
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
        return email.contains("@") &&  email.contains(".");
    }
    boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >=8;
    }
}
