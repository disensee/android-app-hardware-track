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

import com.example.hardwaretrack.models.Computer;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

import java.util.ArrayList;

public class ComputerListActivity extends AppCompatActivity {

    public static final String TAG = "ComputerListActivity";
    private ListView lsComputers;
    private SQLComputerDataAccess da;
    private ArrayList<Computer> allComputers;
    private Button btnAddComputer;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_list);
        //initialize instance variables
        lsComputers = findViewById(R.id.lsComputers);
        da = new SQLComputerDataAccess(this);
        allComputers = da.getAllComputers();
        btnAddComputer = findViewById(R.id.btnAddComputer);
        btnHome = findViewById(R.id.btnHome);
        //Add home button functionality
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComputerListActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        //Add computer button functionality
        btnAddComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComputerListActivity.this, ComputerDetailsActivity.class);
                startActivity(i);
            }
        });
        //Go to computer details if no computers are in DB
        if (allComputers.size() == 0){
            Intent i = new Intent(ComputerListActivity.this, ComputerDetailsActivity.class);
            startActivity(i);
        }

        ArrayAdapter<Computer> adapter = new ArrayAdapter(this, R.layout.custom_computer_list_item, R.id.lblComputerType, allComputers){
            @Override
            public View getView(int position, View convertView, ViewGroup parentListView){
                final View listItemView = super.getView(position, convertView, parentListView);
                TextView lblComputerType = listItemView.findViewById(R.id.lblComputerType);
                TextView lblComputerManufacturer = listItemView.findViewById(R.id.lblComputerManufacturer);
                TextView lblComputerModel = listItemView.findViewById(R.id.lblComputerModel);

                Computer currentPC = allComputers.get(position);
                lblComputerType.setText(currentPC.getType());
                lblComputerManufacturer.setText(currentPC.getManufacturer());
                lblComputerModel.setText(currentPC.getModel());

                listItemView.setTag(currentPC);
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Computer pc = (Computer)listItemView.getTag();
                        Intent i = new Intent(ComputerListActivity.this, ComputerDetailsActivity.class);
                        i.putExtra(ComputerDetailsActivity.EXTRA_COMPUTER_ID, pc.getId());
                        startActivity(i);
                    }
                });
                return listItemView;
            }
        };
        lsComputers.setAdapter(adapter);
    }
}
