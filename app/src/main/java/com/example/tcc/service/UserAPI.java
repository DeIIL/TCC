package com.example.tcc.service;

import com.example.tcc.models.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("User")
    @Headers({"content-type: application/json; charset=utf-8"})
    Call<User> postUser(@Body User user);

    @GET("User/1")
    Call<User> getUser();
}