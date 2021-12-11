package com.socalledengineers.diutransportadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddBusActivity extends AppCompatActivity {


    EditText busnameEdt,busidEdt,drivernameEdt,startingpointEdt,destinationEdt,tripnumberEdt,triptimesEdt,seatnumberEdt;
    Button addBusBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        init();


    }


    private void init(){


        busnameEdt = findViewById(R.id.busName);
        busidEdt = findViewById(R.id.busId);
        drivernameEdt = findViewById(R.id.driverName);
        startingpointEdt = findViewById(R.id.fromEdt);
        destinationEdt = findViewById(R.id.toEdt);
        tripnumberEdt = findViewById(R.id.tripEdt);
        triptimesEdt = findViewById(R.id.timeEdt);
        seatnumberEdt = findViewById(R.id.seatEdt);
        
        addBusBtn = findViewById(R.id.addDriverButton);///



    }
}