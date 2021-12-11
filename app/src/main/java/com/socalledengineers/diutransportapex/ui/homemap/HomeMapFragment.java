package com.socalledengineers.diutransportapex.ui.homemap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.DriverBusListActivity;
import com.socalledengineers.diutransportapex.RoutesWebView;
import com.socalledengineers.diutransportapex.model.Bus;
import com.socalledengineers.diutransportapex.model.BusItem;
import com.socalledengineers.diutransportapex.utils.Display;
import com.socalledengineers.diutransportapex.utils.NodeName;
import com.socalledengineers.diutransportapex.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;


public class HomeMapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "Map";
    private String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermission;
    private GoogleMap googleMap;
    private int REQUEST_CODE = 1001;
    private float DEFAULT_ZOOM = 16f;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private ImageButton homeMapBusSearch;
    private FirebaseFirestore firestore;


    private DatabaseReference databaseReference;
    public HomeMapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_map, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        firestore = FirebaseFirestore.getInstance();
        busArrayList = new ArrayList<>();
        busItems = new ArrayList<>();
        homeMapBusSearch = view.findViewById(R.id.homeMapBusSearch);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLocationPermission();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        homeMapBusSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DriverBusListActivity.class));
            }
        });
    }


    private void initMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().
                findFragmentById(R.id.homeMapFragment);
        supportMapFragment.getMapAsync(this);
    }

    private void getLocationPermission() {


        String permission[] = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                mLocationPermission = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(getActivity(), permission, REQUEST_CODE);
            }

        } else {
            ActivityCompat.requestPermissions(getActivity(), permission, REQUEST_CODE);
        }


    }

    private void getDeviceCurrentLocation() {

        Log.d(TAG, "getDeviceCurrentLocation ");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        try {
            Task<Location> task = fusedLocationProviderClient.getLastLocation();

            task.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        Location userLocation = task.getResult();

                        if (userLocation != null) {
                            LatLng latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
                            moveCameraToLocation(latLng, DEFAULT_ZOOM);

                        } else {
                            Display.infoToast(getContext(), "Unable to find current location");
                        }
                    } else {
                        Display.errorToast(getContext(), "Unable to find current location");
                    }
                }
            });
        } catch (SecurityException e) {
            Log.e(TAG, "SecurityException " + e.getMessage());
        }
    }

    private void moveCameraToLocation(LatLng latLng, float zoom) {
        String from =latLng.latitude+","+ latLng.longitude;
        String to = "23.752981287671716, 90.3777078984378";
        //getDirectionOfMap(from,to);

        LatLng versityLatLng = new LatLng(23.752981287671716,90.3777078984378);
        float[] results = new float[1];
        Location.distanceBetween(latLng.latitude, latLng.longitude,
                versityLatLng.latitude, versityLatLng.longitude, results);
        float distance = results[0];
        Display.successToast(getContext(),distance+"");
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        googleMap.animateCamera(cameraUpdate);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_user_home_pin_icon))
                .title("Sweet Home")
                .snippet("current location");
        //23.752981287671716, 90.3777078984378

       // LatLng diuLatLng = new LatLng(23.752981287671716,90.3777078984378);
      /*  MarkerOptions diuMarker = new MarkerOptions()
                .position(diuLatLng)
                .title("DIU ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon))
                .snippet("Versity");*/
        googleMap.addMarker(markerOptions);


    }
    private ArrayList<Bus> busArrayList;
    private ArrayList<BusItem> busItems;
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


                    for (Bus bus : busArrayList){
                        String from = bus.getFrom();
                        String to = bus.getTo();
                        if (bus.getLat() ==null || bus.getLon()==null){
                            bus.setLat(Utils.versityLatLng.latitude);
                            bus.setLon(Utils.versityLatLng.longitude);
                        }
                        LatLng latLng = new LatLng(bus.getLat(),bus.getLon());
                        String from_to = from +" ---> " + to;
                        addBusToMap(latLng,bus.getName(),from_to,bus.getDoc_id());
                    }
                }

            }
        });
//23.760686416205747, 90.372689161463
    }
    private  void getAllLiveBus(){
        busItems.clear();
        databaseReference.child(NodeName.BUS_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    for (DataSnapshot documents_snapshot : snapshot.getChildren()) {
                        BusItem bus = documents_snapshot.getValue(BusItem.class);
                        busItems.add(bus);
                        String from = bus.getFrom();
                        String to = bus.getTo();
                        if (bus.getLat() ==null || bus.getLon()==null){
                            bus.setLat(Utils.versityLatLng.latitude);
                            bus.setLon(Utils.versityLatLng.longitude);
                        }
                        LatLng latLng = new LatLng(bus.getLat(),bus.getLon());
                        String from_to = from +" ---> " + to;
                        addBusToMap(latLng,bus.getName(),from_to,bus.getDoc_id());
                    }
                    Log.v("Bus","Bus Size "+ busItems.size());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        //getAllBusFromDatabase();
        getAllLiveBus();
    }

    private  void addBusToMap(LatLng lat_lng, String name, String from_to, String doc_id){
        //googleMap.clear();
        MarkerOptions map_marker = new MarkerOptions()
                .position(lat_lng)
                .title(from_to)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon))
                .snippet(doc_id);

        googleMap.addMarker(map_marker);



        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                String id = marker.getId();
                if (!id.equals("m0")){
                    Intent intent = new Intent(getContext(), RoutesWebView.class);
                    intent.putExtra(NodeName.INTENT_MAP_MARKER,marker.getSnippet());
                    startActivity(intent);
                }

                //String tag = marker.getTag().toString();


                return false;
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        mLocationPermission = false;

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0) {

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        mLocationPermission = false;
                    }
                }
                mLocationPermission = true;
                initMap();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        Display.successToast(getContext(), "Map Ready");

        if (mLocationPermission) {
            getDeviceCurrentLocation();
        }
    }
}