package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tcc.models.User;
import com.example.tcc.service.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager SM;
    ImageButton btn_arrow;
    Button btn_next_login;
    EditText login_email, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LoginScreen);
        setContentView(R.layout.activity_login);

        btn_arrow = findViewById(R.id.btn_arrow);
        btn_next_login = findViewById(R.id.btn_next_login);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        SM.registerListener((SensorEventListener) this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_next_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(login_email.getText().toString()) || TextUtils.isEmpty(login_password.getText().toString())) {
                    Toast.makeText(Login.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    //proceed to login
                    getUser();
                }
            }
        });
    }

    public void getUser() {
        String email = ((EditText)findViewById(R.id.login_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://4d56-191-19-238-127.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<User> call = userAPI.getUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                if(response.body().getUser_password().equals(password) && response.body().getUser_email().equals(email)) {
                    Intent intent = new Intent(Login.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] >= 5 || event.values[1] >= 5 || event.values[0] <= -5 || event.values[1] <= -5) {

            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Deseja apagar todos os dados salvos?");
            dlgAlert.setPositiveButton("Apagar todos os dados",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            login_email.getText().clear();
                            login_password.getText().clear();
                        }
                    })
                    .setNegativeButton("Cancelar",null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Nothing here
    }
}