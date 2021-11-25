package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class CreateAccount2nd extends AppCompatActivity {

    public  final static String EXTRA_MESSAGE_PHONE = "com.example.TCC.PHONE";
    public final static String EXTRA_MESSAGE_CEP = "com.example.TCC.CEP";
    public  final static String EXTRA_MESSAGE_CITY = "com.example.TCC.CITY";
    public  final static String EXTRA_MESSAGE_BAIRRO = "com.example.TCC.BAIRRO";
    public  final static String EXTRA_MESSAGE_ADDRESS = "com.example.TCC.ADDRESS";
    public  final static String EXTRA_MESSAGE_COMPLEMENT = "com.example.TCC.COMPLEMET";

    ImageButton btn_arrow;
    android.widget.Button btn_next;
    EditText field_cpf,field_tel,field_cep,field_city,field_bairro,field_address,field_complement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LoginScreen);
        setContentView(R.layout.activity_create_account2nd);

        field_tel = findViewById(R.id.field_tel);
        field_cpf = findViewById(R.id.field_cpf);
        field_cep = findViewById(R.id.field_cep);
        field_city = findViewById(R.id.field_cidade);
        field_bairro = findViewById(R.id.field_bairro);
        field_address = findViewById(R.id.field_endereco);
        field_complement = findViewById(R.id.field_complemento);
        String value = getIntent().getStringExtra("EXTRA_MESSAGE_CPF");
        field_cpf.setText(value);

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
                in.putExtra("EXTRA_MESSAGE_PHONE", field_tel.getText().toString());
                in.putExtra("EXTRA_MESSAGE_CEP", field_cep.getText().toString());
                in.putExtra("EXTRA_MESSAGE_CITY", field_city.getText().toString());
                in.putExtra("EXTRA_MESSAGE_BAIRRO", field_bairro.getText().toString());
                in.putExtra("EXTRA_MESSAGE_ADDRESS", field_address.getText().toString());
                in.putExtra("EXTRA_MESSAGE_COMPLEMENT", field_complement.getText().toString());

                startActivity(in);
            }
        });
    }
}