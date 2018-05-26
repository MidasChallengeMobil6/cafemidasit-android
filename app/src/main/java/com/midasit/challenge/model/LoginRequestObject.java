package com.midasit.challenge.model;

/**
 * Created by ichaeeun on 2018. 5. 21..
 */

public class LoginRequestObject {
    String username;
    String password;

    public LoginRequestObject(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
