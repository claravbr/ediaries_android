package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.RegistroService;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.clients.RegistroClient;
import com.uclm.louise.ediaries.data.models.Usuario;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRegistroActivity extends AppCompatActivity {

    private Button buttonNext;

    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_registro);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });

        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormRegistroActivity.this, StartAppActivity.class);
                startActivity(intent);
                Log.i("Info log", "Atrás en el formulario");
            }
        });
    }

    //Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.top_app_bar_form, menu);
        return true;
    }

    private void nextStep(){

        RegistroService registroService = RegistroClient.getRegistroService(this);

        TextInputEditText editTextName = findViewById(R.id.editTextName);
        TextInputEditText editTextLastName = findViewById(R.id.editTextLastName);
        TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);
        TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);

        // Se obtienen los datos introducidos en el formulario
        String nombre = editTextName.getText().toString();
        String apellidos = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String fotoPath = null;

        //Se crea una petición
        CreateUsuarioRequest usuarioRequest = new CreateUsuarioRequest(nombre, apellidos, email, password);

        // Realizar la solicitud POST
        Call<Usuario> call = registroService.createUsuario(usuarioRequest);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    // Si STATUS CODE == 200, Siguiente paso en el registro (FormDpersonalesActivity)
                    Intent intent = new Intent(FormRegistroActivity.this, FormDpersonalesActivity.class);
                    startActivity(intent);
                    Log.i("Info log", "Siguiente");
                } else {
                    // Error en la solicitud
                    Log.e("Error log", "Error en la solicitud. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Error en la solicitud
                Log.e("Error log", "Error en la solicitud. Código: " + t.getMessage());
            }
        });


    }


}