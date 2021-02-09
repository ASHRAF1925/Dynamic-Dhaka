package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The Main activity of Dynamic Dhaka
 * @author Md.Ashraful Islam ,Mishal-Al-Rahman,Yeasin Hossain
 * @Ids: 180041122 ,180041133 ,180041125
 * @version 1.0
 * First screen the user see
 */

public class MainActivity extends AppCompatActivity {
    /**
     * This function change the main screen to user type menu
     * @see User_type_menu
     * @param view recives the click
     */
    public void  usertype_page(View view)
    {
        Intent  intent = new Intent(MainActivity.this, User_type_menu.class);//going to user select type
        startActivity(intent);
    }

    /**
     *This method starts the layout of main activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}