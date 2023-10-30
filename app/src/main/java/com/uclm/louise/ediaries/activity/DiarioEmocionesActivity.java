package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.uclm.louise.ediaries.R;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class DiarioEmocionesActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diario_emociones);

        setDate();



        buttonSave = findViewById(R.id.buttonSave);

        setSupportActionBar(topAppBar);
        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiarioEmocionesActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                Log.i("Info log", "Vuelta al menu principal");
            }
        });
    }

    private void setDate() {
        Month mes = LocalDate.now().getMonth();
        String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        TextView textViewDate = findViewById(R.id.textViewDate);

        textViewDate.setText("Fecha: " + LocalDate.now().getDayOfMonth() + " de " + nombreMes + " de " + LocalDate.now().getYear());
    }
}