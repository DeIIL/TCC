package com.example.tcc;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://e745-191-19-238-127.ngrok.io/api/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static UserAPI getUserAPI(){
        UserAPI userAPI = getRetrofit().create(UserAPI.class);

        return userAPI;
    }

    public static ProductsAPI getProductsAPI (){
        ProductsAPI productsAPI = getRetrofit().create(ProductsAPI.class);
        return productsAPI;
    }

}
