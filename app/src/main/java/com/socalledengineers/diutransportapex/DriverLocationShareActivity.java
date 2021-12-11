package com.socalledengineers.diutransportapex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.socalledengineers.diutransportapex.utils.Display;

public class DriverLocationShareActivity extends AppCompatActivity {

    private static final float LOCATION_REFRESH_DISTANCE = 0;
    private static final long LOCATION_REFRESH_TIME = 0;
    private TextView driveLocationTV;
    protected LocationManager mLocationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_location_share);
        init();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Display.infoToast(DriverLocationShareActivity.this,"Permission not granted");
            return;
        }else {
            Display.infoToast(DriverLocationShareActivity.this,"Permission Okay");
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);
    }

    private  void init(){
        driveLocationTV = findViewById(R.id.driveLocationTV);
    }
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            driveLocationTV.setText(location.getLatitude() + " : " + location.getLongitude());
        }
    };



}