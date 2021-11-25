package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_NAME = "com.example.TCC.NAME";
    public final static String EXTRA_MESSAGE_CPF = "com.example.TCC.CPF";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.TCC.EMAIL";
    public final static String EXTRA_MESSAGE_PASSWORD = "com.example.TCC.PASSWORD";

    ImageButton btn_arrow;
    Button btn_next;
    EditText field_name,field_cpf,field_email,field_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LoginScreen);
        setContentView(R.layout.activity_create_account);

        field_name = findViewById(R.id.field_name);
        field_cpf = findViewById(R.id.field_cpf);
        field_email = findViewById(R.id.field_email);
        field_password = findViewById(R.id.field_password);
        btn_next = findViewById(R.id.btn_next_create_account);
        btn_arrow = findViewById(R.id.btn_arrow);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, CreateAccount2nd.class);
                intent.putExtra("EXTRA_MESSAGE_NAME", field_name.getText().toString());
                intent.putExtra("EXTRA_MESSAGE_CPF", field_cpf.getText().toString());
                intent.putExtra("EXTRA_MESSAGE_EMAIL", field_email.getText().toString());
                intent.putExtra("EXTRA_MESSAGE_PASSWORD", field_password.getText().toString());

                startActivity(intent);
            }
        });

        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}