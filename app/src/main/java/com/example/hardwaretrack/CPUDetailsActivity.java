package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hardwaretrack.models.CPU;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

public class CPUDetailsActivity extends AppCompatActivity {

    public static final String TAG = "CPUDetailsActivity";
    public static final String EXTRA_CPU_ID = "cpuId";
    SQLComputerDataAccess da;
    CPU cpu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpudetails);
    }
}
