package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This activity is the menu of assistant
 */


public class assistent_first_menu extends AppCompatActivity {
    String s;

    /**
     * STart the layout
     * Get the assistent id
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistent_first_menu);
        s=getIntent().getStringExtra("user_id");
        System.out.println("the id is "+s);
    }

    /**
     * This method initiate the check schedule
     * @param v get the click from the check schedule button
     */
    public void check_schedule(View v)
    { System.out.println("entered show driver schedule");
        Intent intent = new Intent(assistent_first_menu.this, check_schedule.class);
        intent.putExtra("user_id",s);

        startActivity(intent);
    }

    /**
     * Log out from the assistant
     * @param v
     */
    public void logout(View v)
    {
        Intent intent = new Intent(assistent_first_menu.this, User_type_menu.class);
        intent.putExtra("user_id",s);

        startActivity(intent);
    }
    
}