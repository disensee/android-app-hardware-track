package com.example.hardwaretrack;

import android.app.Application;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class CustomApp extends Application {

    public static final String TAG = "CustomApp";

    public static void selectSpinnerItemByValue(Spinner spinner, String value) {
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position) == value) {
                spinner.setSelection(position);
                return;
            }
        }
    }
}
