package com.example.dynamic_dhaka;

/**
 * this class is used to store the locations
 */

public class Location {
    String Location_name;
    String distance;
    Double Latitude;
    Double longitude;
    int route_id;

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getLocation_name() {
        return Location_name;
    }

    public void setLocation_name(String location_name) {
        Location_name = location_name;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
