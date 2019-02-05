package com.example.jendi.projektgrupowy.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jendi.projektgrupowy.R;
import com.example.jendi.projektgrupowy.clients.LoginClient;
import com.example.jendi.projektgrupowy.models.LoginRequest;
import com.example.jendi.projektgrupowy.models.LoginResponse;

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

        final LoginClient loginClient = new LoginClient();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setLogin(username.getText().toString());
                loginRequest.setPassword(password.getText().toString());

                try {
                    response = loginClient.logIn(loginRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int a = 17;
            }
        });
    }
}
