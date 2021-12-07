package com.example.tcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    com.denzcoskun.imageslider.ImageSlider imageSlider;
    ImageButton btn_seemore;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTheme(R.style.Theme_LoginScreen);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://4b42-191-19-238-127.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ProductsAPI productsAPI = retrofit.create(ProductsAPI.class);

        Call<List<ProductsGET>> call = productsAPI.getProducts();


        call.enqueue(new Callback<List<ProductsGET>>() {
            @Override
            public void onResponse(Call<List<ProductsGET>> call, Response<List<ProductsGET>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<ProductsGET> products = response.body();

                createCard(products);

            }


            @Override
            public void onFailure(Call<List<ProductsGET>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


        /*-------------- Hooks ----------------*/
        drawerLayout = findViewById(R.id.drawar_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageSlider = findViewById(R.id.image_slider);
        btn_seemore = findViewById(R.id.btn_seemore);

        setSupportActionBar(toolbar);


        /*-------------- ImageSlider ---------------------------*/
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.dog_slider, null));
        images.add(new SlideModel(R.drawable.toy_slide, null));
        images.add(new SlideModel(R.drawable.truck_slider, null));

        imageSlider.setImageList(images, ScaleTypes.CENTER_CROP);

        /*-------------- Navigation Drawer Menu ----------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


    }

    public void createCard(List<ProductsGET> products) {
        ListAdapter listAdapter = new ListAdapter(products, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}