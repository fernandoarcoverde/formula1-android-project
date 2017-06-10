package com.unibratec.ads.formula1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by root on 08/06/17.
 */

public class CustomDialog {

    private AlertDialog alertDialog;

    public void simpleMessage(Context context, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_custom_alert_dialog);
        builder.setTitle(title);
        builder.setMessage(message);
        //  builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }


}
