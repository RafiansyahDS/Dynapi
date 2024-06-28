package com.rafiansyahds.dynamicapi.models;

import java.sql.Timestamp;

public class SVCRest {
    private String id; 
    private String nama;
    private String httpMethod;
    private String httpURI;
    private String isActive = "Y";
    private Timestamp createdAt;
    private Timestamp updateAt;

    public SVCRest(String id, String nama, String httpMethod, String httpURI, String isActive){
        this.id = id;
        this.nama = nama;
        this.httpMethod = httpMethod;
        this.httpURI = httpURI;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpURI() {
        return httpURI;
    }

    public void setHttpURI(String httpURI) {
        this.httpURI = httpURI;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
