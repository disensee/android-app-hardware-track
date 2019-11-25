package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.hardwaretrack.models.Computer;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLComputerDataAccess computerDa;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        computerDa = new SQLComputerDataAccess(this);

        ArrayList<Computer> pcList = computerDa.getAllComputers();

        Computer pc = computerDa.getComputerById(1);
        Log.d(TAG, pc.toString());
    }
}
