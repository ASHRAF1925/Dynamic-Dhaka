package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This activity Works to add balance to the customer account
 */

public class balance_add extends AppCompatActivity {

    String ids;
    Double bal,balance;
    EditText amnrt;
    EditText account_id;

    /**
     * This method starts the layout
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_add);

    }

    /**
     * Geting the balance from the database
     *
     * @param v get the click from the balance amount
     */
    public void datas(View v)
    {
        amnrt=(EditText)findViewById(R.id.amnt);
        account_id=(EditText)findViewById(R.id.acid);

        ids=account_id.getText().toString();
        System.out.println("id of account is "+ids);


        DatabaseReference ac;
        ac=FirebaseDatabase.getInstance().getReference().child("Bank_Account").child("1").child("balance");
        ac.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                balance=Double.parseDouble(snapshot.getValue().toString());
                System.out.println("account balance"+ balance);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    /**
     * UPdating the balance and setting it to the database
     * @param v get the click form add
     */
    public void addition(View v)
    {

      bal=Double.parseDouble(amnrt.getText().toString());
        balance += bal;
        Bank_Account inac=new Bank_Account();
        inac.setAccount_id(Integer.parseInt(ids));
        inac.setBalance(balance);
        /**
         * Setting the balance to database
         */
        FirebaseDatabase acc=FirebaseDatabase.getInstance();
        DatabaseReference accin=acc.getReference("Bank_Account");
        accin.child(ids).setValue(inac);
        Intent intent = new Intent(balance_add.this, counter_master_first_menu.class);

        startActivity(intent);
    }


    ////

        ///
}