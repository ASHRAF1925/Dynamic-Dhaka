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

/**
 * this activity works for staff login
 */

public class staff_login extends AppCompatActivity {
    String s;

    @Override
    ///////geting about the selection data from staff first menu drop down list
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        s=getIntent().getStringExtra("user_id");
        System.out.println(s);
    }
    ////////////////checking the user id and pass from database
    public void login (View v)
    {
        EditText name=(EditText)findViewById(R.id.staff_name);
        EditText password=(EditText)findViewById(R.id.staff_pass);
        String u_name=name.getText().toString();
        String p=password.getText().toString();
        name.setText("");
        password.setText("");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(s);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(u_name))
                {
                    String rs=snapshot.child(u_name).child("password").getValue().toString();
                    if(rs.equals(p))
                    {
                        Toast.makeText(getApplicationContext(),"Login Successful !",Toast.LENGTH_SHORT).show();
                        if(s.equals("Assistants"))
                        {  /**
                           *THis initiates the assistant login
                         * @see assistent_first_menu
                         */
                        /*Intent intent = new Intent(staff_login.this, check_schedule.class);
                        intent.putExtra("user_id",u_name);

                        startActivity(intent);*/
                            Intent intent = new Intent(staff_login.this,assistent_first_menu.class);
                            intent.putExtra("user_id",u_name);
                            startActivity(intent);
                        }
                        else if(s.equals("Drivers"))
                        {
                            /**
                             * THis methods works for  driver menu
                             * @see driver_first_menu
                             */
                            System.out.println("driver entered");
                            Intent intent = new Intent(staff_login.this,driver_first_menu.class);
                            intent.putExtra("user_id",u_name);
                            startActivity(intent);
                        }
                        else if(s.equals("counter_masters"))
                        {

                            /**
                             * THis methods works for  counter master menu
                             * @see counter_master_first_menu
                             */
                            Intent intent = new Intent(staff_login.this,counter_master_first_menu.class);
                            intent.putExtra("user_id",u_name);
                            startActivity(intent);

                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Id Does not Exists !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}