package com.example.hardwaretrack;

import android.app.Application;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class CustomApp extends Application {

    public static final String TAG = "CustomApp";

    public static void selectSpinnerItemByValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                spinner.setSelection(i);
            }
        }
    }
}
