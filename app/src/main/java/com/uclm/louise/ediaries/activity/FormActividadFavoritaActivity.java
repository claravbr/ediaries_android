package com.uclm.louise.ediaries.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.models.RegistroContext;
import com.uclm.louise.ediaries.data.requests.CreateActividadesFavoritasRequest;

import java.util.ArrayList;
import java.util.List;

public class FormActividadFavoritaActivity  extends AppCompatActivity {

    private List<Integer> actividadesFavoritas = new ArrayList<>();
    Button buttonNext;
    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstaneState) {

        super.onCreate(savedInstaneState);
        setContentView(R.layout.form_actividadesfavoritas);

        ChipGroup chipGroup = findViewById(R.id.chipGroupActivities);

        String[] actividadesArray = getResources().getStringArray(R.array.actividades_favoritas);

        for (String actividad : actividadesArray) {
            String[] parts = actividad.split(",");
            if (parts.length == 2) {
                String nombre = parts[0];
                int idActividad = Integer.parseInt(parts[1]);

                Chip chip = new Chip(this);
                chip.setText(nombre);
                chip.setCheckable(true);

                // Listener para manejar la selección
                chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        actividadesFavoritas.add(idActividad);
                    } else {
                        actividadesFavoritas.remove(Integer.valueOf(idActividad));
                    }
                });

                chipGroup.addView(chip);
            }
        }

        // -- SIGUIENTE (FormDClinicosActivity) --
        buttonNext = findViewById(R.id.buttonSubmit);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar si se ha añadido al menos una actividad favorita
                if (checkActivities(chipGroup)) {
                    CreateActividadesFavoritasRequest actividadesFavoritasRequest = new CreateActividadesFavoritasRequest();
                    actividadesFavoritasRequest.setActividadfavoritaIds(actividadesFavoritas);


                    RegistroContext registroContext = RegistroContext.getInstance();
                    registroContext.setActividadesFavoritasRequest(actividadesFavoritasRequest);

                    Intent intent = new Intent(FormActividadFavoritaActivity.this, FormDclinicosActivity.class);
                    startActivity(intent);
                    Log.i("Info log", "Siguiente");
                }
            }
        });

        // -- VOLVER A FORM REGISTRO DESCOLARES --
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormActividadFavoritaActivity.this, FormDescolaresActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkActivities(ChipGroup chipGroup) {

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            View child = chipGroup.getChildAt(i);
            if (child instanceof Chip) {
                Chip chip = (Chip) child;
                if (chip.isChecked()) {
                    return true;
                }
            }
        }
        Toast.makeText(this, "Selecciona al menos una actividad favorita", Toast.LENGTH_SHORT).show();
        return false;
    }

}
