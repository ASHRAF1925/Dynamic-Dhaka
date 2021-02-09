package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This activity is the main menu of counter master
 */

public class counter_master_first_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_master_first_menu);
    }

    /**
     * THis method acts to add balance to the user account
     * @see balance_add
     * @param v
     */
    public void add_balance(View v)
    {

    Intent intent = new Intent(counter_master_first_menu.this, balance_add.class);
    startActivity(intent);}

    /**
     * This method acts to log out from the counter master
     * @param v
     */
    public void logout(View v)
    {
        Intent intent = new Intent(counter_master_first_menu.this, User_type_menu.class);
        startActivity(intent);
    }
}