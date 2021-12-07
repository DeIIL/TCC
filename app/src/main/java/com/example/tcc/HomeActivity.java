package com.example.tcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

    public void trustAllCertificates() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTheme(R.style.Theme_LoginScreen);

        this.trustAllCertificates();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.15.25:44393/api/Product/")
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

                for (ProductsGET product : products) {
                    String content = "";
                    content += "ID: " + product.getProd_id() + "\n";
                    content += "name: " + product.getProd_name() + "\n";
                    content += "desc: " + product.getProd_desc() + "\n";
                    content += "brand: " + product.getProd_brand() + "\n";
                    content += "price: " + product.getProd_price() + "\n";
                    content += "quant: " + product.getProd_quant() + "\n";
                    content += "img: " + product.getProd_img() + "\n";
                    content += "min: " + product.getProd_min_quant() + "\n";
                    content += "category: " + product.getFk_category() + "\n";

                    textViewResult.append(content);
                }
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
        textViewResult = findViewById(R.id.text_view_result);

        setSupportActionBar(toolbar);

        btn_seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(HomeActivity.this, ProductActivity.class);
                startActivity(in);
            }
        });
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