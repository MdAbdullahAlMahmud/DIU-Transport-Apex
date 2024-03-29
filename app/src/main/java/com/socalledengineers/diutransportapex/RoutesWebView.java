package com.socalledengineers.diutransportapex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.model.Bus;
import com.socalledengineers.diutransportapex.model.BusItem;
import com.socalledengineers.diutransportapex.utils.Display;
import com.socalledengineers.diutransportapex.utils.NodeName;

public class RoutesWebView extends AppCompatActivity {

    private WebView mWebView;
    private String routes_url = "https://www.google.com/maps/d/embed?mid=1J8QtXb3iMgXJTsECsIzdzu3mIgDio5Al&ll=23.815653053593255%2C90.34311500000001&z=12";

    private ImageView backToActivity;
    private TextView activity_name;

    private FirebaseFirestore firestore;

    private MaterialButton seatBookedButton;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_web_view);
        init();
        reference = FirebaseDatabase.getInstance().getReference();
        firestore =FirebaseFirestore.getInstance();

        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);

        if (getIntent()!=null){
            String id = getIntent().getExtras().getString(NodeName.INTENT_MAP_MARKER);
            //getSelectedBusInfo(id);
            getSelectedBus(id);
        }


        activity_name.setText(R.string.routes_details);
        mWebView = (WebView) findViewById(R.id.routes_web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        backToActivity.setOnClickListener(view -> finish());


        seatBookedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoutesWebView.this,SeatStatusActivity.class);
                startActivity(intent);
            }
        });

    }

    //23.760568583248563, 90.37265697264328
    private void init(){
        seatBookedButton= findViewById(R.id.seatBookedButton);
    }
    private void getSelectedBusInfo(String doc_id){
        firestore.collection(NodeName.BUS_NODE).document(doc_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    Bus bus = task.getResult().toObject(Bus.class);

                    if (bus.getRoutes_url()!=null){

                        String url = bus.getRoutes_url();
                        mWebView.loadUrl(url);
                    }else {
                        mWebView.loadUrl(routes_url);
                    }

                }
            }
        });

    }

    private void getSelectedBus(String doc_id){
        reference.child(NodeName.BUS_NODE).child(doc_id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    BusItem busItem = task.getResult().getValue(BusItem.class);
                    if (busItem.getRoutes_url()!=null){
                        String url = busItem.getRoutes_url();
                        mWebView.loadUrl(url);
                    }else {
                        mWebView.loadUrl(routes_url);
                    }
                }
            }
        });

    }
}