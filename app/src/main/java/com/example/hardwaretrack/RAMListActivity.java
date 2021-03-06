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

import com.example.hardwaretrack.models.RAM;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

import java.util.ArrayList;

public class RAMListActivity extends AppCompatActivity {

    public static final String TAG = "RAMListActivity";
    private ListView lsRAM;
    private SQLComputerDataAccess da;
    private ArrayList<RAM> allRAM;
    private Button btnAddRAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramlist);

        lsRAM = findViewById(R.id.lsRAM);
        da = new SQLComputerDataAccess(this);
        allRAM = da.getAllRAM();
        btnAddRAM = findViewById(R.id.btnAddRAM);

        btnAddRAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RAMListActivity.this, RAMDetailsActivity.class);
                startActivity(i);
            }
        });

        if(allRAM.size() == 0){
            Intent i = new Intent(RAMListActivity.this, RAMDetailsActivity.class);
            startActivity(i);
        }

        ArrayAdapter<RAM> adapter = new ArrayAdapter(this, R.layout.custom_ram_list_item, R.id.lblRAMManufacturer, allRAM){
            @Override
            public View getView(int position, View convertView, ViewGroup parentListView){
                final View listItemView = super.getView(position, convertView, parentListView);
                TextView lblRAMManufacturer = listItemView.findViewById(R.id.lblRAMManufacturer);
                TextView lblRAMModel = listItemView.findViewById(R.id.lblRAMModel);

                RAM currentRAM = allRAM.get(position);
                lblRAMManufacturer.setText(getString(R.string.manufacturer) + ": " +currentRAM.getManufacturer());
                lblRAMModel.setText(getString(R.string.model) + ": " + currentRAM.getModel() + " " + currentRAM.getSpeed() + " " + getResources().getString(R.string.mhz));

                listItemView.setTag(currentRAM);
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RAM ram = (RAM) listItemView.getTag();
                        Intent i = new Intent(RAMListActivity.this, RAMDetailsActivity.class);
                        i.putExtra(RAMDetailsActivity.EXTRA_RAM_ID, ram.getId());
                        startActivity(i);
                    }
                });
                return listItemView;
            }
        };
        lsRAM.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        Intent back = new Intent(RAMListActivity.this, MainActivity.class);
        startActivity(back);
    }
}
