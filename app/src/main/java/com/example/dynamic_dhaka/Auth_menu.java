package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.VerifiedInputEvent;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * This activity is the menu of the authority
 *
 */

public class Auth_menu extends AppCompatActivity {
    String start_time,Bus_id,Route_id,Driver_id,Asssistant_id;
    ArrayList<String> timesid=new ArrayList<String>();
    ArrayList <String>routeid=new ArrayList<String>();
    ArrayList <String>driverid=new ArrayList<String>();
    ArrayList <String>assistantid=new ArrayList<String>();
    ArrayList <String>Busid=new ArrayList<String>();
    ArrayList <String>rusid=new ArrayList<String>();
    ArrayList <String>dusid=new ArrayList<String>();
    ArrayList <String>ausid=new ArrayList<String>();

    /**
     * It starts the layout and also load all the data from the dta base
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_menu);
        /**
         * geting the times from the data base and storing it to an array
         */
        ///////////////////////////////getting the tiemer option




        DatabaseReference ref;
        ref= FirebaseDatabase.getInstance().getReference().child("Time_slot");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    timesid.add(i,"Choose the Starting time");
                    for(DataSnapshot itr :snapshot.getChildren())
                    { String t=itr.child("Start_time").getValue().toString();
                        i++;
                        timesid.add(i,t);

                        //System.out.println(timesid.get(i));


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        /////////////////////tiemr set
        /**
         * geting the bus id from the data base and storing it to an array
         */

        //////////Setting bus id


        DatabaseReference ref_bus;
        ref_bus=FirebaseDatabase.getInstance().getReference().child("Buses");
        ref_bus.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    Busid.add(i,"Choose the Bus no");
                    for(DataSnapshot itr :snapshot.getChildren())
                    { String t=itr.getKey().toString();
                        i++;
                        Busid.add(i,t);
                        //System.out.println(Busid.get(i)+" is bus id");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /**
         * Geting the road number from the data base and stronig it to array
         */
        //////////Setting route id






        DatabaseReference ref_route;
        ref_route=FirebaseDatabase.getInstance().getReference().child("route");
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
        });

        //////////
        /**
         * Geting the driver array and setting it to array
         */



        ///setting driver id



        DatabaseReference ref_dri;
        ref_dri=FirebaseDatabase.getInstance().getReference().child("Drivers");
        ref_dri.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    dusid.add("Choose the Drivers no");
                    for(DataSnapshot itr :snapshot.getChildren())
                    { String t=itr.getKey().toString();
                        i++;
                        dusid.add(i,t);
                        System.out.println(dusid.get(i)+" is Drivers id");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /////closing driver id
        /**
         * geting the assistant id and storing it to an array
         */



        ///setting Assistant id



        DatabaseReference ref_ari;
        ref_ari=FirebaseDatabase.getInstance().getReference().child("Assistants");
        ref_ari.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    ausid.add(i,"Choose the Assistants no");
                    for(DataSnapshot itr :snapshot.getChildren())
                    { String t=itr.getKey().toString();
                        i++;
                        ausid.add(i,t);
                        System.out.println(ausid.get(i)+" is Assistants id");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /////closing assistan id id*/







    }

    /**
     * This method works to add routes
     * It initiate the add location activity
     * @see Add_location
     * @param view get clik from the add route button
     */
    public void addlocation(View view)
    {
        Intent intent = new Intent(Auth_menu.this, Add_location.class);
        startActivity(intent);

    }

    /**
     * This method showe road to the authority
     * it initiates the Location view activity
     * @see Location_view_info
     * @param view
     */
    public void location_info(View view)
    {
        Intent intent = new Intent(Auth_menu.this, Location_view_info.class);
        startActivity(intent);
    }

    /**
     * it select the type of staff toa add
     * @see Staff_Type
     * @param v
     */
    public void staff_typer(View v)
    {
        Intent intent = new Intent(Auth_menu.this, Staff_Type.class);
        startActivity(intent);
    }
    public  void add_bus(View view)
    {
        Intent intent = new Intent(Auth_menu.this, Bus_addition.class);
        startActivity(intent);
    }
    public  void add_schedule(View view)
    {
        Intent intent = new Intent(Auth_menu.this, Add_Schedule.class);
        intent.putExtra("time",timesid);
        intent.putExtra("busid",Busid);
        intent.putExtra("road",rusid);
        intent.putExtra("driver",dusid);
        intent.putExtra("asssistant",ausid);
        startActivity(intent);

    }
    public void logout(View v)
    {
        Intent intent = new Intent(Auth_menu.this, User_type_menu.class);
        startActivity(intent);

    }
}