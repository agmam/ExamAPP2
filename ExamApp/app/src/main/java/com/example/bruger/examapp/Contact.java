package com.example.bruger.examapp;

import java.io.Serializable;

/**
 * Created by Bruger on 07-03-2017.
 */

public class Contact implements Serializable{
    String phoneNr;
    String name;
    String address;
    String email;
    String zipCode;
    String url;
    int id;
    String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public Contact(int id, String url, String zipCode, String email, String address, String name, String phoneNr) {
        this.url = url;
        this.zipCode = zipCode;
        this.email = email;
        this.address = address;
        this.name = name;
        this.phoneNr = phoneNr;
        this.id= id;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
