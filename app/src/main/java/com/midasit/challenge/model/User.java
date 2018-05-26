package com.midasit.challenge.model;

public class User {

    public int id;
    public String name;
    public String username;
    public String email;
    public String phone;
    public String birthday;
    public String joinDate;
    public String authority;

    public User(){

    }

    public User(int id, String name, String username, String email, String phone, String birthday, String joinDate, String authority) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.joinDate = joinDate;
        this.authority = authority;
    }
}
