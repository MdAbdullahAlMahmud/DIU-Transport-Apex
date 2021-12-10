package com.socalledengineers.diutransportapex.model;

import com.google.firebase.Timestamp;

public class FeedbackModel {
    private String desc;
    private String name;
    private String uid;
    private Timestamp timestamp;


    public FeedbackModel() {
    }

    public FeedbackModel(String desc, String name, String uid, Timestamp timestamp) {
        this.desc = desc;
        this.name = name;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
