package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        Log.d(TAG, pcList.toString());

    }

    public void buttonClicked(View v){
        int viewId = v.getId();
        String resName = getResources().getResourceEntryName(viewId);
        Intent i;

        switch(resName){
            case "btnAllComputers":
                i = new Intent(this, ComputerListActivity.class);
                startActivity(i);
                break;
            case "btnAllCPUs":
                i = new Intent(this, CPUListActivity.class);
                startActivity(i);
                break;
        }
    }
}
