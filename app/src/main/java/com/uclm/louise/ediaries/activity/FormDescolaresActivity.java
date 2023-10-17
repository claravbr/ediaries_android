package com.uclm.louise.ediaries.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.RegistroService;
import com.uclm.louise.ediaries.data.clients.RegistroClient;
import com.uclm.louise.ediaries.data.models.DEscolares;
import com.uclm.louise.ediaries.data.models.RegistroContext;
import com.uclm.louise.ediaries.data.requests.CreateDEscolaresRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormDescolaresActivity extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_descolares);

        // -- VOLVER A FORM REGISTRO DPERSONALES --
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormDescolaresActivity.this, FormDpersonalesActivity.class);
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

        TextInputEditText editLayoutSchool = findViewById(R.id.editTextSchool);
        MaterialSwitch materialSwitch = findViewById(R.id.materialSwitch);
        TextInputLayout textInputLayoutNivelAcademico = findViewById(R.id.menu);


        // Obtener los datos introducidos en el formulario
        String nivelAcademico = ((AutoCompleteTextView)textInputLayoutNivelAcademico.getEditText()).getText().toString();
        String centroEducativo = editLayoutSchool.getText().toString();
        boolean repetidor = materialSwitch.isChecked();

        // Comprobar si se ha quedado algun campo obligatorio sin rellenar y que el formato de la fecha de nacimiento sea el pedido
        if(validFields(editLayoutSchool, textInputLayoutNivelAcademico)){
            CreateDEscolaresRequest dEscolaresRequest = new CreateDEscolaresRequest(nivelAcademico, centroEducativo, repetidor);

            // Rellenar contexto
            RegistroContext registroContext = RegistroContext.getInstance();
            registroContext.setdEscolaresRequest(dEscolaresRequest);

            Intent intent = new Intent(FormDescolaresActivity.this, FormActividadFavoritaActivity.class);
            startActivity(intent);
            Log.i("Info log", "Siguiente");
        }
    }

    private boolean validFields(TextInputEditText editText, TextInputLayout textInputLayoutNivelAcademico) {
        String messageError = "Este campo no puede estar vacío";

        if (editText.getText().toString().isEmpty()) {
            editText.setError(messageError);
            editText.requestFocus();
            return false;
        } else if (textInputLayoutNivelAcademico.getEditText().getText().toString().isEmpty()){
            textInputLayoutNivelAcademico.requestFocus();
            return false;
        }
        return true;
    }
}