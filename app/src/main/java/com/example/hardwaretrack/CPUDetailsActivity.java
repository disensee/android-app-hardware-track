package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CPUDetailsActivity extends AppCompatActivity {
    public static final String TAG = "CPUDetailsActivity";
    public static final String EXTRA_CPU_ID = "CPUId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpudetails);
    }
}
