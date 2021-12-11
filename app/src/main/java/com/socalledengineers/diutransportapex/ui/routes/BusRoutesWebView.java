package com.socalledengineers.diutransportapex.ui.routes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.utils.NodeName;

public class BusRoutesWebView extends AppCompatActivity {
    private WebView mWebView;
    private String routes_url = "https://www.google.com/maps/d/embed?mid=1J8QtXb3iMgXJTsECsIzdzu3mIgDio5Al&ll=23.815653053593255%2C90.34311500000001&z=12";

    private ImageView backToActivity;
    private TextView activity_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_routes_web_view);

        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);
        activity_name.setText("Bus Routes");
        activity_name.setText(R.string.routes_details);
        mWebView = (WebView) findViewById(R.id.bus_routes_web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        backToActivity.setOnClickListener(view -> finish());
        if (getIntent()!=null){
            routes_url = getIntent().getExtras().getString(NodeName.INTENT_BUS_ROUTE_URL);
            mWebView.loadUrl(routes_url);
            //getSelectedBusInfo(id);

        }else {
            mWebView.loadUrl(routes_url);
        }




    }
}