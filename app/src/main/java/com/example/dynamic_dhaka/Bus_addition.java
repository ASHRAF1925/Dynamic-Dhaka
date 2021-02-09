package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
 * This Activity Checks the infos of bus and add a new bus to the database
 */

public class Bus_addition extends AppCompatActivity {


    final String[] serial = new String[1];
    final int[] ids = new int[1];
    final boolean[] starter =new boolean[1];
   //Button btn;

    /**
     * THis method starts the layout and also get the bus id from the database
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        starter[0]=true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_addition);

        //////////getting Bus id
        DatabaseReference ref;
        ref= FirebaseDatabase.getInstance().getReference().child("Bus_id");
        ref.addValueEventListener(new ValueEventListener() {
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
        });//////////////*

    }

    /**
     * THis method checks the bus information and add it to the datbase
     * @param view
     */
    public void add_data(View view)
    {
        //////getting informations
        EditText o_name=(EditText)findViewById(R.id.ona);
        EditText r_no=(EditText)findViewById(R.id.rno);
        EditText f_no=(EditText)findViewById(R.id.fno);


        String Owner_name=o_name.getText().toString();
        String reg_no=r_no.getText().toString();
        String fit_no=f_no.getText().toString();
        System.out.println("owner "+Owner_name);


        ////////////

        //////Checking nid

                final String[] nam = new String[1];
                final String[] Dob = new String[1];


                final DatabaseReference[] ref = new DatabaseReference[1];

                ref[0] =FirebaseDatabase.getInstance().getReference().child("Database").child("Bus_reg_server");//connecting to NId database
                ref[0].addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.hasChild(reg_no))//checking id no exists or not
                        {
                            /**
                             * Checking the information correctness
                             */

                            if(true)
                            {
                                ref[0] = FirebaseDatabase.getInstance().getReference().child("Database").child("Bus_reg_server").child(reg_no);//changing the reference to id no
                                {
                                    ref[0].addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                                            nam[0] =snapshot.child("Owner's Name").getValue().toString();//retriving name
                                            Dob[0] =snapshot.child("Fitness number").getValue().toString();//retriving dob
                                            //Toast.makeText(getApplicationContext(),name[0],Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(getApplicationContext(),na,Toast.LENGTH_SHORT).show();

                                            //Toast.makeText(getApplicationContext(),Dob[0],Toast.LENGTH_SHORT).show();
                                            if(!o_name.equals(nam[0]))//checking the names
                                            {
                                                starter[0] =false;
                                                Toast.makeText(getApplicationContext(),"Owner  NAME Doesnot Match",Toast.LENGTH_SHORT).show();
                                            }
                                            if(!f_no.equals(Dob[0]))//checking the Dobs
                                            {
                                                starter[0] =false;
                                                Toast.makeText(getApplicationContext(),"Fitness no Doesnot Match",Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });}
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Bus Registration  Number Is Not Present In National Database",Toast.LENGTH_SHORT).show();
                            starter[0]=false;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                ////////////////nid check complete



                /////
                if(starter[0]) {
                    System.out.println("enter insert");
                    //////inserting driver info to data base
                    Bus_info temp = new Bus_info();
                  temp.setOwner(Owner_name);
                  temp.setRegistration_number(reg_no);
                  temp.setFitness_number(fit_no);
                    List<Integer> seats=new ArrayList<Integer>();
                    /**
                     * adding seat to the bus
                     */

                    for(int i=0;i<31;i++)
                        seats.add(0);
                    /**
                     * Adding the seat array to the bus
                     */
                    FirebaseDatabase qq = FirebaseDatabase.getInstance();
                    DatabaseReference rr = qq.getReference("Bus_seats");
                    rr.child(serial[0]).setValue(seats);
                    //DatabaseReference na=help.getReference();
                    //        na.child("route").child(serial[0]).setValue(route);

                   /**
                   *Seting the data to the database
                    */
                    FirebaseDatabase dat_in = FirebaseDatabase.getInstance();
                    DatabaseReference data_in = dat_in.getReference("Buses");
                    data_in.child(serial[0]).setValue(temp);
                    r_no.setText("");
                    f_no.setText("");
                    o_name.setText("");

                    /////////data insert finish


                    ///insert the driver id
                    /**
                     * Seting the serial to the database
                     */
                    DatabaseReference idt = FirebaseDatabase.getInstance().getReference("Bus_id");
                    idt.setValue(serial[0]);
                    /////id insert finsih
                    Intent intent = new Intent(Bus_addition.this, Auth_menu.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Correctly Enter All the Data",Toast.LENGTH_SHORT).show();
                }
                ///adding seats













    }
}