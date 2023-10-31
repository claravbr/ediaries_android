package com.uclm.louise.ediaries.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.models.RegistroContext;
import com.uclm.louise.ediaries.data.requests.CreateDClinicosRequest;
import com.uclm.louise.ediaries.enums.TipoTDAH;
import com.uclm.louise.ediaries.utils.RegistroManager;

public class FormDclinicosActivity extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dclinicos);

        // -- VOLVER A FORM ACTIVIDADES FAVORITAS --
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormDclinicosActivity.this, FormActividadFavoritaActivity.class);
                startActivity(intent);
            }
        });

        // -- SIGUIENTE --
        buttonNext = findViewById(R.id.buttonEnd);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });

        // Aparecer y desaparecer campos opcionales según la respuesta a "¿Tienes TDAH?"
        TextView textViewTDAHTipo = findViewById(R.id.textViewTDAHTipo);
        MaterialButtonToggleGroup toggleButton = findViewById(R.id.toggleButton);
        TextInputLayout outlinedTextFieldEdadTDAH = findViewById(R.id.outlinedTextFieldEdadTDAH);
        MaterialSwitch materialSwitch = findViewById(R.id.materialSwitch);

        // Listener del switch "¿Tienes TDAH?"
        materialSwitch.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Si tiene TDAH, se muestran las preguntas adicinales en el formulario. Si no, se ocultan
                if(isChecked){
                    textViewTDAHTipo.setVisibility(View.VISIBLE);
                    toggleButton.setVisibility(View.VISIBLE);
                    outlinedTextFieldEdadTDAH.setVisibility(View.VISIBLE);
                } else {
                    textViewTDAHTipo.setVisibility(View.GONE);
                    toggleButton.setVisibility(View.GONE);
                    outlinedTextFieldEdadTDAH.setVisibility(View.GONE);
                }
            }
        }));

        // Aparecer y desaparecer campo opcional según la respuesta a "Indica si tomas alguna medicación"
        TextInputLayout outlinedTextFieldTiempoMedicacion = findViewById(R.id.outlinedTextFieldTiempoMedicacion);
        TextInputEditText editTextMedicacion = findViewById(R.id.editTextMedicacion);

        // Listener de editTextMedication
        editTextMedicacion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length() > 0){
                    outlinedTextFieldTiempoMedicacion.setVisibility(View.VISIBLE);
                } else {
                    outlinedTextFieldTiempoMedicacion.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void nextStep() {

        MaterialButtonToggleGroup toggleButton = findViewById(R.id.toggleButton);
        TextInputEditText editTextEnfermedad = findViewById(R.id.editTextEnfermedad);
        MaterialSwitch materialSwitch = findViewById(R.id.materialSwitch);
        TextInputEditText editTextEdadTDAH = findViewById(R.id.editTextEdadTDAH);
        TextInputLayout textInputLayoutDificultadAprendizaje = findViewById(R.id.menu);
        TextInputEditText editTextMedicacion = findViewById(R.id.editTextMedicacion);
        TextInputEditText editTextTiempoMedicacion = findViewById(R.id.editTextTiempoMedicacion);
        MaterialSwitch materialSwitchIntervencion = findViewById(R.id.materialSwitchIntervencion);

        int selectedButtonId = toggleButton.getCheckedButtonId();

        // Comprobar que los datos sean validos
        if(validFields(materialSwitch, editTextEdadTDAH, editTextMedicacion, editTextTiempoMedicacion, selectedButtonId)) {
            // Obtener los datos introducidos en el formulario
            String enfermedad = editTextEnfermedad.getText().toString().trim();
            boolean hasTDAH = materialSwitch.isChecked();

            Integer tdahEdad = null;
            if (!editTextEdadTDAH.getText().toString().isEmpty()) {
                tdahEdad = Integer.parseInt(editTextEdadTDAH.getText().toString().trim());
            }
            String medicacionInfo = editTextMedicacion.getText().toString().trim();
            String medicacionAntiguedad = editTextTiempoMedicacion.getText().toString().trim();
            boolean hasIntervencion = materialSwitchIntervencion.isChecked();
            String dificultad = textInputLayoutDificultadAprendizaje.getEditText().getText().toString();

            String tdahTipo = null;

            if (selectedButtonId == R.id.buttonCombinado) {
                tdahTipo = String.valueOf(TipoTDAH.Combinado);
            } else if (selectedButtonId == R.id.buttonHiperactivo) {
                tdahTipo = String.valueOf(TipoTDAH.Hiperactivo);
            } else if (selectedButtonId == R.id.buttonInatento) {
                tdahTipo = String.valueOf(TipoTDAH.Inatento);
            }

            CreateDClinicosRequest dClinicosRequest = new CreateDClinicosRequest(enfermedad, hasTDAH, tdahTipo, tdahEdad, dificultad, medicacionInfo.isEmpty(), medicacionAntiguedad, medicacionInfo, hasIntervencion);

            // Rellenar contexto
            RegistroContext registroContext = RegistroContext.getInstance();
            registroContext.setdClinicosRequest(dClinicosRequest);

            Intent intent = new Intent(FormDclinicosActivity.this, LoadingActivity.class);
            startActivity(intent);

            RegistroManager registroManager = new RegistroManager();
            registroManager.registrarUsuario(this);

        }
    }

    private boolean validFields(MaterialSwitch materialSwitch, TextInputEditText editTextEdadTDAH, TextInputEditText editTextMedicacion, TextInputEditText editTextTiempoMedicacion, int selectedButtonId) {
        String messageError = "Este campo no puede estar vacío";

        if(materialSwitch.isChecked() && !isChecked(selectedButtonId) && editTextEdadTDAH.getText().toString().isEmpty()){
            editTextEdadTDAH.setError(messageError);
            editTextEdadTDAH.requestFocus();
            return false;
        }

        if(!editTextMedicacion.getText().toString().isEmpty() && editTextMedicacion.getText().toString().isEmpty()){
            editTextTiempoMedicacion.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isChecked(int selectedButtonId){
        if(selectedButtonId == View.NO_ID){
            return false;
        }
        return true;
    }
}