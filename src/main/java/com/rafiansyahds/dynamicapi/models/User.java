package com.rafiansyahds.dynamicapi.models;

import java.sql.Timestamp;

public class User {
    private String id;
    private String userId;
    private String nama;
    private String isActive = "Y";
    private String passwd;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User(String id, String userId, String nama, String isActive, String passwd){
        this.id = id;
        this.userId = userId;
        this.nama = nama;
        this.isActive = isActive;
        this.passwd = passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
