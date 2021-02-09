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
 *

*this activty get the choice of staff to add and initiate the activity to add
*/
public class Staff_Type extends AppCompatActivity {
    private Spinner spinner;

    /**
     * this method starts the layout
     * ALso set the data to spinner
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__type);
        spinner=findViewById(R.id.st_spin);
        List<String> user= new ArrayList<String>();
        user.add(0,"Choose The User Type");
        user.add(1,"Driver");
        user.add(2,"Bus Assistant");
        user.add(3,"Counter Master");
        ArrayAdapter data;
        data=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,user);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  if(parent.getItemAtPosition(position).equals("Choose The User Type"))
                                                  {
                                                      Toast.makeText(parent.getContext(),"Choose The User Type!",Toast.LENGTH_SHORT).show();
                                                  }
                                                  else if(parent.getItemAtPosition(position).equals("Driver"))
                                                  {
                                                      /**
                                                       * if the selected type is driver then initiate driver addintion
                                                       * @see driver_addition
                                                       */
                                                      Intent intent = new Intent(Staff_Type.this, driver_addition.class);
                                                      startActivity(intent);

                                                  }
                                                  else if(parent.getItemAtPosition(position).equals("Bus Assistant"))
                                                  {
                                                      /**
                                                       * if the selected type is assistent then insitiate the assistent
                                                       * @see assistant_addition
                                                       */
                                                      Intent intent = new Intent(Staff_Type.this, assistant_addition.class);
                                                      startActivity(intent);

                                                  }
                                                  else if(parent.getItemAtPosition(position).equals("Counter Master"))
                                                  {
                                                      /**
                                                       * if the selected type is counter masterthen insitiate the counter master
                                                       * @see counter_master_addition
                                                       */
                                                      Intent intent = new Intent(Staff_Type.this, counter_master_addition.class);
                                                      startActivity(intent);

                                                  }
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                                  Toast.makeText(parent.getContext(),"Choose The Staff Type!",Toast.LENGTH_SHORT).show();

                                              }
                                          }
        );
    }

}