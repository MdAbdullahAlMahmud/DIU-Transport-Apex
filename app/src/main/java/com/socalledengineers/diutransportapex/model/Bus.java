package com.socalledengineers.diutransportapex.model;

import java.util.List;

public class Bus {
    private int id;
    private String name;
    private String starting_time;
    private Double lat;
    private Double lon;
    private String driver_uid;
    private String doc_id;
    private String from;
    private String to;
    private String routes_name;
    private String routes_description;
    private String routes_url;
    private String created_uid;
    private long created_at;
    private List<Integer> seats;

    public Bus() {
    }

    public Bus(int id, String name, String starting_time, Double lat, Double lon, String driver_uid, String doc_id, String from, String to, String routes_name, String routes_description, String routes_url, String created_uid, long created_at, List<Integer> seats) {
        this.id = id;
        this.name = name;
        this.starting_time = starting_time;
        this.lat = lat;
        this.lon = lon;
        this.driver_uid = driver_uid;
        this.doc_id = doc_id;
        this.from = from;
        this.to = to;
        this.routes_name = routes_name;
        this.routes_description = routes_description;
        this.routes_url = routes_url;
        this.created_uid = created_uid;
        this.created_at = created_at;
        this.seats = seats;
    }

    public String getCreated_uid() {
        return created_uid;
    }

    public void setCreated_uid(String created_uid) {
        this.created_uid = created_uid;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer>seats) {
        this.seats = seats;
    }

    public String getRoutes_name() {
        return routes_name;
    }

    public void setRoutes_name(String routes_name) {
        this.routes_name = routes_name;
    }

    public String getRoutes_description() {
        return routes_description;
    }

    public void setRoutes_description(String routes_description) {
        this.routes_description = routes_description;
    }

    public String getRoutes_url() {
        return routes_url;
    }

    public void setRoutes_url(String routes_url) {
        this.routes_url = routes_url;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getDriver_uid() {
        return driver_uid;
    }

    public void setDriver_uid(String driver_uid) {
        this.driver_uid = driver_uid;
    }
}
