package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.models.Usuario;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;

public class MenuPrincipalActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        sessionManager = new SessionManager(this);

        Session session = Session.getInstance();

        Toolbar toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle("¡Hola " + session.getUsuario().getNombre() + "!");
        setSupportActionBar(toolbar);

        Button buttonMisTareas = findViewById(R.id.option1Button);
        buttonMisTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, TareaDiariaActivity.class);
                startActivity(intent);
            }
        });

        Button buttonMiProgreso = findViewById(R.id.option2Button);
        buttonMiProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, MiProgresoActivity.class);
                startActivity(intent);
            }
        });

        Button buttonConsejos = findViewById(R.id.option3Button);
        buttonConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, ConsejosActivity.class);
                startActivity(intent);
            }
        });

        Button buttonComoEstoy = findViewById(R.id.option4Button);
        buttonComoEstoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, DiarioEmocionesActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCerrarSesion = findViewById(R.id.buttonLogout);
        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}