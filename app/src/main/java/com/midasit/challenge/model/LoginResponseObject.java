package com.midasit.challenge.model;

/**
 * Created by ichaeeun on 2018. 5. 21..
 */

public class LoginResponseObject {

    public int err;
    public LoginData data;


    public class LoginData {
        private String token;
        private String authority;

        public LoginData(String token, String admin) {
            this.token = token;
            this.authority = admin;
        }

        public String getToken() {
            return token;
        }

        public String getAdmin() {
            return authority;
        }
    }
}
