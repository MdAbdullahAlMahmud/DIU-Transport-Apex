package com.socalledengineers.diutransportapex.ui.routes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.adapter.RoutesTractListAdapter;
import com.socalledengineers.diutransportapex.model.BusItem;
import com.socalledengineers.diutransportapex.utils.NodeName;

import java.util.ArrayList;

public class RoutesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<BusItem> busItemArrayList;
    private RoutesTractListAdapter adapter;

    ImageView backToActivity;
    TextView activity_name;

    private DatabaseReference reference;
    private ProgressDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_list2);
        init();
        initRecycleView();
        dialog = new ProgressDialog(this);
        reference = FirebaseDatabase.getInstance().getReference();

        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);
        activity_name.setText("Routes List");
        backToActivity.setOnClickListener(view -> finish());

    }
    private void init(){
        busItemArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.userRouteList);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getBusTrackRoutes();
    }

    private void getBusTrackRoutes(){
        busItemArrayList.clear();
        reference.child(NodeName.BUS_NODE).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()){

                        if (dataSnapshot.exists()){
                            BusItem busItem = dataSnapshot.getValue(BusItem.class);
                            busItemArrayList.add(busItem);
                        }
                        adapter = new RoutesTractListAdapter(RoutesListActivity.this,busItemArrayList);
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