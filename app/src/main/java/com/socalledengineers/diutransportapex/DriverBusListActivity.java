package com.socalledengineers.diutransportapex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.socalledengineers.diutransportapex.model.Bus;
import com.socalledengineers.diutransportapex.model.TripItem;
import com.socalledengineers.diutransportapex.utils.Display;
import com.socalledengineers.diutransportapex.utils.NodeName;
import com.socalledengineers.diutransportapex.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverBusListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private ArrayList<Bus> busArrayList;
    private RoutesAdapter adapter;

    ImageView backToActivity;
    TextView activity_name;
    private ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_list);

        dialog = new ProgressDialog(this);
        init();
        initRecycleView();
        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);

        activity_name.setText(R.string.busTrip);

        adapter = new RoutesAdapter(this,busArrayList);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllBusFromDatabase();
    }

    private void getAllBusFromDatabase(){
        busArrayList.clear();
        firestore.collection(NodeName.BUS_NODE).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()){
                        Bus bus = snapshot.toObject(Bus.class);
                        busArrayList.add(bus);
                    }


                    adapter = new RoutesAdapter(DriverBusListActivity.this,busArrayList);
                    recyclerView.setAdapter(adapter);



                    adapter.setOnStartTripClickListener(new RoutesAdapter.OnStartTripClickListener() {
                        @Override
                        public void onTripClick(Bus bus) {

                            insertANewTrip(bus);

                        }
                    });

                    adapter.notifyDataSetChanged();

                }

            }
        });
//23.760686416205747, 90.372689161463
    }

    private void insertANewTrip(Bus bus) {

        dialog.setTitle("Processing ....");
        dialog.setMessage("please wait");
        dialog.show();
        String trip_id = firestore.collection(NodeName.TRIP_LIST_NODE).document().getId();
        List<String> seatList = new ArrayList();
        List<String> transactionList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            seatList.add("0");
            transactionList.add("0");
        }
        long timestamp = new Date().getTime();
        TripItem tripItem = new TripItem(bus.getDoc_id(),trip_id,seatList,transactionList,bus.getDriver_uid(),timestamp);

        firestore.collection(bus.getDoc_id())
                .document(trip_id)
                .set(tripItem)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Display.successToast(DriverBusListActivity.this,"Trip Item Success");

                            dialog.dismiss();
                        }else {
                            Log.v("Driver", "Error " + task.getException().getMessage());
                            Display.errorToast(DriverBusListActivity.this,"Error "+ task.getException().getMessage());
                            dialog.dismiss();
                        }
                    }
                });


   /* (tripItem).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    Display.successToast(DriverBusListActivity.this,"Trip Item Success");

                    dialog.dismiss();
                }else {
                    Log.v("Driver", "Error " + task.getException().getMessage());
                    Display.errorToast(DriverBusListActivity.this,"Error "+ task.getException().getMessage());
                    dialog.dismiss();
                }
            }
        });*/
    }


    private void init(){
        busArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.busRouteList);
        firestore = FirebaseFirestore.getInstance();

    }
    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

}