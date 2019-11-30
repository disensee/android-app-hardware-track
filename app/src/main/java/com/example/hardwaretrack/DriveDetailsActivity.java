package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DriveDetailsActivity extends AppCompatActivity {

    public static final String TAG = "DriveDetailsActivity";
    public static final String EXTRA_DRIVE_ID = "driveId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_details);
    }
}
