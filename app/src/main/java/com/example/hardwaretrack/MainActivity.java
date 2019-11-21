package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

public class MainActivity extends AppCompatActivity {
    SQLComputerDataAccess computerDa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        computerDa = new SQLComputerDataAccess(this);

    }
}
