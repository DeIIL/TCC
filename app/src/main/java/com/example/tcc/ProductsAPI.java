package com.example.tcc;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsAPI {

    @GET("Product")
    Call<List<ProductsGET>> getProducts();
}
