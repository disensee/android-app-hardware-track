package com.example.hardwaretrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hardwaretrack.models.Drive;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

import java.util.ArrayList;

public class DriveListActivity extends AppCompatActivity {

    public static final String TAG = "DriveListActivity";
    private ListView lsDrives;
    private SQLComputerDataAccess da;
    private ArrayList<Drive> allDrives;
    private Button btnAddDrive;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_list);

        lsDrives = findViewById(R.id.lsDrives);
        da = new SQLComputerDataAccess(this);
        allDrives = da.getAllDrives();
        btnAddDrive = findViewById(R.id.btnAddDrive);
        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DriveListActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnAddDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DriveListActivity.this, DriveDetailsActivity.class);
                startActivity(i);
            }
        });

        if(allDrives.size() == 0){
            Intent i = new Intent(DriveListActivity.this, DriveDetailsActivity.class);
            startActivity(i);
        }

        ArrayAdapter<Drive> adapter = new ArrayAdapter(this, R.layout.custom_drive_list_item, R.id.lblDriveManufacturer, allDrives){
            @Override
            public View getView(int position, View convertView, ViewGroup parentListView){
                final View listItemView = super.getView(position, convertView, parentListView);
                TextView lblDriveType = listItemView.findViewById(R.id.lblDriveType);
                TextView lblDriveManufacturer = listItemView.findViewById(R.id.lblDriveManufacturer);
                TextView lblDriveModel = listItemView.findViewById(R.id.lblDriveModel);

                Drive currentDrive = allDrives.get(position);
                lblDriveType.setText(getString(R.string.type) + ": " + currentDrive.getType().toUpperCase());
                lblDriveManufacturer.setText(getString(R.string.manufacturer) + ": " +currentDrive.getManufacturer());
                lblDriveModel.setText(getString(R.string.model) + ": " + currentDrive.getModel());

                listItemView.setTag(currentDrive);
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drive d = (Drive) listItemView.getTag();
                        Intent i = new Intent(DriveListActivity.this, DriveDetailsActivity.class);
                        i.putExtra(DriveDetailsActivity.EXTRA_DRIVE_ID, d.getId());
                        startActivity(i);
                    }
                });
                return listItemView;
            }
        };
        lsDrives.setAdapter(adapter);
    }
}
