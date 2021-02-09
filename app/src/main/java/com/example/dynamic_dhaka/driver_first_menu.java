package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * THis activity is the first  meu of the driver
 */

public class driver_first_menu extends AppCompatActivity {
    Schedule_info t1 =new Schedule_info();
    final Schedule_info[] abc = {new Schedule_info()};
    String st_time,Bs_ids,route_id;
    String s;

    /**
     * Geting the  datas from the data base and starting the layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_first_menu);
        s=getIntent().getStringExtra("user_id");
        System.out.println("the id is "+s);

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
                            route_id=t1.getRoute_id();

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
     * This method works to show the schedule
     * @see check_schedule
     * @param v get the click from the check scheule
     */
    public void check_schedule(View v)
    { System.out.println("entered show driver schedule");
        Intent intent = new Intent(driver_first_menu.this, check_schedule.class);
        intent.putExtra("user_id",s);

        startActivity(intent);
    }

    /**
     * This method shows the route to the driver
     * @param v get the click from the check route button
     */
    public void check_route(View v)
    {
        Intent intent = new Intent(driver_first_menu.this, Route_show.class);
        intent.putExtra("route",route_id);
        startActivity(intent);
    }

    /**
     * Log out from the driver
     * @param v
     */
    public void logout(View v)
    {
        Intent intent = new Intent(driver_first_menu.this, User_type_menu.class);

        startActivity(intent);

    }
}