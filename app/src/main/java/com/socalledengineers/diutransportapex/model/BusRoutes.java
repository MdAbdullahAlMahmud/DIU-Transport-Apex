package com.socalledengineers.diutransportapex.model;

public class BusRoutes {

    private String doc_id;
    private String routes_name;
    private String routes_description;
    private String routes_url;
    private String routes_id;


    public BusRoutes(String routes_id,String doc_id, String routes_url, String routes_name) {
        this.routes_id = routes_id;
        this.doc_id = doc_id;
        this.routes_url = routes_url;
        this.routes_name = routes_name;
    }

    public String getUid() {
        return doc_id;
    }

    public void setUid(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getRoutes_id() {
        return routes_id;
    }

    public void setRoutes_id(String routes_id) {
        this.routes_id = routes_id;
    }

    public String getRoutes_url() {
        return routes_url;
    }

    public void setRoutes_url(String routes_url) {
        this.routes_url = routes_url;
    }

    public String getRoutes_name() {
        return routes_name;
    }

    public void setRoutes_name(String routes_name) {
        this.routes_name = routes_name;
    }
}
