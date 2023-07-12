package com.uclm.louise.ediaries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonMisTareas;
    private Button buttonMiProgreso;
    private Button buttonConsejos;
    private Button buttonComoEstoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        buttonMisTareas = findViewById(R.id.option1Button);
        buttonMisTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TareasPendientesActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción mis tareas clickada");
            }
        });

        buttonMiProgreso = findViewById(R.id.option2Button);
        buttonMiProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MiProgresoActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción mi progreso clickada");
            }
        });

        buttonConsejos = findViewById(R.id.option3Button);
        buttonConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConsejosActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción consejos y ayuda clickada");
            }
        });

        buttonComoEstoy = findViewById(R.id.option4Button);
        buttonComoEstoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DiarioEmocionesActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción cómo estoy clickada");
            }
        });

    }
}