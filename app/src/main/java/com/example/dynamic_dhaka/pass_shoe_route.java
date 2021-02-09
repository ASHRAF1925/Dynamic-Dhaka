package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This actiity works to select the road id and intiate the road show
 */

public class pass_shoe_route extends AppCompatActivity {
    ArrayList<String> rusid=new ArrayList<String>();
   Spinner rud;
    String Route_id="ttrr";


    /**
     * STarts the layout
     * also set the spiner data from the array recived from the previous activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_shoe_route);
        Bundle bundle=getIntent().getExtras();
        rusid=bundle.getStringArrayList("datas");
        rud=(Spinner)findViewById(R.id.route_spinner);
        ArrayAdapter<String> rid;
        rid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,rusid);
        rid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rud.setAdapter(rid);
        rud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Geting the selected id from the spinner
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Route_id=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*DatabaseReference ref_route;
        ref_route= FirebaseDatabase.getInstance().getReference().child("route");
        ref_route.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    rusid.add(i,"Choose the Route no");
                    for(DataSnapshot itr :snapshot.getChildren())
                    { String t=itr.getKey().toString();
                        i++;
                        rusid.add(i,t);
                        System.out.println(rusid.get(i)+" is route id");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }

    /**
     * After selecting the road id it initiate the road show activity
     * @param v get clicks from the show route button
     * @see Route_show
     */

    public void show_route(View v)
    {
        if(Route_id=="ttrr")
        {
            Toast.makeText(getApplicationContext(),"Please Load and Select the Road First",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(pass_shoe_route.this, Route_show.class);
            intent.putExtra("route",Route_id);
            startActivity(intent);
        }
    }
}