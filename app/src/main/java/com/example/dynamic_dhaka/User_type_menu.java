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
 * This is the second the screen of the app
 * This activity works to show the user type menu and select the user
 */

public class User_type_menu extends AppCompatActivity {
    /**
     * Spinner for user type selection
     */
    private Spinner spinner;

    /**
     * This method start the activity
     * Make the user type visible
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type_menu);
        spinner = findViewById(R.id.st_spin);
        List<String> user = new ArrayList<String>();
        user.add(0, "Choose The User Type");
        user.add(1, "Passenger");
        user.add(2, "Staff");
        user.add(3, "Authority");
        ArrayAdapter<String> data;
        data = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, user);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data);
        /**
         * This method is getting the selected value of the user and changing the menu according to the choice
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  if (parent.getItemAtPosition(position).equals("Choose The User Type")) {
                                                      Toast.makeText(parent.getContext(), "Choose The User Type!", Toast.LENGTH_SHORT).show();
                                                  } else if (parent.getItemAtPosition(position).equals("Passenger")) {
                                                      /**
                                                       * If the selected type is passenger then it is changing the activity to Passenger interface
                                                       * @see Pass_first_menu
                                                       */
                                                      Intent intent = new Intent(User_type_menu.this, Pass_first_menu.class);//goinf to pass login page
                                                      startActivity(intent);

                                                  } else if (parent.getItemAtPosition(position).equals("Staff")) {
                                                      /**
                                                       * if the selected type is Staff it is changing the activity to staff select
                                                       * @see Staf_first_menu
                                                       */
                                                      Intent intent = new Intent(User_type_menu.this, Staf_first_menu.class);//going to staff select page
                                                      startActivity(intent);

                                                  } else if (parent.getItemAtPosition(position).equals("Authority")) {
                                                      /**
                                                       * if  the selected type is authority it is changing the activity to authority
                                                       * @see Auth_first_menu
                                                       */
                                                      Intent intent = new Intent(User_type_menu.this, Auth_first_menu.class);//going to authority login page
                                                      startActivity(intent);
                                                      Toast.makeText(getApplicationContext(), parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                                                  }
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                                  Toast.makeText(parent.getContext(), "Choose The User Type!", Toast.LENGTH_SHORT).show();

                                              }
                                          }
        );
    }


}