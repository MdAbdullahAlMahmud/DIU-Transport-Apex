package com.socalledengineers.diutransportadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportadmin.Driver.DriverBusListActivity;
import com.socalledengineers.diutransportadmin.Driver.DriverLocationShareActivity;
import com.socalledengineers.diutransportadmin.adapter.BusAdapter;
import com.socalledengineers.diutransportadmin.model.Bus;
import com.socalledengineers.diutransportadmin.model.BusItem;
import com.socalledengineers.diutransportadmin.utils.NodeName;

import java.util.ArrayList;

public class BusListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<BusItem> busItemArrayList;
    private BusAdapter adapter;

    ImageView backToActivity;
    TextView activity_name;

    private DatabaseReference reference;
    private ProgressDialog dialog ;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
         dialog = new ProgressDialog(this);
         reference = FirebaseDatabase.getInstance().getReference();

         activity_name = findViewById(R.id.activity_name);
         backToActivity = findViewById(R.id.finish_activity);

         init();
         initRecycleView();

         backToActivity.setOnClickListener(view -> finish());

         activity_name.setText("Admin Bus List");
         getBusOfDriver();
        init();
         findViewById(R.id.addBusfloatingActionButton).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(BusListActivity.this,AddBusActivity.class));
             }
         });




    }
    private void init(){
        busItemArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.adminBusListRV);
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
                        adapter = new BusAdapter(BusListActivity.this,busItemArrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();



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