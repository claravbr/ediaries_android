package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.requests.LoginRequest;
import com.uclm.louise.ediaries.data.responses.LoginResponse;
import com.uclm.louise.ediaries.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;

    Button buttonLogin;

    private SessionManager sessionManager;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, StartAppActivity.class);
                startActivity(intent);
                Log.i("Info log", "Vuelta al menu principal");
            }
        });

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                Log.i("Info log", "Iniciar sesión");
            }
        });

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);

        apiClient.getApiService(this).login(new LoginRequest("s@sample.com", "mypassword")).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Error al iniciar sesión
            }

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (loginResponse != null && loginResponse.getStatusCode() == 200 && loginResponse.getUsuario() != null) {
                    sessionManager.saveAuthToken(loginResponse.getAuthToken());
                } else {
                    // Error al iniciar sesión
                }
            }
        });
    }
}