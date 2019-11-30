package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GPUDetailsActivity extends AppCompatActivity {

    public static final String TAG = "GPUDetailsActivity";
    public static final String EXTRA_GPU_ID = "gpuId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpudetails);
    }
}
