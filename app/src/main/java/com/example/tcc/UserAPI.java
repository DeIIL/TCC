package com.example.tcc;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

   @POST("User")
   Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
