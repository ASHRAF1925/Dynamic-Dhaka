package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * This activity Buys the ticket for the customer
 */

public class Ticket_buying extends AppCompatActivity {
    ArrayList<String> time_list=new ArrayList<String>();
    ArrayList<Schedule_info>schedule_list=new ArrayList<Schedule_info>();
    ArrayList<String>route_id_list=new ArrayList<String>();
    ArrayList<Location>route_lis=new ArrayList<Location>();
    Spinner schedule_spin;
    Spinner route_spin;
    Spinner dest_spin;
    String schedule_time;
    String board;
    String dest;
    int amount;
    final String[] serial = new String[1];
    final int[] ids = new int[1];
    String s;
    String r1,r2;
    double balance;
    Spinner rods;
   String Route_id;

    ArrayList<String> rusid=new ArrayList<String>();

    /**
     * It starts the lay out
     * ALso set all the datas to the spinner and get the selected value from the spinner
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_buying);
        s = getIntent().getStringExtra("user_id");
        System.out.println(s);
        rods=(Spinner)findViewById(R.id.routeidsss) ;
        Bundle bundle4=getIntent().getExtras();
//        rusid=bundle4.getStringArrayList("datas");

        ArrayAdapter<String> raid;
        raid=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,rusid);
        raid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rods.setAdapter(raid);
        rods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * select the road id selected buy the passenger
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Route_id=parent.getItemAtPosition(position).toString();
                System.out.println("selected route id is"+Route_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //////////////////////////////
        final int[] i = {0};
        for (int j = 1; j <= 2; j++) {
            /**
             * setting the location data to spinner
             */

            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference().child("route").child(Integer.toString(j));/////////route number use korte hbe
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                       route_id_list.add(i[0], "Choose the Place");

                        for (DataSnapshot itr : snapshot.getChildren()) {
                            i[0]++;

                            Location t1 = new Location();
                            t1 = itr.getValue(Location.class);
                            route_id_list.add(i[0], t1.getLocation_name());///////////name of location
                            route_lis.add(i[0] - 1, t1);

                            System.out.println(t1.getLocation_name() + "location names idss");


                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        //////////////////
            /**
             * geting the account balance of the user
             */

        DatabaseReference ac;
        ac=FirebaseDatabase.getInstance().getReference().child("Bank_Account").child(s).child("balance");
        ac.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                balance=Double.parseDouble(snapshot.getValue().toString());
                System.out.println("account balance"+ balance);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
            /**
             * geting the ticket id from the database
             */
        DatabaseReference ref_id;
        ref_id = FirebaseDatabase.getInstance().getReference().child("ticket_no");
        ref_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serial[0] = snapshot.getValue().toString();
                ids[0] = Integer.parseInt(serial[0]);
                ids[0]++;
                serial[0] = Integer.toString(ids[0]);
                System.out.println("serial " + serial[0]);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       // Bundle bundle = getIntent().getExtras();
       // route_id_list = bundle.getStringArrayList("location");
        Bundle bundle1 = getIntent().getExtras();
        time_list = bundle1.getStringArrayList("schedule");

        schedule_spin = (Spinner) findViewById(R.id.time_spin);
        ArrayAdapter<String> bid;
        bid = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, time_list);
        bid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schedule_spin.setAdapter(bid);
            /**
             * get the selected time from the spinner
             */
        schedule_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                schedule_time = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        route_spin = (Spinner) findViewById(R.id.board_spin);
        ArrayAdapter<String> rid;
        rid = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, route_id_list);
        rid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        route_spin.setAdapter(rid);
            /**
             * Get the boarding point form the spinner
             */
        route_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                board = parent.getItemAtPosition(position).toString();


                /////////////////// seting the destination point

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dest_spin = (Spinner) findViewById(R.id.des_spin);
        route_id_list.add(0, " ");
        ArrayAdapter<String> did;
        did = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, route_id_list);
        did.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dest_spin.setAdapter(did);
        dest_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**get the destination point from the spinner
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dest = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////////////getting the boarding point

            ///////////////////////////


            ///////////////////////////////getting schedule option



