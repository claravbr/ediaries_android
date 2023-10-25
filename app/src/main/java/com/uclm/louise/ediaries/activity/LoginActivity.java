package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.uclm.louise.ediaries.ApiService;
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
                login();
            }
        });


    }

    private void login() {

        TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);
        TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);

        // Obtener los datos introducidos en el formulario
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Comprobar si se han rellenado los campos antes de intentar continuar
        if(validFields(editTextEmail, editTextPassword)){

            LoginRequest loginRequest = new LoginRequest(email, password);

            apiClient = new ApiClient();
            sessionManager = new SessionManager(this);

            Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
            startActivity(intent);

            apiClient.getApiService(this).login(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    // Error al iniciar sesión
                    error();
                }

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse != null && response.code() == 200 && loginResponse.getUsuario() != null) {
                        sessionManager.saveAuthToken(loginResponse.getToken());

                        Intent intentMenuPrincipal = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                        startActivity(intentMenuPrincipal);
                        Log.i("Info log", "Sesión iniciada con éxito");

                    } else {
                        // Error al iniciar sesión
                        error();
                    }
                }
            });
        }
    }

    private boolean validFields(TextInputEditText... editTexts) {

        for (TextInputEditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                editText.requestFocus();
                return false;
            }
        }
        return true;
    }

    void error(){
        Toast.makeText(this, "Ha ocurrido un error durante el login", Toast.LENGTH_SHORT).show();

        Intent startAppIntent = new Intent(this, StartAppActivity.class);
        startActivity(startAppIntent);
    }
}