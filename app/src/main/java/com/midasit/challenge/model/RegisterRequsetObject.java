package com.midasit.challenge.model;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class RegisterRequsetObject {

    /*
    {
	"username" : "sskdhf12",
	"password" : "abcd1234",
	"email" : "sskdhf12@midasit.com",
	"phone" : "010-2343-3432",
	"birthday" : "1995-02-18",
	"name" : "김하나"
}
    * */

    String username;
    String password;
    String email;
    String phone;
    String birthday;
    String name;

    public RegisterRequsetObject(String username, String password, String email, String phone, String birthday, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.name = name;
    }
}