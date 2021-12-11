package com.socalledengineers.diutransportadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddDriver extends AppCompatActivity {

    
    EditText driverNameEdt,driverIdEdt,driverAgeEdt,driverPassEdt;
    Button addDriverBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        
        init();
    }

    private void init(){


        driverNameEdt = findViewById(R.id.driverName);
        driverIdEdt = findViewById(R.id.driverId);
        driverAgeEdt = findViewById(R.id.driverAge);
        driverPassEdt = findViewById(R.id.driverPass);
       
        addDriverBtn  = findViewById(R.id.addDriverButton);


    }
}