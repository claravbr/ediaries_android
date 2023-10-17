package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.models.RegistroContext;
import com.uclm.louise.ediaries.data.requests.CreateDPersonalesRequest;
import com.uclm.louise.ediaries.enums.Sexo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormDpersonalesActivity extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private Button buttonNext;

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

        TextInputEditText editFechaNacimiento = findViewById(R.id.editFechanacimiento);
        TextInputEditText editTextWeight = findViewById(R.id.editTextWeight);
        TextInputEditText editTextHeight = findViewById(R.id.editTextHeight);
        MaterialButtonToggleGroup toggleButton = findViewById(R.id.toggleButton);

        String fechaNacimiento = editFechaNacimiento.getText().toString();

        int selectedButtonId = toggleButton.getCheckedButtonId();

        // Comprobar si se ha quedado algun campo obligatorio sin rellenar y que el formato de la fecha de nacimiento sea el pedido
        if(validFields(editFechaNacimiento, editTextHeight, editTextWeight) && validDate(fechaNacimiento) && isChecked(selectedButtonId)){

            // Obtener los datos introducidos en el formulario
            Float peso = Float.parseFloat(editTextWeight.getText().toString());
            Float altura = Float.parseFloat(editTextHeight.getText().toString());
            String sexo = null;

            if (selectedButtonId == R.id.buttonMasc) {
                sexo = String.valueOf(Sexo.Masculino);
            } else if (selectedButtonId == R.id.buttonFem) {
                sexo = String.valueOf(Sexo.Femenino);
            }

            // Rellenar contexto
            RegistroContext registroContext = RegistroContext.getInstance();

            CreateDPersonalesRequest dPersonalesRequest = new CreateDPersonalesRequest(sexo, peso, altura, fechaNacimiento);
            registroContext.setdPersonalesRequest(dPersonalesRequest);

            Intent intent = new Intent(FormDpersonalesActivity.this, FormDescolaresActivity.class);
            startActivity(intent);
            Log.i("Info log", "Siguiente");
        }
    }

    private boolean validFields(TextInputEditText... editTexts) {
        String messageError = "Este campo no puede estar vacío";

        for (TextInputEditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                editText.setError(messageError);
                editText.requestFocus();
                return false;
            }
        }
        return true;
    }

    private boolean validDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        try {
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isChecked(int selectedButtonId){
        if(selectedButtonId == View.NO_ID){
            return false;
        }
        return true;
    }

}