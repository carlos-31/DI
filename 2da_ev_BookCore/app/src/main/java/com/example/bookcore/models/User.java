package com.example.bookcore.models;

public class User {
    private String name;
    private String phone;
    private String address;
    private String email;

    public User(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String toString(){
        return"name: " + this.name + " phone: " + this.phone + " address: " + this.address;
    }
}
