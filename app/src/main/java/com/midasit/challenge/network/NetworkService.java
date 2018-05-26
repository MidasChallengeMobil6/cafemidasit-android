package com.midasit.challenge.network;


import com.midasit.challenge.model.LoginRequestObject;
import com.midasit.challenge.model.LoginResponseObject;
import com.midasit.challenge.model.SignUpRequsetObject;
import com.midasit.challenge.model.SignUpResponseObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface NetworkService {

    @POST("/v1/signup")
    Call<SignUpResponseObject> signUp(@Body SignUpRequsetObject object);

    @POST("/v1/login")
    Call<LoginResponseObject> login(@Body LoginRequestObject object);



}
