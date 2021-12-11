package com.socalledengineers.diutransportadmin.Driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.socalledengineers.diutransportadmin.AdminDashboard;
import com.socalledengineers.diutransportadmin.R;
import com.socalledengineers.diutransportadmin.location.GpsTracker;
import com.socalledengineers.diutransportadmin.utils.Display;
import com.socalledengineers.diutransportadmin.utils.NodeName;

import java.util.HashMap;
import java.util.Map;

public class DriverLocationShareActivity extends AppCompatActivity {


    private static final float LOCATION_REFRESH_DISTANCE = 0;
    private static final long LOCATION_REFRESH_TIME = 0;
    private TextView driveLocationTV;
    protected LocationManager mLocationManager;

    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    private GpsTracker gpsTracker;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 5000;

    String busId = "";
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_location_share);
        reference = FirebaseDatabase.getInstance().getReference();

        init();
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        if(getIntent()!=null){
            busId= getIntent().getExtras().getString(NodeName.INTENT_INTENT_BUS_ID);
        }else {
            Display.errorToast(DriverLocationShareActivity.this,"Something Error");
        }

        getLocation();

    }

    private void updateDriveLocation(String busId){
        gpsTracker = new GpsTracker(DriverLocationShareActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Map<String, Object> map = new HashMap<>();

            map.put("lat",latitude);
            map.put("lon", longitude);
            reference.child(NodeName.BUS_NODE).child(busId).updateChildren(map);

        }else{
            gpsTracker.showSettingsAlert();
        }
//23.790568583248863 , 90.37265697264328
    }
    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                updateDriveLocation(busId);
                Toast.makeText(DriverLocationShareActivity.this, "Location Updated",
                        Toast.LENGTH_SHORT).show();

            }
        }, delay);
        super.onResume();
    }
    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
        super.onPause();
    }
    public void getLocation( ){
        gpsTracker = new GpsTracker(DriverLocationShareActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            driveLocationTV.setText( latitude+ " : " + longitude);
        }else{
            gpsTracker.showSettingsAlert();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DriverLocationShareActivity.this, AdminDashboard.class);
        startActivity(intent);
    }

    private  void init(){
        driveLocationTV = findViewById(R.id.driveLocationTV);
    }




}