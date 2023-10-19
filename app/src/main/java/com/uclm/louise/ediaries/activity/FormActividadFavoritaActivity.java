package com.uclm.louise.ediaries.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.models.RegistroContext;

import java.util.ArrayList;
import java.util.List;

public class FormActividadFavoritaActivity  extends AppCompatActivity {

    private List<String> actividadesFavoritas = new ArrayList<>();
    Button buttonAddActivity;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstaneState) {

        super.onCreate(savedInstaneState);
        setContentView(R.layout.form_actividadesfavoritas);

        // Añadir actividades favoritas
        buttonAddActivity = findViewById(R.id.buttonAddActivity);
        buttonAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputLayout textInputLayoutActivity = findViewById(R.id.textInputLayoutActivity);
                TextInputEditText editTextActivity = (TextInputEditText) textInputLayoutActivity.getEditText();

                if (editTextActivity != null){
                    String activity = editTextActivity.getText().toString().trim();

                    if(!activity.isEmpty()){
                        actividadesFavoritas.add(activity);
                        addChipToGroup(activity);
                        editTextActivity.setText("");
                    }
                }
            }
        });

        // -- SIGUIENTE (FormDClinicosActivity) --
        buttonNext = findViewById(R.id.buttonSubmit);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistroContext registroContext = RegistroContext.getInstance();
                registroContext.setActividadFavorita(actividadesFavoritas);

                Intent intent = new Intent(FormActividadFavoritaActivity.this, FormDclinicosActivity.class);
                startActivity(intent);
                Log.i("Info log", "Siguiente");
            }
        });
    }

    private void addChipToGroup(String activity) {
        ChipGroup chipGroup = findViewById(R.id.chipGroupActivities);
        Chip chip = new Chip(this);
        chip.setText(activity);
        chip.setCloseIconVisible(true);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actividadesFavoritas.remove(chip.getText().toString());
                chipGroup.removeView(chip);
            }
        });
        chipGroup.addView(chip);
    }
}
