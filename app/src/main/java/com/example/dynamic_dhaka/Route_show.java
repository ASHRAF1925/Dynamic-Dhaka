package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This Activity works to show the roads to the user
 * It get data  from the previous route and shoe it on the google map
 */

public class Route_show extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String route_id;

    /**
     * Starts the layout of the activity
     * Also load the data passed from the previous activity
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_show);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        route_id = getIntent().getStringExtra("route");
        System.out.println(route_id);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    /**
     * Get the data from the location array and show it on the google map using the marks
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        List <Location>route_list=new ArrayList<Location>();

        mMap = googleMap;
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("route").child(route_id);
         reference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 Location temp;
                 if(snapshot.exists())
                 { int i=0;
                     for(DataSnapshot dss : snapshot.getChildren())
                     {
                          temp=dss.getValue(Location.class);
                          route_list.add(temp);
                         Double lat=route_list.get(i).getLatitude();
                         Double lng=route_list.get(i).getLongitude();
                         String name=route_list.get(i).getLocation_name();
                         System.out.println(name);
                         // Add a marker in Sydney and move the camera
                         LatLng sydney = new LatLng(lat, lng);
                         mMap.addMarker(new MarkerOptions().position(sydney).title(name));
                         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10));
                         i++;

                     }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


    }
}