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

    ImageButton btn_arrow;
    Button btn_next;
    EditText  field_name, field_cpf, field_email, field_password;
    TextView title_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setTheme(R.style.Theme_LoginScreen);
//        TextView title_acc = (TextView)findViewById(R.id.title_acc);
//        EditText field_name = (EditText)findViewById(R.id.field_name);
//        EditText field_cpf = (EditText)findViewById(R.id.field_cpf);
//        EditText field_email = (EditText)findViewById(R.id.field_email);
//        EditText field_password = (EditText)findViewById(R.id.field_password);
//        ImageButton btn_arrow = (ImageButton)findViewById(R.id.btn_arrow);
        btn_next = findViewById(R.id.btn_next_create_account);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, CreateAccount2nd.class);
                startActivity(intent);
            }
        });
    }
}