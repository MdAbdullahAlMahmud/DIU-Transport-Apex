package com.socalledengineers.diutransportapex.model;

public class Student {

    private String name;
    private String email;
    private String id;
    private boolean  status;
    private String  uid;
    private long  account_created_at;
    private Double lat;
    private Double lon;


    public Student() {
    }

    public Student(String name, String email, String id, boolean status, String uid, long account_created_at, Double lat, Double lon) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.status = status;
        this.uid = uid;
        this.account_created_at = account_created_at;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getAccount_created_at() {
        return account_created_at;
    }

    public void setAccount_created_at(long account_created_at) {
        this.account_created_at = account_created_at;
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
