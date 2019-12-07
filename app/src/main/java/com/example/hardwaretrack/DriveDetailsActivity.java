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
import android.widget.SpinnerAdapter;
import android.widget.Spinner;

import com.example.hardwaretrack.models.Drive;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;


public class DriveDetailsActivity extends AppCompatActivity {

    public static final String TAG = "DriveDetailsActivity";
    public static final String EXTRA_DRIVE_ID = "driveId";

    private SQLComputerDataAccess da;
    private Drive drive;

    private EditText txtManufacturer;
    private EditText txtModel;
    private Spinner spDriveType;
    private Spinner spFormFactor;
    private Spinner spTransferProtocol;
    private EditText txtCapacity;
    private EditText txtMaxTransferRate;
    private Button btnSave;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_details);

        da = new SQLComputerDataAccess(this);
        txtManufacturer = findViewById(R.id.txtDriveManufacturer);
        txtModel = findViewById(R.id.txtDriveModel);
        spDriveType = findViewById(R.id.spDriveType);
        spFormFactor = findViewById(R.id.spDriveFormFactor);
        spTransferProtocol = findViewById(R.id.spDriveTransferProtocol);
        txtCapacity = findViewById(R.id.txtDriveCapacity);
        txtMaxTransferRate = findViewById(R.id.txtDriveMaxTransferRate);
        btnSave = findViewById(R.id.btnDriveSave);
        btnDelete = findViewById(R.id.btnDriveDelete);

        Intent i = getIntent();
        long id = i.getLongExtra(EXTRA_DRIVE_ID, 0);

        if(id > 0){
            Log.d(TAG, "Display drive with id of " + id + " in the user interface");
            drive = da.getDriveById(id);
            displayDrive(drive);
        }else{
            Log.d(TAG, "Adding new drive");
            drive = null;
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(drive != null && drive.getId() > 0){
                    if(validateDriveData()){
                        da.updateDrive(setDriveData(drive));

                        Intent driveListView = new Intent(DriveDetailsActivity.this, DriveListActivity.class);
                        startActivity(driveListView);
                    }
                }else if(drive == null){
                    if(validateDriveData()){
                        Drive driveToAdd = new Drive(0, null, null, "SSD", "M.2", "NVMe", 0, 0 );
                        da.insertDrive(setDriveData(driveToAdd));

                        Intent driveListView = new Intent(DriveDetailsActivity.this, DriveListActivity.class);
                        startActivity(driveListView);
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDrive(drive);
            }
        });
    }

    private void displayDrive(Drive d){
        txtManufacturer.setText(drive.getManufacturer());
        txtModel.setText(drive.getModel());
        CustomApp.selectSpinnerItemByValue(spDriveType, drive.getType());
        CustomApp.selectSpinnerItemByValue(spFormFactor, drive.getFormFactor());
        CustomApp.selectSpinnerItemByValue(spTransferProtocol, drive.getTransferProtocol());
        txtCapacity.setText(String.valueOf(drive.getCapacity()));
        txtMaxTransferRate.setText(String.valueOf(drive.getMaxTransferRate()));
    }

    private Drive setDriveData(Drive d){
        d.setManufacturer(txtManufacturer.getText().toString());
        d.setModel(txtModel.getText().toString());
        d.setType(spDriveType.getSelectedItem().toString());
        d.setFormFactor(spFormFactor.getSelectedItem().toString());
        d.setTransferProtocol(spTransferProtocol.getSelectedItem().toString());
        try{
            d.setCapacity(Long.parseLong(txtCapacity.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }
        try{
            d.setMaxTransferRate(Long.parseLong(txtMaxTransferRate.getText().toString()));
        }catch(NumberFormatException e){
            Log.d(TAG, "Number format exception: " + e);
        }

        return d;
    }

    private boolean validateDriveData(){
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
            txtCapacity.setError(getText(R.string.error_capacity));
        }

        if(txtCapacity.getText().toString().length() != 0){
            try{
                Long.parseLong(txtCapacity.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number format exception: " + e);
                txtCapacity.setError(getText(R.string.error_value_capacity));
                valid = false;
            }
        }

        if(txtMaxTransferRate.getText().toString().length() == 0){
            valid = false;
            txtMaxTransferRate.setError(getText(R.string.error_max_transfer_rate));
        }

        if(txtMaxTransferRate.getText().toString().length() != 0){
            try{
                Long.parseLong(txtMaxTransferRate.getText().toString());
            }catch (NumberFormatException e){
                Log.d(TAG, "Number format exception: " + e);
                txtCapacity.setError(getText(R.string.error_value_max_transfer_rate));
                valid = false;
            }
        }

        return valid;
    }

    private void deleteDrive(final Drive d){
        AlertDialog.Builder builder = new AlertDialog.Builder(DriveDetailsActivity.this);
        builder.setMessage(R.string.delete_drive_confirm);
        builder.setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                da.deleteDrive(d);

                Intent driveListView = new Intent(DriveDetailsActivity.this, DriveListActivity.class);
                startActivity(driveListView);
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
