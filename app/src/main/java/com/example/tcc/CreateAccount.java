package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tcc.models.User;
import com.example.tcc.service.UserAPI;
import com.santalu.maskara.widget.MaskEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccount extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_NAME = "com.example.TCC.NAME";
    public final static String EXTRA_MESSAGE_CPF = "com.example.TCC.CPF";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.TCC.EMAIL";
    public final static String EXTRA_MESSAGE_PASSWORD = "com.example.TCC.PASSWORD";

    ImageButton btn_arrow;
    Button btn_next;
    EditText field_name,field_email,field_password;
    com.santalu.maskara.widget.MaskEditText field_cpf, field_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LoginScreen);
        setContentView(R.layout.activity_create_account);

        field_name = findViewById(R.id.field_name);
        field_cpf = findViewById(R.id.field_cpf);
        field_email = findViewById(R.id.field_email);
        field_password = findViewById(R.id.field_password);
        field_tel = findViewById(R.id.field_tel);
        btn_next = findViewById(R.id.btn_next_create_account);
        btn_arrow = findViewById(R.id.btn_arrow);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNewUser();

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

    public void postNewUser() {
        String name = ((EditText)findViewById(R.id.field_name)).getText().toString();
        String email = ((EditText)findViewById(R.id.field_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.field_password)).getText().toString();
        String tel = ((MaskEditText)findViewById(R.id.field_tel)).getUnMasked();
        String cpf = ((MaskEditText)findViewById(R.id.field_cpf)).getUnMasked();

        User user = new User(cpf, tel, name, email, password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://4d56-191-19-238-127.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<User> call = userAPI.postUser(user);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Intent intent = new Intent(CreateAccount.this, CreateAccount2nd.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}