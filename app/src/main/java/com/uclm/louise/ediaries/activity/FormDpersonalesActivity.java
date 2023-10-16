package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.RegistroService;
import com.uclm.louise.ediaries.data.clients.RegistroClient;
import com.uclm.louise.ediaries.data.models.Child;
import com.uclm.louise.ediaries.data.models.DPersonales;
import com.uclm.louise.ediaries.data.models.RegistroContext;
import com.uclm.louise.ediaries.data.requests.CreateDPersonalesRequest;
import com.uclm.louise.ediaries.enums.Sexo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormDpersonalesActivity extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private Button buttonNext;

    private Integer usuarioId = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dpersonales);

        // -- VOLVER A FORM REGISTRO --
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormDpersonalesActivity.this, FormRegistroActivity.class);
                startActivity(intent);
            }
        });

        // -- SIGUIENTE --
        buttonNext = findViewById(R.id.buttonRegister);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });

    }

    private void nextStep() {

        RegistroService registroService = RegistroClient.getRegistroService(this);

        TextInputEditText editFechaNacimiento = findViewById(R.id.editFechanacimiento);
        TextInputEditText editTextWeight = findViewById(R.id.editTextWeight);
        TextInputEditText editTextHeight = findViewById(R.id.editTextHeight);
        MaterialButtonToggleGroup toggleButton = findViewById(R.id.toggleButton);

        // Obtener los datos introducidos en el formulario
        String fechaNacimiento = editFechaNacimiento.getText().toString();
        Float peso = Float.parseFloat(editTextWeight.getText().toString());
        Float altura = Float.parseFloat(editTextHeight.getText().toString());

        int selectedButtonId = toggleButton.getCheckedButtonId();
        String sexo = null;

        if (selectedButtonId == R.id.buttonMasc) {
            sexo = String.valueOf(Sexo.Masculino);
        } else if (selectedButtonId == R.id.buttonFem) {
            sexo = String.valueOf(Sexo.Femenino);
        }

        // Crear una solicitud
        RegistroContext registroContext = RegistroContext.getInstance();

        CreateDPersonalesRequest dPersonalesRequest = new CreateDPersonalesRequest(sexo,peso,altura,fechaNacimiento);
        registroContext.setdPersonalesRequest(dPersonalesRequest);
        dPersonalesRequest.setChildId(7);
        Call<DPersonales> call = registroService.createDPersonales(dPersonalesRequest);
        call.enqueue(new Callback<DPersonales>() {
            @Override
            public void onResponse(Call<DPersonales> call, Response<DPersonales> response) {

            }

            @Override
            public void onFailure(Call<DPersonales> call, Throwable t) {

            }
        });
    }
}