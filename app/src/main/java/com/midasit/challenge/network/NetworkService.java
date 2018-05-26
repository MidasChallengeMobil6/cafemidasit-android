package com.midasit.challenge.network;


import com.midasit.challenge.model.ItemResponseObject;
import com.midasit.challenge.model.LoginRequestObject;
import com.midasit.challenge.model.LoginResponseObject;
import com.midasit.challenge.model.RegisterRequsetObject;
import com.midasit.challenge.model.RegisterResponseObject;
import com.midasit.challenge.model.SignUpRequsetObject;
import com.midasit.challenge.model.SignUpResponseObject;
import com.midasit.challenge.model.User;
import com.midasit.challenge.model.UserResponseObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NetworkService {

    @POST("/v1/signup")
    Call<SignUpResponseObject> signUp(@Body SignUpRequsetObject object);

    @POST("/v1/login")
    Call<LoginResponseObject> login(@Body LoginRequestObject object);

    @GET("/v1/users")
    Call<UserResponseObject> getUsers();

    @GET("/v1/items")
    Call<ItemResponseObject> getItems(@Query("category") String category);

    @POST("/v1/users")
    Call<RegisterResponseObject> registerUser(@Body RegisterRequsetObject object);


}
