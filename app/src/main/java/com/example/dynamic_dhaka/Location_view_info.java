package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
 * This activity gets the data from the authority and show the route
 */

public class Location_view_info extends AppCompatActivity {
    /**
     * it strat the layout
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_view_info);
    }

    /**
     * Yhis method get the data and show the road to the authority
     * @see Route_show
     * @param v
     */
    public void show_roads(View v)
    {  EditText ids=(EditText)findViewById(R.id.r_id);
        String id=ids.getText().toString();
        Intent intent = new Intent(Location_view_info.this, Route_show.class);
        intent.putExtra("route",id);
        startActivity(intent);
    }

   


}