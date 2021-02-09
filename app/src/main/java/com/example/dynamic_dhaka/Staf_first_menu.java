package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * THis activity works to select the type of staffs
 */

public class Staf_first_menu extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staf_first_menu);
        spinner=(Spinner)findViewById(R.id.st_spin);
        List<String> user = new ArrayList<String>();
        user.add(0, "Choose The Staff Type");
        user.add(1, "Driver");
        user.add(2, "Assistent");
        user.add(3, "Counter_master");
        ArrayAdapter<String> data;
        data = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, user);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * THis method select the user type for the login and initiate the login
             * @param parent
             * @param view
             * @param position
             * @param id
             */

                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  if (parent.getItemAtPosition(position).equals("Choose The User Type")) {
                                                      Toast.makeText(parent.getContext(), "Choose The User Type!", Toast.LENGTH_SHORT).show();
                                                  } else if (parent.getItemAtPosition(position).equals("Driver")) {
                                                      Intent intent = new Intent(Staf_first_menu.this, staff_login.class);
                                                      startActivity(intent);
                                                      intent.putExtra("user_id","Drivers");
                                                      startActivity(intent);

                                                  } else if (parent.getItemAtPosition(position).equals("Assistent")) {
                                                      Intent intent = new Intent(Staf_first_menu.this, staff_login.class);
                                                      startActivity(intent);
                                                      intent.putExtra("user_id","Assistants");
                                                      startActivity(intent);

                                                  } else if (parent.getItemAtPosition(position).equals("Counter_master")) {
                                                      Intent intent = new Intent(Staf_first_menu.this, staff_login.class);
                                                      startActivity(intent);
                                                      intent.putExtra("user_id","counter_masters");
                                                      startActivity(intent);
                                                     // Toast.makeText(getApplicationContext(), parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                                                  }
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                                  Toast.makeText(parent.getContext(), "Choose The Staff Type!", Toast.LENGTH_SHORT).show();

                                              }
                                          }
        );
    }
}