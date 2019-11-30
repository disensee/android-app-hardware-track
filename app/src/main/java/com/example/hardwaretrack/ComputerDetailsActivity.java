package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ComputerDetailsActivity extends AppCompatActivity {

    public static final String TAG = "ComputerDetailsActivity";
    public static final String EXTRA_COMPUTER_ID = "computerId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_details);
    }
}
