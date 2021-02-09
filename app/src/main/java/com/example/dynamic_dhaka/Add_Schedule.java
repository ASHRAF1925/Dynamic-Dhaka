package com.example.dynamic_dhaka;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity works to add schedule to the data base
 */

public class Add_Schedule extends AppCompatActivity {
    private Spinner timer;
    Spinner spinner;
    Spinner bud;
    Spinner rud;
    final String[] serial = new String[1];
    final int[] ids = new int[1];
    String start_time,Bus_id,Route_id,Driver_id,Asssistant_id;
    ArrayList <String>timesid=new ArrayList<String>();
    ArrayList <String>routeid=new ArrayList<String>();
    ArrayList <String>driverid=new ArrayList<String>();
    ArrayList <String>assistantid=new ArrayList<String>();
    ArrayList <String>Busid=new ArrayList<String>();
    ArrayList <String>rusid=new ArrayList<String>();
    Spinner dud;
    ArrayList <String>dusid=new ArrayList<String>();
    Spinner aud;
    ArrayList <String>ausid=new ArrayList<String>();

    /**
     * This method starts the layout and get the available data from the data base
     * @param savedInstanceState
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__schedule);
        spinner = findViewById(R.id.time_bar);


        /**
         * Geting the serial id from the data base
         */
        ///////////////getiing id
        //////////getting Bus id
        DatabaseReference ref_id;
        ref_id= FirebaseDatabase.getInstance().getReference().child("Schedule_id");
        ref_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serial[0] =snapshot.getValue().toString();
                ids[0] =Integer.parseInt(serial[0]);
                ids[0]++;
                serial[0] =Integer.toString(ids[0]);
                System.out.println("serial "+serial[0]);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /**
         * Seting the time to the spinner
         */
        Bundle bundle=getIntent().getExtras();
        timesid=bundle.getStringArrayList("time");
        Busid=bundle.getStringArrayList("busid");
        Bundle bundle1=getIntent().getExtras();
        rusid=bundle1.getStringArrayList("road");
        dusid=bundle1.getStringArrayList("driver");
        ausid=bundle1.getStringArrayList("asssistant");

        ArrayAdapter<String> data;
        data = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, timesid);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Getting the selected time
             * @param parent
             * @param view
             * @param position
             * @param id
             */

                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                  start_time  =  parent.getSelectedItem().toString();
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                                  Toast.makeText(parent.getContext(), "Choose The User Type!", Toast.LENGTH_SHORT).show();

                                              }
                                          }
        );
        bud=(Spinner)findViewById(R.id.bi);
        ArrayAdapter<String> bid;
        bid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Busid);
        bid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bud.setAdapter(bid);
        bud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Geting the bus id from the user
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bus_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rud=(Spinner)findViewById(R.id.ri);
        ArrayAdapter<String> rid;
        rid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,rusid);
        rid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rud.setAdapter(rid);
        rud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Route_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dud=(Spinner)findViewById(R.id.di);
        ArrayAdapter<String> did;
        did=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,dusid);
        did.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dud.setAdapter(did);
        dud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * geting the selected driver from the spinner
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Driver_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        aud=(Spinner)findViewById(R.id.ai);
        ArrayAdapter<String> aid;
        aid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,ausid);
        aid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aud.setAdapter(aid);
        aud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * geting the selectted asssistent id
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Asssistant_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //////////////*


 ///////////////////////////////getting the tiemer option


/*

        DatabaseReference ref;
        ref=FirebaseDatabase.getInstance().getReference().child("Time_slot");
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

    public void data_insert(View v)
    { /*ArrayAdapter<String> data;
        data = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, timesid);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                               start_time  =  parent.getSelectedItem().toString();
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                                  Toast.makeText(parent.getContext(), "Choose The User Type!", Toast.LENGTH_SHORT).show();

                                              }
                                          }
        );
        bud=(Spinner)findViewById(R.id.bi);
        ArrayAdapter<String> bid;
        bid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Busid);
        bid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bud.setAdapter(bid);
        bud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bus_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rud=(Spinner)findViewById(R.id.ri);
        ArrayAdapter<String> rid;
        rid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,rusid);
        rid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rud.setAdapter(rid);
        rud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Route_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dud=(Spinner)findViewById(R.id.di);
        ArrayAdapter<String> did;
        did=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,dusid);
        did.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dud.setAdapter(did);
        dud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Driver_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        aud=(Spinner)findViewById(R.id.ai);
        ArrayAdapter<String> aid;
        aid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,ausid);
        aid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aud.setAdapter(aid);
        aud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Asssistant_id=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/


    }

    /**
     * inserting the data to the database
     * @param view
     */
    public void insert_to_base(View view)
    { Schedule_info temp=new Schedule_info();
        temp.setAssistent_id(Asssistant_id);
        temp.setBus_id(Bus_id);
        temp.setDriver_id(Driver_id);
        temp.setRoute_id(Route_id);
        temp.setStart_time(start_time);
        temp.setSchedule_id(serial[0]);
        FirebaseDatabase dat_in = FirebaseDatabase.getInstance();
        DatabaseReference data_in = dat_in.getReference("Schedules");
        data_in.child(serial[0]).setValue(temp);
        /**
         * Seting the schedule id into the database
         */
        DatabaseReference idt = FirebaseDatabase.getInstance().getReference("Schedule_id");
        idt.setValue(serial[0]);
        //Schedule_id
        /*System.out.println(start_time+"start");
        System.out.println(Bus_id+"Bus id");
        System.out.println(Route_id+"ROute id");
        System.out.println(Driver_id+"Driver");
        System.out.println(Asssistant_id+"Assistant");*/
    }



}