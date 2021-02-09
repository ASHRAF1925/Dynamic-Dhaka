package com.example.dynamic_dhaka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class works to store bus information
 */

public class Bus_info {
    String owner;
    String bus_id;
    String registration_number;
    String fitness_number;
    int bus_capacity=30;
    public int occupied=0;

    public Bus_info() {

    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getFitness_number() {
        return fitness_number;
    }

    public void setFitness_number(String fitness_number) {
        this.fitness_number = fitness_number;
    }

}