/*
        DatabaseReference ref;
        ref= FirebaseDatabase.getInstance().getReference().child("Schedules");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    time_list.add(i,"Choose the Time");

                    for(DataSnapshot itr :snapshot.getChildren())
                    {i++;

                        Schedule_info t1 =new Schedule_info();
                        t1=itr.getValue(Schedule_info.class);
                        time_list.add(i,t1.getStart_time());
                        schedule_list.add(i-1,t1);

                        System.out.println(t1.getBus_id()+"bus idss");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


            /////////////////////tiemr set
       /* DatabaseReference reff;
        reff= FirebaseDatabase.getInstance().getReference().child("route").child("1");
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    route_id_list.add(i,"Choose the Board Point");

                    for(DataSnapshot itr :snapshot.getChildren())
                    {i++;

                        Location t1 =new Location();
                        t1=itr.getValue(Location.class);
                        route_id_list.add(i,t1.getLocation_name());
                        route_lis.add(i-1,t1);

                        System.out.println(t1.getLocation_name()+"location names idss");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        }
    }

    /**
     * This method insert all the data to the database
     * @param v
     */

    public void buy_tickets(View v)
    { /**
     *Calculating the fare
     *
     */
        Location q1 = new Location();
        Location q2=new Location();
        Location tep1=new Location();
        double a = 0;
        double b=0;
        double c=0;

        for (int i=0;i<route_lis.size();i++)
        {

            tep1=route_lis.get(i);
            if(tep1.getLocation_name()==board)
            {
                q1=tep1;
                r1=q1.getDistance();
                System.out.println(r1+"never");
                a=Double.parseDouble(r1);
            }
            else if(tep1.getLocation_name()==dest)
            {
                q2=tep1;
                r2=q2.getDistance();
                System.out.println(r2+"here");
                b=Double.parseDouble(r2);
            }

        }
         
      
        
        c=a-b;
        if(c<0)
        {c=c*(-1);}
        Toast.makeText(getApplicationContext(),"The fare tk "+c*10+" is deducted from Account ",Toast.LENGTH_LONG).show();
        /**
         * Checking if the balnace if greater or not
         */
        if(c>balance)
        {
            Toast.makeText(getApplicationContext(),"There is not enough money in the account!Recharge it!",Toast.LENGTH_LONG).show();
        }
        else
        { /**
         *Deducting the baace from the user account and updating the account
         */
            balance=balance-c;
            Bank_Account inac=new Bank_Account();
            inac.setAccount_id(Integer.parseInt(s));
            inac.setBalance(balance);
            FirebaseDatabase acc=FirebaseDatabase.getInstance();
            DatabaseReference accin=acc.getReference("Bank_Account");
            accin.child(s).setValue(inac);
            /**
             * Initializing all the variables to a ticket object
             */
            Ticket_info insert= new Ticket_info();
            insert.setBus_id("1");
            insert.setPass_id(s);
            insert.setTicket_id(serial[0]);
            insert.setSchdedule_id("1");
            insert.setSeat_no(serial[0]);
            insert.setRoute_id("1");
            insert.setBoard_point(board);
            insert.setDestination_point(dest);
            /**
             * Seting the ticket object to the desired number
             */
            DatabaseReference idt = FirebaseDatabase.getInstance().getReference("ticket_no");
            idt.setValue(serial[0]);
            /**
             * marking the seat of the bus
             */
            DatabaseReference idtr = FirebaseDatabase.getInstance().getReference("Bus_seats").child("1");
            idtr.child(serial[0]).setValue(s);
            /**
             * inserting the updated ticket number
             */
            DatabaseReference idtrr = FirebaseDatabase.getInstance().getReference("Tickets");
            idtrr.child(serial[0]).setValue(insert);
            /**
             * after successful ticket buy changing the menu to Passenger main menu
             * @see Passenger_main_menu
             */
            Toast.makeText(getApplicationContext(),"Ticket buy successful!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Ticket_buying.this, Passenger_main_menu.class);
            //intent.putExtra("datas",rusid);
            startActivity(intent);
        }



        //////////////////////////////////////////////////////////

        ///////////////////////////////


















    }

}