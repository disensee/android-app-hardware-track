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

import com.example.hardwaretrack.models.CPU;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CPUListActivity extends AppCompatActivity {

    public static final String TAG = "CPUListActivity";
    private ListView lsCPUs;
    private SQLComputerDataAccess da;
    private ArrayList<CPU> allCPUs;
    private Button btnAddCPU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpulist);

        lsCPUs = findViewById(R.id.lsCPUs);
        da = new SQLComputerDataAccess(this);
        allCPUs = da.getAllCPUs();
        btnAddCPU = findViewById(R.id.btnAddCPU);

        btnAddCPU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CPUListActivity.this, CPUDetailsActivity.class);
                startActivity(i);
            }
        });

        if(allCPUs.size() == 0){
            Intent i = new Intent(CPUListActivity.this, CPUDetailsActivity.class);
            startActivity(i);
        }

        ArrayAdapter<CPU> adapter = new ArrayAdapter(this, R.layout.custom_cpu_list_item, R.id.lblCPUManufacturer, allCPUs){
            @Override
            public View getView(int position, View convertView, ViewGroup parentListView){
                final View listItemView = super.getView(position, convertView, parentListView);
                TextView lblCPUManufacturer = listItemView.findViewById(R.id.lblCPUManufacturer);
                TextView lblCPUModel = listItemView.findViewById(R.id.lblCPUModel);

                CPU currentCPU = allCPUs.get(position);
                lblCPUManufacturer.setText("Manufacturer: " + currentCPU.getManufacturer());
                lblCPUModel.setText("Model: " + currentCPU.getModel().toUpperCase());

                listItemView.setTag(currentCPU);
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CPU cpu = (CPU)listItemView.getTag();
                        Intent i = new Intent(CPUListActivity.this, CPUDetailsActivity.class);
                        i.putExtra(CPUDetailsActivity.EXTRA_CPU_ID, cpu.getId());
                        startActivity(i);
                    }
                });
                return listItemView;
            }
        };
        lsCPUs.setAdapter(adapter);
    }
}
