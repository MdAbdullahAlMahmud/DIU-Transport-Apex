package com.socalledengineers.diutransportapex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.model.Bus;
import com.socalledengineers.diutransportapex.utils.NodeName;

public class RoutesWebView extends AppCompatActivity {

    private WebView mWebView;
    private String routes_url = "https://www.google.com/maps/d/embed?mid=1J8QtXb3iMgXJTsECsIzdzu3mIgDio5Al&ll=23.815653053593255%2C90.34311500000001&z=12";

    private ImageView backToActivity;
    private TextView activity_name;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_web_view);
        firestore =FirebaseFirestore.getInstance();

        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);

        if (getIntent()!=null){
            String id = getIntent().getExtras().getString(NodeName.INTENT_MAP_MARKER);
            getSelectedBusInfo(id);
        }


        activity_name.setText(R.string.routes_details);
        mWebView = (WebView) findViewById(R.id.routes_web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        backToActivity.setOnClickListener(view -> finish());

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
}