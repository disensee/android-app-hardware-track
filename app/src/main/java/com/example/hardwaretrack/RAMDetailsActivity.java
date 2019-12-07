package com.example.hardwaretrack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hardwaretrack.models.RAM;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

public class RAMDetailsActivity extends AppCompatActivity {

    public static final String TAG = "RAMDetailsActivity";
    public static final String EXTRA_RAM_ID = "ramId";

    private RAM ram;
    private SQLComputerDataAccess da;

    private EditText txtManufacturer;
    private EditText txtModel;
    private Spinner spRAMType;
    private EditText txtCapacity;
    private EditText txtSpeed;
    private Spinner spRAMFormFactor;
    private Button btnSave;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramdetails);

        da = new SQLComputerDataAccess(this);
        txtManufacturer = findViewById(R.id.txtRAMManufacturer);
        txtModel = findViewById(R.id.txtRAMModel);
        spRAMType = findViewById(R.id.spRAMType);
        txtCapacity = findViewById(R.id.txtRAMCapacity);
        txtSpeed = findViewById(R.id.txtRAMSpeed);
        spRAMFormFactor = findViewById(R.id.spRAMFormFactor);
        btnSave = findViewById(R.id.btnRAMSave);
        btnDelete = findViewById(R.id.btnRAMDelete);

        Intent i = getIntent();
        long id = i.getLongExtra(EXTRA_RAM_ID, 0);

        if(id > 0){
            Log.d(TAG, "Display RAM with id of " + id + " in the user interface");
            ram = da.getRamById(id);
            displayRAM(ram);
        }else{
            Log.d(TAG, "Adding new RAM");
            ram = null;
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ram != null && ram.getId() > 0){
                    if(validateRAMData()){
                        da.updateRAM(setRAMData(ram));

                        Intent ramListView = new Intent(RAMDetailsActivity.this, RAMListActivity.class);
                        startActivity(ramListView);
                    }
                }else if(ram == null){
                    if(validateRAMData()){
                        RAM ramToAdd = new RAM(0, null, null, "DDR4", 16, 2666, "DIMM");
                        da.insertRAM(setRAMData(ramToAdd));

                        Intent ramListView = new Intent(RAMDetailsActivity.this, RAMListActivity.class);
                        startActivity(ramListView);
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRAM(ram);
            }
        });
    }

    private void displayRAM(RAM ram){
        txtManufacturer.setText(ram.getManufacturer());
        txtModel.setText(ram.getModel());
        CustomApp.selectSpinnerItemByValue(spRAMType, ram.getType());
        txtCapacity.setText(String.valueOf(ram.getCapacity()));
        txtSpeed.setText(String.valueOf(ram.getSpeed()));
        CustomApp.selectSpinnerItemByValue(spRAMFormFactor, ram.getFormFactor());
    }

    private RAM setRAMData(RAM ram){
        ram.setManufacturer(txtManufacturer.getText().toString());
        ram.setModel(txtModel.getText().toString());
        ram.setType(spRAMType.getSelectedItem().toString());
        try{
            ram.setCapacity(Long.parseLong(txtCapacity.getText().toString()));
        }catch (NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        try{
            ram.setSpeed(Long.parseLong(txtSpeed.getText().toString()));
        }catch (NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        ram.setFormFactor(spRAMFormFactor.getSelectedItem().toString());

        return ram;
    }

    private boolean validateRAMData(){
        boolean valid = true;

        if(txtManufacturer.getText().toString().length() == 0){
            valid = false;
            txtManufacturer.setError(getText(R.string.error_manufacturer));
        }

        if(txtModel.getText().toString().length() == 0){
            valid = false;
            txtModel.setError(getText(R.string.error_model));
        }

        if(txtCapacity.getText().toString().length() == 0){
            valid = false;
            txtCapacity.setError(getText(R.string.error_ram_capacity));
        }

        if(txtCapacity.getText().toString().length() != 0){
            try{
                Long.parseLong(txtCapacity.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number format exception: " + e);
                txtCapacity.setError(getText(R.string.error_value_ram_capacity));
                valid = false;
            }
        }

        if(txtSpeed.getText().toString().length() == 0){
            valid = false;
            txtSpeed.setError(getText(R.string.error_speed));
        }

        if(txtSpeed.getText().toString().length() != 0){
            try{
                Long.parseLong(txtSpeed.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number format exception: " + e);
                txtSpeed.setError(getText(R.string.error_value_speed));
                valid = false;
            }
        }

        return valid;
    }

    private void deleteRAM(final RAM ram){
        AlertDialog.Builder builder = new AlertDialog.Builder(RAMDetailsActivity.this);
        builder.setMessage(R.string.delete_ram_confirm);
        builder.setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                da.deleteRAM(ram);

                Intent ramListView = new Intent(RAMDetailsActivity.this, RAMListActivity.class);
                startActivity(ramListView);
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
