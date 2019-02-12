package com.example.jendi.projektgrupowy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jendi.projektgrupowy.R;
import com.example.jendi.projektgrupowy.clients.UserClient;
import com.example.jendi.projektgrupowy.models.LoginRequest;
import com.example.jendi.projektgrupowy.models.LoginResponse;
import com.example.jendi.projektgrupowy.models.RegistrationRequest;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    LoginResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.loginUsername);
        final EditText password = findViewById(R.id.loginPassword);

        Button login = findViewById(R.id.loginButton);

        final EditText rUsername = findViewById(R.id.registerUsername);
        final EditText rPassword = findViewById(R.id.registerPassword);
        final EditText rPasswordAgain = findViewById(R.id.registerPasswordAgain);
        final EditText rName = findViewById(R.id.registerName);
        final EditText rSurname = findViewById(R.id.registerSurname);

        Button register = findViewById(R.id.registerButton);

        final UserClient userClient = new UserClient();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setLogin(username.getText().toString());
                loginRequest.setPassword(password.getText().toString());

                try {
                    response = userClient.logIn(loginRequest);
                    Intent intent = new Intent(LoginActivity.this, LoggedActivity.class);
                    intent.putExtra("token", response.getToken());
                    intent.putExtra("superUser", response.getSuperUser());
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rPassword.getText().toString().equals(rPasswordAgain.getText().toString())) {
                    RegistrationRequest registrationRequest = new RegistrationRequest();
                    registrationRequest.setLogin(rUsername.getText().toString());
                    registrationRequest.setPassword(rPassword.getText().toString());
                    registrationRequest.setName(rName.getText().toString());
                    registrationRequest.setSurname(rSurname.getText().toString());

                    try {
                        response = userClient.register(registrationRequest);
                        Intent intent = new Intent(LoginActivity.this, LoggedActivity.class);
                        intent.putExtra("token", response.getToken());
                        intent.putExtra("superUser", response.getSuperUser());
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
