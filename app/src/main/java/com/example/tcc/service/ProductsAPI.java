package com.example.tcc.service;

import com.example.tcc.models.Products;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsAPI {

    @GET("Product")
    Call<List<Products>> getProducts();
}
