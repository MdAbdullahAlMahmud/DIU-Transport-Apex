package com.socalledengineers.diutransportapex.model;

public class Driver {

    private String uid;
    private int id;
    private String name;
    private String phone;


    public Driver(String uid, int id, String name, String phone) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
