package com.socalledengineers.diutransportapex.model;

public class Bus {
    private int id;
    private String name;
    private String starting_time;
    private Double lat;
    private Double lon;


    public Bus() {
    }

    public Bus(int id, String name, String starting_time, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.starting_time = starting_time;
        this.lat = lat;
        this.lon = lon;
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
}
