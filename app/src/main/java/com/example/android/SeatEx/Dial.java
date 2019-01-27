package com.example.android.SeatEx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import java.lang.reflect.Method;

/**
 * Created by raj garg on 24-01-2019.
 */

public class Dial extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmation Box").setMessage("Are you really interested in the seat??").setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Seats.flag=1;
                System.out.println("(((((((((((((((()))))))))))))))))))))))"+Seats.flag);
            }
        });
        return builder.create();
    }
}
