package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This activity works to show the schedule to  the staffs
 */

public class check_schedule extends AppCompatActivity {
    String s;
    Schedule_info t1 =new Schedule_info();
    final Schedule_info[] abc = {new Schedule_info()};
    String st_time,Bs_ids;

    /**
     * THis method works to start the layout and load all the data from the database
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_schedule);
        s=getIntent().getStringExtra("user_id");

        System.out.println(s+"got here");
        DatabaseReference ref;
        ref= FirebaseDatabase.getInstance().getReference().child("Schedules");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {  int i=0;
                    //time_list.add(i,"Choose the Time");

                    for(DataSnapshot itr :snapshot.getChildren())
                    {i++;
                        /**
                         * GEting the bus id and time to show
                         */

                        Schedule_info t1 =new Schedule_info();
                        t1=itr.getValue(Schedule_info.class);
                        //time_list.add(i,t1.getStart_time());
                        //schedule_list.add(i-1,t1);
                        if(t1.getDriver_id().equals(s))
                        { System.out.println("STart time enter ");
                           abc[0] =t1;
                            System.out.println("STart time "+t1.getStart_time());
                            Bs_ids=t1.getBus_id();
                            st_time=t1.getStart_time();

                        }
                        else if(t1.getAssistent_id().equals(s))
                        {
                            abc[0] =t1;
                            Bs_ids=t1.getBus_id();
                            st_time=t1.getStart_time();
                        }




                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * Shows the infor,ation to the user
     * @param v get the click from the button click
     */
    public void check_schedule(View v)
    {
        TextView strat_time=(TextView)findViewById(R.id.st_time);
        TextView bus_id=(TextView)findViewById(R.id.bs_id);
       // System.out.println("STart time "+abc[0].getStart_time());
        strat_time.setText(st_time);
        bus_id.setText(Bs_ids);

    }

    /**
     * log out from the account
     * @param v get click from the log iut button
     */
    public  void logout(View v)
    {
        Intent intent = new Intent(check_schedule.this, User_type_menu.class);
        startActivity(intent);
    }
}