package com.example.hardwaretrack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button btnSave;
    private Button btnDelete;

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
        btnSave = findViewById(R.id.btnComputerSave);
        btnDelete = findViewById(R.id.btnComputerDelete);

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

        if(id > 0){
            Log.d(TAG, "Display computer with id of " + id + " in the user interface");
            pc = da.getComputerById(id);
            displayComputer(pc);
        }else{
            Log.d(TAG, "Adding new computer");
            pc = null;
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pc != null && pc.getId() >0){
                    if(validateComputerData()){
                        da.updateComputer(setComputerData(pc));

                        Intent computerListView = new Intent(ComputerDetailsActivity.this, ComputerListActivity.class);
                        startActivity(computerListView);
                    }
                }else if(pc == null){
                    if(validateComputerData()){
                        Computer pcToAdd = new Computer(0, "Laptop", null, null, false, null, null, null, null );
                        da.insertComputer(setComputerData(pcToAdd));

                        Intent computerListView = new Intent(ComputerDetailsActivity.this, ComputerListActivity.class);
                        startActivity(computerListView);
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteComputer(pc);
            }
        });
    }

    private void displayComputer(Computer pc){
        CustomApp.selectSpinnerItemByValue(spType, pc.getType());
        txtManufacturer.setText(pc.getManufacturer());
        txtModel.setText(pc.getModel());
        chkCustomBuild.setChecked(pc.isCustomBuild());
        CustomApp.selectSpinnerItemByValue(spCPU, pc.getProcessor().toString());
        CustomApp.selectSpinnerItemByValue(spGPU, pc.getGraphicsProcessor().toString());
        CustomApp.selectSpinnerItemByValue(spDrive, pc.getDrive().toString());
        CustomApp.selectSpinnerItemByValue(spRAM, pc.getRam().toString());
    }

    private Computer setComputerData(Computer pc){
        pc.setType(spType.getSelectedItem().toString());
        pc.setManufacturer(txtManufacturer.getText().toString());
        pc.setModel(txtModel.getText().toString());
        if(chkCustomBuild.isChecked()){
            pc.setCustomBuild(true);
        }else if(!chkCustomBuild.isChecked()){
            pc.setCustomBuild(false);
        }
        pc.setProcessor((CPU)spCPU.getSelectedItem());
        pc.setGraphicsProcessor((GPU)spGPU.getSelectedItem());
        pc.setDrive((Drive)spDrive.getSelectedItem());
        pc.setRam((RAM)spRAM.getSelectedItem());

        return pc;
    }

    public boolean validateComputerData(){
        boolean valid = true;

        if(txtManufacturer.getText().toString().length() == 0){
            valid = false;
            txtManufacturer.setError(getText(R.string.error_manufacturer));
        }

        if(txtModel.getText().toString().length() == 0){
            valid = false;
            txtModel.setError(getText(R.string.error_model));
        }

        return valid;
    }

    private void deleteComputer(final Computer pc){
        AlertDialog.Builder builder = new AlertDialog.Builder(ComputerDetailsActivity.this);
        builder.setMessage(R.string.delete_computer_confirm);
        builder.setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                da.deleteComputer(pc);

                Intent computerListView = new Intent(ComputerDetailsActivity.this, ComputerListActivity.class);
                startActivity(computerListView);
            }
        });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }


}
