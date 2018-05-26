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
        private String userId;

        public LoginData(String token, String admin, String userId) {
            this.token = token;
            this.authority = admin;
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public String getAdmin() {
            return authority;
        }

        public String getId() { return userId; }
    }
}
