package com.socalledengineers.diutransportadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.socalledengineers.diutransportadmin.Driver.DriverBusListActivity;

public class AdminDashboard extends AppCompatActivity {
    private Button adminBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        init();
        adminBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,BusListActivity.class));
            }
        });
        findViewById(R.id.adminDriverAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,AddDriver.class));
            }
        });
        findViewById(R.id.adminDriverControll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, DriverBusListActivity.class));
            }
        });


    }
    private void init(){
        adminBus =findViewById(R.id.adminBus);
    }
}