package com.rafiansyahds.dynamicapi.models;

public class SVCData {
    private String id;
    private String data;

    public SVCData(String id, String data){
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
