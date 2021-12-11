package com.socalledengineers.diutransportadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DriverLoginActivity extends AppCompatActivity {


    EditText driverLoginId,driverLoginPass;
    Button driverLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        init();
    }

    private void init(){


        driverLoginId = findViewById(R.id.driverLoginId);
        driverLoginPass = findViewById(R.id.driverLoginPass);

        driverLoginBtn  = findViewById(R.id.driverloginButton);


    }
}