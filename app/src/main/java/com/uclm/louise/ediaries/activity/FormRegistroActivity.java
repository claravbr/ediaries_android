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
import com.uclm.louise.ediaries.data.clients.RegistroClient;
import com.uclm.louise.ediaries.data.models.Usuario;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRegistroActivity extends AppCompatActivity {

    private Button buttonNext;
    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_registro);

        // -- SIGUIENTE --
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });

        // -- VOLVER A MENU PRINCIPAL --
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormRegistroActivity.this, StartAppActivity.class);
                startActivity(intent);
            }
        });
    }

    private void nextStep() {
        RegistroService registroService = RegistroClient.getRegistroService(this);

        TextInputEditText editTextName = findViewById(R.id.editTextName);
        TextInputEditText editTextLastName = findViewById(R.id.editTextLastName);
        TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);
        TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);

        // Obtener los datos introducidos en el formulario
        String nombre = editTextName.getText().toString();
        String apellidos = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String fotoPath = null;

        // Crear una solicitud
        CreateUsuarioRequest usuarioRequest = new CreateUsuarioRequest(nombre, apellidos, email, password);

        // Realizar la solicitud POST
        Call<Usuario> call = registroService.createUsuario(usuarioRequest);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.i("Debug log", "entra aqui?? 2");
                if (response.isSuccessful()) {
                    // Si STATUS CODE == 200, siguiente paso en el registro (FormDpersonalesActivity)
                    Intent intent = new Intent(FormRegistroActivity.this, FormDpersonalesActivity.class);
                    startActivity(intent);
                    Log.i("Info log", "Siguiente");
                } else {
                    Log.i("Debug log", "entra aqui?? 3");
                    // Error en la solicitud
                    Log.e("Error log", "onResponse. Error en la solicitud. Código: " + response.code());
                    Toast.makeText(FormRegistroActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("Debug log", "entra aqui?? 4");
                // Error en la solicitud
                Log.e("Error log", "onFailure. Error en la solicitud. Código: " + t.getMessage());
                Toast.makeText(FormRegistroActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
