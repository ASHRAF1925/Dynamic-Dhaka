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

/**
 * THis activity works for adding bus drivers
 */

public class driver_addition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_addition);
        Button btn=(Button)findViewById(R.id.button9);
        final boolean[] starter =new boolean[1];
        starter[0]=true;

        final String[] serial = new String[1];
        final int[] ids = new int[1];
        //////////getting driver id
        DatabaseReference ref;
        ref=FirebaseDatabase.getInstance().getReference().child("driver_id");
        ref.addValueEventListener(new ValueEventListener() {
            /**
             * Geting the serial id from the data base
             * @param snapshot
             */
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
        });/////////////
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////getting informations
                EditText nid_name=(EditText)findViewById(R.id.d2);
                EditText nid_no=(EditText)findViewById(R.id.d1);
                EditText date=(EditText)findViewById(R.id.d3);
                EditText d_no=(EditText)findViewById(R.id.d4);
                EditText pas=(EditText)findViewById(R.id.d5);
                EditText copas=(EditText)findViewById(R.id.d6);
                String name=nid_name.getText().toString();
                String id_no=nid_no.getText().toString();
                String dob=date.getText().toString();
                String driving_no=d_no.getText().toString();
                String pass=pas.getText().toString();
                String copass=copas.getText().toString();
                System.out.println(name);
                ////////////
                //////Checking nid
                final String[] nam = new String[1];
                final String[] Dob = new String[1];


                final DatabaseReference[] ref = new DatabaseReference[1];

                ref[0] =FirebaseDatabase.getInstance().getReference().child("Database").child("NID");//connecting to NId database
                ref[0].addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.hasChild(id_no))//checking id no exists or not
                        {
                            /**
                             * Cheicking the validity of the of the information
                             */

                            if(true)
                            {
                                ref[0] = FirebaseDatabase.getInstance().getReference().child("Database").child("NID").child(id_no);//changing the reference to id no
                                {
                                    ref[0].addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                                            nam[0] =snapshot.child("Name").getValue().toString();//retriving name
                                            Dob[0] =snapshot.child("Date of Birth").getValue().toString();//retriving dob
                                            //Toast.makeText(getApplicationContext(),name[0],Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(getApplicationContext(),na,Toast.LENGTH_SHORT).show();

                                            //Toast.makeText(getApplicationContext(),Dob[0],Toast.LENGTH_SHORT).show();
                                            if(!name.equals(nam[0]))//checking the names
                                            {
                                                starter[0] =false;
                                                Toast.makeText(getApplicationContext(),"NID NAME Doesnot Match",Toast.LENGTH_SHORT).show();
                                            }
                                            if(!dob.equals(Dob[0]))//checking the Dobs
                                            {
                                                starter[0] =false;
                                                Toast.makeText(getApplicationContext(),"Date of Birth Doesnot Match",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(),"NID Number Is Not Present In National Database",Toast.LENGTH_SHORT).show();
                             starter[0]=false;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                ////////////////nid check complete
                ///driving license check
                final DatabaseReference[] dref = new DatabaseReference[1];
                dref[0] =FirebaseDatabase.getInstance().getReference().child("Database").child("Driving_Licence");
                dref[0].addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(driving_no))
                        {
                            dref[0] =FirebaseDatabase.getInstance().getReference().child("Database").child("Driving_Licence").child(driving_no);
                            dref[0].addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String doba=snapshot.child("Date of Birth").getValue().toString();
                                    String nama=snapshot.child("Name").getValue().toString();
                                    if(!name.equals(nama))
                                    {
                                        starter[0] =false;
                                        Toast.makeText(getApplicationContext(),"Driving License NAME Doesnot Match",Toast.LENGTH_SHORT).show();
                                    }
                                    if(!dob.equals(doba))
                                    {
                                        starter[0] =false;
                                        Toast.makeText(getApplicationContext(),"Driving license Birthday Doesnot Match",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Driving License Number Is Not Present In National Database",Toast.LENGTH_SHORT).show();
                            starter[0]=false;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });/////// driving license check finish
                //pass verify
                if(!copass.equals(pass))
                {
                    Toast.makeText(getApplicationContext(),"Check the pass word agian",Toast.LENGTH_SHORT).show();
                    starter[0]=false;
                }
                /////
                if(starter[0]) {
                    //////inserting driver info to data base
                    Driver temp = new Driver();
                    temp.setName(name);
                    temp.setDriver_id(serial[0]);
                    temp.setDriving_license(driving_no);
                    temp.setNid_no(id_no);
                    temp.setPassword(pass);
                    /**
                     * setting the datas to the database
                     */

                    FirebaseDatabase dat_in = FirebaseDatabase.getInstance();
                    DatabaseReference data_in = dat_in.getReference("Drivers");
                    data_in.child(serial[0]).setValue(temp);
                    nid_name.setText("");
                    nid_no.setText("");
                    copas.setText("");
                    pas.setText("");
                    d_no.setText("");
                    date.setText("");
                    /////////data insert finish

                    ///insert the driver id
                    DatabaseReference idt = FirebaseDatabase.getInstance().getReference("driver_id");
                    idt.setValue(serial[0]);
                    /////id insert finsih
                    Intent intent = new Intent(driver_addition.this, Auth_menu.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Correctly Enter All the Data",Toast.LENGTH_SHORT).show();
                }











            }
        });

    }








}
