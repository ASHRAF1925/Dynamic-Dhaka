package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This activty works to add neew routes to the system
 */

public class Add_location extends AppCompatActivity {
    final String[] serial = new String[1];
    List<Location> route=new ArrayList<Location>();


    /**
     * This method starts the layout
     * it also get theroute id from the data base
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        //Toast.makeText(getApplicationContext(),"entered",Toast.LENGTH_SHORT).show();
        DatabaseReference ref;

        final int[] ids = new int[1];
        ref= FirebaseDatabase.getInstance().getReference().child("route_id");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serial[0] =snapshot.getValue().toString();
                ids[0] =Integer.parseInt(serial[0]);
                ids[0]++;
                serial[0]=Integer.toString(ids[0]);
                System.out.println(serial[0]);



                //Toast.makeText(getApplicationContext(),serial[0]+"is the string",Toast.LENGTH_SHORT).show();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }

    /**
     * This method add check points to the road by pushing the location into an array
     * @param view
     */
    public  void check_points(View view)
    {//Toast.makeText(getApplicationContext(),"prob",Toast.LENGTH_LONG).show();
        /// adding location to data base

        EditText rname=(EditText)findViewById(R.id.rnm);
        EditText lat=(EditText)findViewById(R.id.lat);
        EditText lng=(EditText)findViewById(R.id.lng);
        EditText dist=(EditText)findViewById(R.id.dist);
       String roname;
        Double latitude,longitude;
        String distanc;
        roname=rname.getText().toString();
        latitude=Double.parseDouble(lat.getText().toString());
        longitude=Double.parseDouble(lng.getText().toString());
        distanc=dist.getText().toString();
        Location temp=new Location();
        temp.setLocation_name(roname);
        temp.setLatitude(latitude);
        temp.setLongitude(longitude);
        temp.setDistance(distanc);
        temp.setRoute_id(Integer.parseInt(serial[0]));
        route.add(temp);
        rname.setText("");
        lat.setText("");
        lng.setText("");
        dist.setText("");
        System.out.println(route.get(0).getLocation_name());
    }

    /**
     * This method add the whole road to the data base by uploading the array
     * @param view
     */
    public void tray(View view)
    {
           //inserting route to data baser
        /**
         * Inserting route to the data base
         */
        FirebaseDatabase uid = FirebaseDatabase.getInstance();
        DatabaseReference node = uid.getReference();
        ///Toast.makeText(getApplicationContext(),"  inserting to database ",Toast.LENGTH_SHORT).show();
        node.child("route_id").setValue(serial[0]);
        /**
         * inserting the serial number to the database
         */
        FirebaseDatabase help=FirebaseDatabase.getInstance();
        DatabaseReference na=help.getReference();
        na.child("route").child(serial[0]).setValue(route);



    }
}