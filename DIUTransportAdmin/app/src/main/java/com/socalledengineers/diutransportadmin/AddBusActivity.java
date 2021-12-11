package com.socalledengineers.diutransportadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.socalledengineers.diutransportadmin.model.BusItem;
import com.socalledengineers.diutransportadmin.utils.Display;
import com.socalledengineers.diutransportadmin.utils.NodeName;
import com.socalledengineers.diutransportadmin.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddBusActivity extends AppCompatActivity {


    EditText busnameEdt,busidEdt,drivernameEdt,startingpointEdt,destinationEdt,tripnumberEdt,triptimesEdt,seatnumberEdt,routeUrlEdt,fromEdt;
    Button addBusBtn;
    MaterialButton addBussButton;
    private DatabaseReference reference;

    private ProgressDialog dialog;
    String busName,busId, from_bus, to_bus,driver_uid,routeURL,startingTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);
        reference = FirebaseDatabase.getInstance().getReference();
        init();



        addBussButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            busName = busidEdt.getText().toString();
            busId= busidEdt.getText().toString();
            driver_uid = drivernameEdt.getText().toString();
            routeURL = routeUrlEdt.getText().toString();
            from_bus = startingpointEdt.getText().toString();
            to_bus = destinationEdt.getText().toString();
            startingTime = triptimesEdt.getText().toString();

            if (TextUtils.isEmpty(busName)){
                busnameEdt.setError("required");
                return;
            }
            if (TextUtils.isEmpty(busId)){
            busidEdt.setError("required");
                return;
            }
        if (TextUtils.isEmpty(driver_uid)){
            drivernameEdt.setError("required");
                return;
            }

        if (TextUtils.isEmpty(routeURL)){
            routeUrlEdt.setError("required");
                return;
            }
         if (TextUtils.isEmpty(from_bus)){
            startingpointEdt.setError("required");
                return;
            }

        if (TextUtils.isEmpty(to_bus)){
            destinationEdt.setError("required");
                return;
            }
         if (TextUtils.isEmpty(startingTime)){
             triptimesEdt.setError("required");
                return;
            }

         getBusData();




        }
    });
    }
    public void getBusData(){
        BusItem bus1 = new BusItem();
        List<String> seatList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            seatList.add("0");
        }
        bus1.setSeats(seatList);

        long created_at = new Date().getTime();
        bus1.setCreated_at(created_at);

        String creator_uid = "oVP0eU1Gu3a9joYf4U09Zmc8nM12";
        bus1.setCreated_uid(creator_uid);

        LatLng diuLocation = com.socalledengineers.diutransportadmin.utils.Utils.versityLatLng;

        bus1.setLat(diuLocation.latitude);
        bus1.setLat(diuLocation.longitude);


        String starting_time = startingTime;
        bus1.setStarting_time(starting_time);
        String driver_uid = "DiNQKw6QSgT5EXrglEmjt60vQNr1";
        bus1.setDriver_uid(driver_uid);

        String from = from_bus;
        bus1.setFrom(from);
        String to = to_bus;
        bus1.setTo(to);
        String routes_name = " ";
        bus1.setRoutes_name(routes_name);

        String routes_description ="";
        bus1.setRoutes_description(routes_description);

        bus1.setLat(Utils.versityLatLng.latitude);
        bus1.setLon(Utils.versityLatLng.longitude);
        bus1.setName(busName);

        String docId = reference.child(NodeName.BUS_TRIP_NODE).push().getKey();
        bus1.setDoc_id(docId);

        bus1.setRoutes_url("https://www.google.com/maps/d/embed?mid=1J8QtXb3iMgXJTsECsIzdzu3mIgDio5Al&ll=23.815653053593255%2C90.34311500000001&z=12");
        addBus(bus1);

    }


    private void addBus(BusItem bus){
        dialog.setMessage("adding bus..");
        com.socalledengineers.diutransportadmin.utils.Display.infoToast(AddBusActivity.this,bus.getDoc_id());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        reference.child(NodeName.BUS_NODE).child(bus.getDoc_id()).setValue(bus).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    dialog.dismiss();
                    com.socalledengineers.diutransportadmin.utils.Display.successToast(AddBusActivity.this,"Bus Added Successfully");
                    Intent intent = new Intent(AddBusActivity.this,AdminDashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else {
                    dialog.dismiss();
                    Display.errorToast(AddBusActivity.this,task.getException().getMessage());
                }
            }
        });

    }
    private void init(){

        dialog = new ProgressDialog(this);
        addBussButton = findViewById(R.id.addBussButton);
        busnameEdt = findViewById(R.id.busName);
        busidEdt = findViewById(R.id.busId);
        drivernameEdt = findViewById(R.id.driverName);
        startingpointEdt = findViewById(R.id.fromEdt);
        destinationEdt = findViewById(R.id.toEdt);
        routeUrlEdt = findViewById(R.id.routeUrlEdt);
        triptimesEdt = findViewById(R.id.timeEdt);

        addBusBtn = findViewById(R.id.addDriverButton);///



    }
}