package com.midasit.challenge.model;

public class User {

    public int userId;
    public String name;
    public String email;
    public String phone;
    public String birthday;
    public String username;
    public String profileImageUrl;
    public String joinDate;

    public User(){

    }

    public User(int userId, String name, String email, String phone, String birthday, String username, String profileImageUrl, String joinDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.joinDate = joinDate;
    }
}
