package com.socalledengineers.diutransportapex.model;

import java.util.List;

public class TripItem {

    private String bus_id;
    private String doc_id;
    private List<String> seats;
    private List<String> transaction;
    private String driver_uid;
    private long created_at;


    public TripItem(String bus_id, String doc_id, List<String> seats, List<String> transaction, String driver_uid, long created_at) {
        this.bus_id = bus_id;
        this.doc_id = doc_id;
        this.seats = seats;
        this.transaction = transaction;
        this.driver_uid = driver_uid;
        this.created_at = created_at;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public List<String> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<String> transaction) {
        this.transaction = transaction;
    }

    public String getDriver_uid() {
        return driver_uid;
    }

    public void setDriver_uid(String driver_uid) {
        this.driver_uid = driver_uid;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}

