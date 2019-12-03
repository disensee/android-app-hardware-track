package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hardwaretrack.models.CPU;
import com.example.hardwaretrack.models.Computer;
import com.example.hardwaretrack.models.Drive;
import com.example.hardwaretrack.models.GPU;
import com.example.hardwaretrack.models.RAM;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

import java.util.ArrayList;

public class ComputerDetailsActivity extends AppCompatActivity {

    public static final String TAG = "ComputerDetailsActivity";
    public static final String EXTRA_COMPUTER_ID = "computerId";

    private SQLComputerDataAccess da;
    private Computer pc;

    private Spinner spType;
    private EditText txtManufacturer;
    private EditText txtModel;
    private CheckBox chkCustomBuild;
    private Spinner spCPU;
    private Spinner spGPU;
    private Spinner spDrive;
    private Spinner spRAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_details);

        da = new SQLComputerDataAccess(this);
        spType = findViewById(R.id.spComputerType);
        txtManufacturer = findViewById(R.id.txtComputerManufacturer);
        txtModel = findViewById(R.id.txtComputerModel);
        chkCustomBuild = findViewById(R.id.chkCustomBuild);
        spCPU = findViewById(R.id.spComputerCPU);
        spGPU = findViewById(R.id.spComputerGPU);
        spDrive = findViewById(R.id.spComputerDrive);
        spRAM = findViewById(R.id.spComputerRAM);

        ArrayList<CPU> cpuList = da.getAllCPUs();
        ArrayList<GPU> gpuList = da.getAllGPUs();
        ArrayList<Drive> driveList = da.getAllDrives();
        ArrayList<RAM> ramList = da.getAllRAM();

        ArrayAdapter<CPU> cpuAdapter = new ArrayAdapter<CPU>(this, R.layout.support_simple_spinner_dropdown_item, cpuList);
        cpuAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<GPU> gpuAdapter = new ArrayAdapter<GPU>(this, R.layout.support_simple_spinner_dropdown_item, gpuList);
        cpuAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<Drive> driveAdapter = new ArrayAdapter<Drive>(this, R.layout.support_simple_spinner_dropdown_item, driveList);
        cpuAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<RAM> ramAdapter = new ArrayAdapter<RAM>(this, R.layout.support_simple_spinner_dropdown_item, ramList);
        cpuAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spCPU.setAdapter(cpuAdapter);
        spGPU.setAdapter(gpuAdapter);
        spDrive.setAdapter(driveAdapter);
        spRAM.setAdapter(ramAdapter);

        Intent i = getIntent();
        long id = i.getLongExtra(EXTRA_COMPUTER_ID, 0);


    }
}
