package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    Button btn_carrinho;
    TextView nome, price, desc, brand;
    ImageView imgProduct;
    ProductsGET productsGET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        btn_carrinho = findViewById(R.id.button);
        nome = findViewById(R.id.name_product);
        price = findViewById(R.id.price_product);
        desc = findViewById(R.id.desc_product);
        brand = findViewById(R.id.desc_brand);

        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            productsGET = (ProductsGET) intent.getSerializableExtra("data");
            String productNameData = productsGET.getProd_name();
            String productPrice = productsGET.getProd_price();
            String productDesc = productsGET.getProd_desc();
            String productBrand = productsGET.getProd_brand();

            nome.setText(productNameData);
            nome.setText(productPrice);
            nome.setText(productDesc);
            nome.setText(productBrand);


        }

        btn_carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, carrinho.class);
                startActivity(intent);
            }
        });
    }
}