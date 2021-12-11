package com.socalledengineers.diutransportapex.utils;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.auth.User;

import java.text.DecimalFormat;

public class Utils {
    public static User user = null;
    public static LatLng versityLatLng = new LatLng(23.752981287671716,90.3777078984378);
    public static LatLng permanentVersityLatLng = new LatLng(23.877548629296303,90.32100657331927);
//23.877548629296303, 90.32100657331927
    public static double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        float[] results = new float[1];
        Location.distanceBetween(StartP.latitude, StartP.longitude,
                EndP.latitude, EndP.longitude, results);
        float distance = results[0];

        return Radius * c;
    }
}
