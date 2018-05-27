package com.midasit.challenge.network;


import com.midasit.challenge.model.DeleteUserResponseObject;
import com.midasit.challenge.model.ItemResponseObject;
import com.midasit.challenge.model.LoginRequestObject;
import com.midasit.challenge.model.LoginResponseObject;
import com.midasit.challenge.model.OrderRequestObject;
import com.midasit.challenge.model.PurchasesResponseObject;
import com.midasit.challenge.model.RegisterRequsetObject;
import com.midasit.challenge.model.RegisterResponseObject;
import com.midasit.challenge.model.ReserveResponseObject;
import com.midasit.challenge.model.ResultObject;
import com.midasit.challenge.model.SignUpRequsetObject;
import com.midasit.challenge.model.SignUpResponseObject;
import com.midasit.challenge.model.UserResponseObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("/v1/orders")
    Call<PurchasesResponseObject> getPurchases(@Query("userid") String user_id,
                                               @Query("year") String year,
                                               @Query("month") String month);
    @GET("/v1/orders")
    Call<ReserveResponseObject> getPurchases2();

    @POST("/v1/users")
    Call<RegisterResponseObject> registerUser(@Body RegisterRequsetObject object);



    @DELETE("/v1/users/{user_id}")
    Call<DeleteUserResponseObject> deleteUser(@Path("user_id") String userId);

    @PUT("/v1/users/{order_id}/{status}")
    Call<ResultObject> updateStatus(@Path("order_id") String order_id, @Path("status") String status);


    // TODO 3: 메뉴 추가
    @Multipart
    @POST("/v1/items")
    Call<ResultObject> addItem(@Part MultipartBody.Part file,
                                         @Part("price") RequestBody price,
                                         @Part("name") RequestBody name,
                                         @Part("category") RequestBody category);
    // TODO 1 : 메뉴 수정
    @Multipart
    @PUT("/v1/items/{item_id}")
    Call<ResultObject> updateItem(@Path("item_id") String itemId, @Part MultipartBody.Part file,
                               @Part("price") RequestBody price,
                               @Part("name") RequestBody name,
                               @Part("category") RequestBody category);

    // TODO 2: 메뉴 삭제
    @DELETE("/v1/items/{item_id}")
    Call<ResultObject> deleteItem(@Path("item_id") String userId);

    @POST("/v1/orders")
    Call<ResultObject> orderItem(@Body OrderRequestObject object);



}
