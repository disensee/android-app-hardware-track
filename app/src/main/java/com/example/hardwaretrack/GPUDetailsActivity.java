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

import com.example.hardwaretrack.models.GPU;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

public class GPUDetailsActivity extends AppCompatActivity {

    public static final String TAG = "GPUDetailsActivity";
    public static final String EXTRA_GPU_ID = "gpuId";

    private SQLComputerDataAccess da;
    private GPU gpu;

    private EditText txtManufacturer;
    private EditText txtModel;
    private EditText txtCoreCount;
    private EditText txtBaseClock;
    private EditText txtBoostClock;
    private EditText txtVRam;
    private Button btnSave;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpudetails);

        da = new SQLComputerDataAccess(this);
        txtManufacturer = findViewById(R.id.txtGPUManufacturer);
        txtModel = findViewById(R.id.txtGPUModel);
        txtCoreCount = findViewById(R.id.txtGPUCoreCount);
        txtBaseClock = findViewById(R.id.txtGPUBaseClock);
        txtBoostClock = findViewById(R.id.txtGPUBoostClock);
        txtVRam = findViewById(R.id.txtGPUVRam);
        btnSave = findViewById(R.id.btnGPUSave);
        btnDelete = findViewById(R.id.btnGPUDelete);

        Intent i = getIntent();
        long id = i.getLongExtra(EXTRA_GPU_ID, 0);

        if(id > 0){
            Log.d(TAG, "Display CPU with the ID of " + id + " in the user interface");
            gpu = da.getGPUById(id);
            displayGPU(gpu);
        }else{
            Log.d(TAG, "Adding a new GPU");
            gpu = null;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gpu != null && gpu.getId() > 0){
                    if(validateGPUData()){
                        da.updateGPU(setGPUData(gpu));

                        Intent gpuListView = new Intent(GPUDetailsActivity.this, GPUListActivity.class);
                        startActivity(gpuListView);
                    }
                }else if(gpu == null){
                    if(validateGPUData()){
                        GPU gpuToAdd = new GPU(0, null, null, 0, 0, 0, null);
                        da.insertGPU(setGPUData(gpuToAdd));

                        Intent gpuListView = new Intent(GPUDetailsActivity.this, GPUListActivity.class);
                        startActivity(gpuListView);
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGPU(gpu);
            }
        });
    }

    private void displayGPU(GPU gpu){
        txtManufacturer.setText(gpu.getManufacturer());
        txtModel.setText(gpu.getModel());
        txtCoreCount.setText(String.valueOf(gpu.getCoreCount()));
        txtBaseClock.setText(String.valueOf(gpu.getBaseClock()));
        txtBoostClock.setText(String.valueOf(gpu.getBoostClock()));
        txtVRam.setText(gpu.getvRam());
    }

    private GPU setGPUData(GPU gpu){
        gpu.setManufacturer(txtManufacturer.getText().toString());
        gpu.setModel(txtModel.getText().toString());
        try{
            gpu.setCoreCount(Integer.parseInt(txtCoreCount.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        try{
            gpu.setBaseClock(Long.parseLong(txtBaseClock.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        try{
            gpu.setBoostClock(Long.parseLong(txtBoostClock.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        gpu.setvRam(txtVRam.getText().toString());

        return gpu;
    }

    private boolean validateGPUData(){
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
                Long.parseLong(txtCoreCount.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number Format Exception: " + e);
                txtCoreCount.setError(getText(R.string.error_value_core_count));
                valid = false;
            }
        }

        if(txtBaseClock.getText().toString().length() == 0){
            valid = false;
            txtBaseClock.setError(getText(R.string.error_base_clock));
        }

        if(txtBaseClock.getText().toString().length() != 0){
            try{
                Long.parseLong(txtBaseClock.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number Format Exception: " + e);
                txtBaseClock.setError(getText(R.string.error_value_base_clock));
                valid = false;
            }
        }

        if(txtBoostClock.getText().toString().length() == 0){
            valid = false;
            txtBoostClock.setError(getText(R.string.error_boost_clock));
        }

        if(txtBoostClock.getText().toString().length() != 0){
            try{
                Long.parseLong(txtBoostClock.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number Format Exception: " + e);
                txtBoostClock.setError(getText(R.string.error_value_boost_clock));
                valid = false;
            }
        }

        if(txtVRam.getText().toString().length() == 0){
            valid = false;
            txtVRam.setError(getText(R.string.error_vram));
        }

        return valid;
    }

    private void deleteGPU(final GPU gpu){
        AlertDialog.Builder builder = new AlertDialog.Builder(GPUDetailsActivity.this);
        builder.setMessage(R.string.delete_gpu_confirm);
        builder.setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                da.deleteGPU(gpu);

                Intent gpuListView = new Intent(GPUDetailsActivity.this, GPUListActivity.class);
                startActivity(gpuListView);
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
