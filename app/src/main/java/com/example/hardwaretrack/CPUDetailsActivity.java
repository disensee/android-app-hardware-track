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
import com.example.hardwaretrack.models.CPU;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;



public class CPUDetailsActivity extends AppCompatActivity {

    public static final String TAG = "CPUDetailsActivity";
    public static final String EXTRA_CPU_ID = "cpuId";
    SQLComputerDataAccess da;
    CPU cpu;
    EditText txtManufacturer;
    EditText txtModel;
    EditText txtCoreCount;
    EditText txtThreadCount;
    EditText txtBaseClock;
    EditText txtBoostClock;
    Button btnSave;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpudetails);

        da = new SQLComputerDataAccess(this);

        txtManufacturer = findViewById(R.id.txtCPUManufacturer);
        txtModel = findViewById(R.id.txtCPUModel);
        txtCoreCount = findViewById(R.id.txtCPUCoreCount);
        txtThreadCount = findViewById(R.id.txtCPUThreadCount);
        txtBaseClock = findViewById(R.id.txtCPUBaseClock);
        txtBoostClock = findViewById(R.id.txtCPUBoostClock);
        btnSave = findViewById(R.id.btnCPUSave);
        btnDelete = findViewById(R.id.btnCPUDelete);

        Intent i = getIntent();
        long id = i.getLongExtra(EXTRA_CPU_ID, 0);

        if(id > 0){
            Log.d(TAG, "Display CPU with the ID of " + id + "in the user interface");
            cpu = da.getCPUById(id);
            displayCPU(cpu);
        }else{
            Log.d(TAG, "Adding new CPU");
            cpu = null;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cpu != null && cpu.getId() > 0){
                    if(validateCPUData()){
                        da.updateCPU(setCPUData(cpu));

                        Intent cpuListView = new Intent(CPUDetailsActivity.this, CPUListActivity.class);
                        startActivity(cpuListView);
                    }
                }else if(cpu == null){
                    if(validateCPUData()){
                        CPU cpuToAdd = new CPU(0, null, null, 0, 0, 0, 0);
                        da.insertCPU(setCPUData(cpuToAdd));

                        Intent cpuListView = new Intent(CPUDetailsActivity.this, CPUListActivity.class);
                        startActivity(cpuListView);
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCPU(cpu);
            }
        });
    }


    private void displayCPU(CPU cpu){
        txtManufacturer.setText(cpu.getManufacturer());
        txtModel.setText(cpu.getModel());
        txtCoreCount.setText(String.valueOf(cpu.getCoreCount()));
        txtThreadCount.setText(String.valueOf(cpu.getThreadCount()));
        txtBaseClock.setText(String.valueOf(cpu.getBaseClock()));
        txtBoostClock.setText(String.valueOf(cpu.getBoostClock()));
    }



    private CPU setCPUData(CPU cpu){
        cpu.setManufacturer(txtManufacturer.getText().toString());
        cpu.setModel(txtModel.getText().toString());
        try{
            cpu.setCoreCount(Integer.parseInt(txtCoreCount.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        try{
            cpu.setThreadCount(Integer.parseInt(txtThreadCount.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        try{
            cpu.setBaseClock(Float.parseFloat(txtBaseClock.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        try{
            cpu.setBoostClock(Float.parseFloat(txtBoostClock.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }

        return cpu;
    }


    private boolean validateCPUData(){
        boolean valid = true;
        if(txtManufacturer.getText().toString().length() == 0){
            valid = false;
            txtManufacturer.setError(getText(R.string.error_manufacturer));
        }

        if(txtModel.getText().toString().length() == 0){
            valid = false;
            txtModel.setError(getText(R.string.error_model));
        }

        if(txtCoreCount.getText().toString().length() == 0){
            valid = false;
            txtCoreCount.setError(getText(R.string.error_core_count));
        }

        if(txtCoreCount.getText().toString().length() != 0){
            try{
                Integer.parseInt(txtCoreCount.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number Format Exception: " + e);
                txtCoreCount.setError(getText(R.string.error_value_core_count));
                valid = false;
            }
        }

        if(txtThreadCount.getText().toString().length() == 0){
            valid = false;
            txtThreadCount.setError(getText(R.string.error_thread_count));
        }

        if(txtThreadCount.getText().toString().length() != 0){
            try{
                Integer.parseInt(txtThreadCount.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number Format Exception: " + e);
                txtThreadCount.setError(getText(R.string.error_value_thread_count));
                valid = false;
            }
        }

        if(txtBaseClock.getText().toString().length() == 0){
            valid = false;
            txtBaseClock.setError(getText(R.string.error_base_clock));
        }

        if(txtBaseClock.getText().toString().length() != 0){
            try{
                Float.parseFloat(txtBaseClock.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number Format Exception: " + e);
                txtBaseClock.setError(getText(R.string.error_value_base_clock));
                valid = false;
            }
        }

        if(txtBoostClock.getText().toString().length() == 0){
            txtBoostClock.setError(getText(R.string.error_boost_clock));
        }

        if(txtBoostClock.getText().toString().length() != 0){
            try{
                Float.parseFloat(txtBoostClock.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number Format Exception: " + e);
                txtBoostClock.setError(getText(R.string.error_value_boost_clock));
                valid = false;
            }
        }

        return valid;
    }


    public void deleteCPU(final CPU cpu){
        AlertDialog.Builder builder = new AlertDialog.Builder(CPUDetailsActivity.this);
        builder.setMessage(R.string.delete_cpu_confirm);
        builder.setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                da.deleteCPU(cpu);

                Intent cpuListView = new Intent(CPUDetailsActivity.this, CPUListActivity.class);
                startActivity(cpuListView);
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
