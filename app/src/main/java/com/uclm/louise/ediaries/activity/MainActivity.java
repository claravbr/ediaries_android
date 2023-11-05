package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.requests.LoginRequest;
import com.uclm.louise.ediaries.data.responses.LoginResponse;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private Button buttonRegister;

    private SessionManager sessionManager;
    private ApiClient apiClient;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences
    SharedPreferences sharedPreferences;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the data which is stored in shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // in shared prefs inside get string method
        // we are passing key value as EMAIL_KEY and
        // default value is set to null if not present.
        email = sharedPreferences.getString("EMAIL_KEY", null);
        password = sharedPreferences.getString("PASSWORD_KEY", null);


        // -- INICIAR SESION --
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        // -- REGISTRARSE --
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormRegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (email != null && password != null) {
            // SI YA ESTABA LOGUEADO, DIRECTAMENTE AL MENU PRINCIPAL
            Intent i = new Intent(MainActivity.this, MenuPrincipalActivity.class);
            startActivity(i);
        }
    }

    private void login() {

        TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);
        TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);

        // Obtener los datos introducidos en el formulario
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Comprobar si se han rellenado los campos antes de intentar continuar
        if(validFields(editTextEmail, editTextPassword)){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            // below two lines will put values for
            // email and password in shared preferences.
            editor.putString(EMAIL_KEY, email);
            editor.putString(PASSWORD_KEY, password);

            // to save our data with key and value.
            editor.apply();

            LoginRequest loginRequest = new LoginRequest(email, password);

            apiClient = new ApiClient();
            sessionManager = new SessionManager(this);

            Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
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
                        Session session = Session.getInstance();
                        session.setChildId(loginResponse.getChildId());
                        session.setUsuario(loginResponse.getUsuario());

                        sessionManager.saveAuthToken(loginResponse.getToken());

                        Intent intentMenuPrincipal = new Intent(MainActivity.this, MenuPrincipalActivity.class);
                        startActivity(intentMenuPrincipal);
                        finish();
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
                Toast.makeText(MainActivity.this, "Por favor, inicia sesión", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    void error(){
        Toast.makeText(this, "Ha ocurrido un error durante el login", Toast.LENGTH_SHORT).show();
    }
}