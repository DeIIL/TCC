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
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.tcc.models.Products;
import com.example.tcc.service.ProductsAPI;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListAdapter.ClickedItem {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    com.denzcoskun.imageslider.ImageSlider imageSlider;
    ImageButton btn_seeMore;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTheme(R.style.Theme_LoginScreen);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://4d56-191-19-238-127.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsAPI productsAPI = retrofit.create(ProductsAPI.class);

        Call<List<Products>> call = productsAPI.getProducts();


        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response);
                    return;
                }

                List<Products> products = response.body();
                createCard(products);

            }


            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


        /*-------------- Hooks ----------------*/
        drawerLayout = findViewById(R.id.drawar_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageSlider = findViewById(R.id.image_slider);
        btn_seeMore = findViewById(R.id.btn_seemore);

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

    public void createCard(List<Products> products) {
        ListAdapter listAdapter = new ListAdapter(products, this, this::ClickedProduct);
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

    @Override
    public void ClickedProduct(Products product) {
        startActivity(new Intent(this, ProductActivity.class).putExtra("data", product));
    }
}