package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tcc.models.Address;
import com.example.tcc.models.User;
import com.example.tcc.service.AddressAPI;
import com.santalu.maskara.widget.MaskEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccount2nd extends AppCompatActivity {

    public  final static String EXTRA_MESSAGE_PHONE = "com.example.TCC.PHONE";
    public final static String EXTRA_MESSAGE_CEP = "com.example.TCC.CEP";
    public  final static String EXTRA_MESSAGE_CITY = "com.example.TCC.CITY";
    public  final static String EXTRA_MESSAGE_BAIRRO = "com.example.TCC.BAIRRO";
    public  final static String EXTRA_MESSAGE_ADDRESS = "com.example.TCC.ADDRESS";
    public  final static String EXTRA_MESSAGE_COMPLEMENT = "com.example.TCC.COMPLEMET";

    ImageButton btn_arrow;
    android.widget.Button btn_next;
    EditText field_cep,field_city,field_district,field_address, field_complemento, field_UF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LoginScreen);
        setContentView(R.layout.activity_create_account2nd);

        field_UF = findViewById(R.id.field_UF);
        field_cep = findViewById(R.id.field_cep);
        field_city = findViewById(R.id.field_city);
        field_district = findViewById(R.id.field_district);
        field_address = findViewById(R.id.field_address);
        field_complemento = findViewById(R.id.field_complemento);
        String value = getIntent().getStringExtra("EXTRA_MESSAGE_CPF");

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
                createAddress();
            }
        });
    }

    public void createAddress() {
        String city = ((EditText)findViewById(R.id.field_city)).getText().toString();
        String uf = ((EditText)findViewById(R.id.field_UF)).getText().toString();
        String district = ((EditText)findViewById(R.id.field_district)).getText().toString();
        String public_place = ((EditText)findViewById(R.id.field_address)).getText().toString();
        String complement = ((EditText)findViewById(R.id.field_complemento)).getText().toString();
        String cep = ((MaskEditText)findViewById(R.id.field_cep)).getUnMasked();

        Address address = new Address(cep, uf, city, district, public_place, complement);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://4d56-191-19-238-127.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AddressAPI addressAPI = retrofit.create(AddressAPI.class);
        Call<Address> call = addressAPI.createAddress(address);


        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Intent in = new Intent(CreateAccount2nd.this, HomeActivity.class);
                startActivity(in);
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
    }
}