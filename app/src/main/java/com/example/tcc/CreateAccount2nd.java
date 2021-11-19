package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CreateAccount2nd extends AppCompatActivity {

    ImageButton btn_arrow;
    android.widget.Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LoginScreen);
        setContentView(R.layout.activity_create_account2nd);

        btn_arrow = findViewById(R.id.btn_arrow);
        btn_next = findViewById(R.id.btn_next_create_account);

        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount2nd.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(CreateAccount2nd.this, HomeActivity.class);
                startActivity(in);
            }
        });
    }
}