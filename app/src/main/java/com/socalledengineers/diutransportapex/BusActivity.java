package com.socalledengineers.diutransportapex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.model.Bus;
import com.socalledengineers.diutransportapex.model.BusItem;
import com.socalledengineers.diutransportapex.utils.Display;
import com.socalledengineers.diutransportapex.utils.NodeName;
import com.socalledengineers.diutransportapex.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BusActivity extends AppCompatActivity {

    private DatabaseReference reference;

    private ProgressDialog dialog;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus2);

        init();
        findViewById(R.id.addBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                LatLng diuLocation = Utils.versityLatLng;

                bus1.setLat(diuLocation.latitude);
                bus1.setLat(diuLocation.longitude);


                String starting_time = "7.15 AM";
                bus1.setStarting_time(starting_time);
                String driver_uid = "DiNQKw6QSgT5EXrglEmjt60vQNr1";
                bus1.setDriver_uid(driver_uid);

                String from = "Dhanmondi";
                bus1.setFrom(from);
                String to = "DSC";
                bus1.setTo(to);
                String routes_name = "Dhanmondi <> DSC";
                bus1.setRoutes_name(routes_name);

                String routes_description = "Dhanmondi - Sobhanbag <> Shyamoli Square <> Technical Mor > Majar Road Gabtoli <> Konabari Bus Stop <> Eastern Housing Rup Nogor <> Birulia Bus Stand <> Daffodil Smart City";
                bus1.setRoutes_description(routes_description);

                bus1.setLat(Utils.versityLatLng.latitude);
                bus1.setLon(Utils.versityLatLng.longitude);
                bus1.setName("Demo Name");

                String docId = reference.child(NodeName.BUS_TRIP_NODE).push().getKey();
                bus1.setDoc_id(docId);

                bus1.setRoutes_url("https://www.google.com/maps/d/embed?mid=1J8QtXb3iMgXJTsECsIzdzu3mIgDio5Al&ll=23.815653053593255%2C90.34311500000001&z=12");
                addBus(bus1);
            }
        });


    }


    private void addBus(BusItem bus){
        dialog.setMessage("adding bus..");
        Display.infoToast(BusActivity.this,bus.getDoc_id());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        reference.child(NodeName.BUS_NODE).child(bus.getDoc_id()).setValue(bus).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    dialog.dismiss();
                    Display.successToast(BusActivity.this,"Bus Added Successfully");
                    Intent intent = new Intent(BusActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else {
                    dialog.dismiss();
                    Display.errorToast(BusActivity.this,task.getException().getMessage());
                }
            }
        });

    }
    private void  simpleInsert(){
        HashMap<String,Object> user_map = new HashMap<>();
        user_map.put("Name","Abdullah");
        reference.child("Chils").push().setValue(user_map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Display.successToast(BusActivity.this,"Success");
                }else {
                    Display.successToast(BusActivity.this,task.getException().getMessage());
                }
            }
        });

    }

    private void init(){
        dialog = new ProgressDialog(BusActivity.this);
        reference = FirebaseDatabase.getInstance().getReference();
        firestore = FirebaseFirestore.getInstance();
    }
}