package com.example.dynamic_dhaka;

/**
 * This class is used to save the ticket infos in the data base
 */

public class Ticket_info {
    String Ticket_id,pass_id,bus_id,schdedule_id,seat_no,route_id,board_point,destination_point;

    public String getTicket_id() {
        return Ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        Ticket_id = ticket_id;
    }

    public String getBoard_point() {
        return board_point;
    }

    public void setBoard_point(String board_point) {
        this.board_point = board_point;
    }

    public String getDestination_point() {
        return destination_point;
    }

    public void setDestination_point(String destination_point) {
        this.destination_point = destination_point;
    }

    public String getPass_id() {
        return pass_id;
    }

    public void setPass_id(String pass_id) {
        this.pass_id = pass_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getSchdedule_id() {
        return schdedule_id;
    }

    public void setSchdedule_id(String schdedule_id) {
        this.schdedule_id = schdedule_id;
    }

    public String getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }
}
