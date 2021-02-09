package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This is the first activity of the user
 * This activity selects the login and signup
 */

public class Pass_first_menu extends AppCompatActivity {
    /**
     * This method change the activity to Passenger sign up
     * @see Pass_signup
     * @param view recives the button click
     */
    public void PassSignUp(View view)
{
    Intent intent = new Intent(Pass_first_menu.this, Pass_signup.class);//going to pass signup menu
    startActivity(intent);
}

    /**
     * This method  change the activity to Passenger login
     * @see Pass_login_page
     * @param view recives button click
     */
    public void PassLogin(View view)
{
    Intent intent = new Intent(Pass_first_menu.this, Pass_login_page.class);//going to pass login menu
    startActivity(intent);
}

    /**
     * Starts the activity layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_first_menu);
    }
}