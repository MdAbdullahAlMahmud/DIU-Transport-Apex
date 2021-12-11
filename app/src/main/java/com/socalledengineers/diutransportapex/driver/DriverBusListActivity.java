package com.socalledengineers.diutransportapex.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.RoutesAdapter;
import com.socalledengineers.diutransportapex.model.Bus;
import com.socalledengineers.diutransportapex.model.BusItem;
import com.socalledengineers.diutransportapex.utils.NodeName;

import java.util.ArrayList;

public class DriverBusListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private ArrayList<Bus> busArrayList;
    private ArrayList<BusItem> busItemArrayList;
    private RoutesAdapter adapter;

    ImageView backToActivity;
    TextView activity_name;

    private DatabaseReference reference;
    private ProgressDialog dialog ;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_list);

        dialog = new ProgressDialog(this);
        reference = FirebaseDatabase.getInstance().getReference();
        mAuth =FirebaseAuth.getInstance();

        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);

        init();
        initRecycleView();

        backToActivity.setOnClickListener(view -> finish());

        activity_name.setText(R.string.busTrip);
        getBusOfDriver();
    }

    private void init(){
        busArrayList = new ArrayList<>();
        busItemArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.busRouteList);
        firestore = FirebaseFirestore.getInstance();

    }

    private void getBusOfDriver(){
        busItemArrayList.clear();
        reference.child(NodeName.BUS_NODE).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()){

                        if (dataSnapshot.exists()){
                            BusItem busItem = dataSnapshot.getValue(BusItem.class);
                            busItemArrayList.add(busItem);

                            Log.v("Driver", "Bus Name "+ busItem.getName());
                            /*
                            if (busItem.getDriver_uid().equals(uid)){
                                busItemArrayList.add(busItem);
                            }*/

                        }
                        adapter = new RoutesAdapter(DriverBusListActivity.this,busItemArrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        adapter.setOnStartTripClickListener(new RoutesAdapter.OnStartTripClickListener() {
                            @Override
                            public void onTripClick(BusItem bus) {
                                Intent intent  = new Intent(DriverBusListActivity.this, DriverLocationShareActivity.class);
                                intent.putExtra(NodeName.INTENT_INTENT_BUS_ID,bus.getDoc_id());
                                startActivity(new Intent(DriverBusListActivity.this,DriverLocationShareActivity.class));
                                startActivity(intent);
                                //Display.infoToast(DriverBusListActivity.this,"Bus Name " + bus.getName());
                            }
                        });


                    }
                }
            }
        });

    }
    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

}