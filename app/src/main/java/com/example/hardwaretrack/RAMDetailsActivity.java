package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RAMDetailsActivity extends AppCompatActivity {

    public static final String TAG = "RAMDetailsActivity";
    public static final String EXTRA_RAM_ID = "ramId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramdetails);
    }
}
