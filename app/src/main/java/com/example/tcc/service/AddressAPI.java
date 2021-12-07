package com.example.tcc.service;

import com.example.tcc.models.Address;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddressAPI {

    @POST("Address")
    @Headers({"content-type: application/json; charset=utf-8"})
    Call<Address> createAddress(@Body Address address);
}