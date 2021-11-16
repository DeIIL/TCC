package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    TextView direitos, ja, entrar;
    ImageView dom,fingerprint;
    Button btn_cd;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LoginScreen);
        setContentView(R.layout.activity_main);
        TextView direitos = (TextView)findViewById(R.id.textView_direitos);
        TextView ja = (TextView)findViewById(R.id.textViewja);
        TextView entrar = (TextView)findViewById(R.id.textViewentrar);
        ImageView dom = (ImageView)findViewById(R.id.imageViewdom);
        ImageView fingerprint = (ImageView)findViewById(R.id.imageViewfingerprint);
        btn_cd = findViewById(R.id.btn_cd);


        btn_cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }

    public void login(View v){
        TextView ja = (TextView) findViewById(R.id.textViewja);
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
}