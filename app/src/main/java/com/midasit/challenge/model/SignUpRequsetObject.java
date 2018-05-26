package com.midasit.challenge.model;

/**
 * Created by ichaeeun on 2018. 5. 21..
 */

public class SignUpRequsetObject {
    String name;
    String username;
    String email;
    String password;

    public SignUpRequsetObject(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
