package com.example.dynamic_dhaka;

/**
 * Object to insert Passenger data to database
 */

public class PassDataInsert {
    String User_id,Nid_no,User_name,Name,Password;

    public PassDataInsert(String user_id, String nid_no, String user_name, String name, String password) {
        User_id = user_id;
        Nid_no = nid_no;
        User_name = user_name;
        Name = name;
        Password = password;
    }



    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getNid_no() {
        return Nid_no;
    }

    public void setNid_no(String nid_no) {
        Nid_no = nid_no;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
