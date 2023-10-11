package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uclm.louise.ediaries.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        Button buttonMisTareas = findViewById(R.id.option1Button);
        buttonMisTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, TareaDiariaActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción mis tareas clickada");
            }
        });

        Button buttonMiProgreso = findViewById(R.id.option2Button);
        buttonMiProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, MiProgresoActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción mi progreso clickada");
            }
        });

        Button buttonConsejos = findViewById(R.id.option3Button);
        buttonConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, ConsejosActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción consejos y ayuda clickada");
            }
        });

        Button buttonComoEstoy = findViewById(R.id.option4Button);
        buttonComoEstoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, DiarioEmocionesActivity.class);
                startActivity(intent);
                Log.i("Info log", "Opción cómo estoy clickada");
            }
        });
    }

}