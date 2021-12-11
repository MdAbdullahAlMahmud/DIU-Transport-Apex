package com.socalledengineers.diutransportadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EventmanagementActivity extends AppCompatActivity {

    EditText eventName,busNumber,destination,time;
    Button addEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventmanagement);

        init();
    }

    private void init(){


        eventName = findViewById(R.id.eventName);
        busNumber = findViewById(R.id.busNumber);
        destination = findViewById(R.id.destination);
        time = findViewById(R.id.time);

        addEventBtn = findViewById(R.id.addEventrButton);



    }
}