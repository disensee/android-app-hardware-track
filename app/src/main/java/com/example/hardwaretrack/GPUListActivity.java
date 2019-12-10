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

import com.example.hardwaretrack.models.GPU;
import com.example.hardwaretrack.sqlite.SQLComputerDataAccess;

import java.util.ArrayList;

public class GPUListActivity extends AppCompatActivity {

    public static final String TAG = "GPUListActivity";
    private ListView lsGPUs;
    private SQLComputerDataAccess da;
    private ArrayList<GPU> allGPUs;
    private Button btnAddGPU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpulist);

        lsGPUs = findViewById(R.id.lsGPUs);
        da = new SQLComputerDataAccess(this);
        allGPUs = da.getAllGPUs();
        btnAddGPU = findViewById(R.id.btnAddGPU);

        btnAddGPU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GPUListActivity.this, GPUDetailsActivity.class);
                startActivity(i);
            }
        });

        if(allGPUs.size() == 0){
            Intent i = new Intent(GPUListActivity.this, GPUDetailsActivity.class);
            startActivity(i);
        }

        ArrayAdapter<GPU> adapter = new ArrayAdapter(this, R.layout.custom_gpu_list_item, R.id.lblGPUManufacturer, allGPUs){
            @Override
            public View getView(int position, View convertView, ViewGroup parentListView){
                final View listItemView = super.getView(position, convertView, parentListView);
                TextView lblGPUManufacturer = listItemView.findViewById(R.id.lblGPUManufacturer);
                TextView lblGPUModel = listItemView.findViewById(R.id.lblGPUModel);

                GPU currentGPU = allGPUs.get(position);
                lblGPUManufacturer.setText(getString(R.string.manufacturer) + ": " + currentGPU.getManufacturer());
                lblGPUModel.setText(getString(R.string.model) + ": " + currentGPU.getModel());

                listItemView.setTag(currentGPU);
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GPU gpu = (GPU)listItemView.getTag();
                        Intent i = new Intent(GPUListActivity.this, GPUDetailsActivity.class);
                        i.putExtra(GPUDetailsActivity.EXTRA_GPU_ID, gpu.getId());
                        startActivity(i);
                    }
                });
                return listItemView;
            }
        };
        lsGPUs.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        Intent back = new Intent(GPUListActivity.this, MainActivity.class);
        startActivity(back);
    }
}