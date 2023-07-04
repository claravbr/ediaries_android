package com.uclm.louise.ediaries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Consejos extends AppCompatActivity {

    private ExpandableListView listaconsejos;
    private ExpLVAdapter adapter;
    private ArrayList<String> listPregunta;
    private Map<String, String> mapChild;

    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejos);

        listaconsejos = (ExpandableListView) findViewById(R.id.listaconsejos);
        listPregunta = new ArrayList<>();
        mapChild = new HashMap<>();

        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consejos.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cargarDatos();
    }

    private void cargarDatos(){
        listPregunta.add("¿Cómo mejoro mi atención?");
        listPregunta.add("¿Cómo mejoro mi memoria?");
        listPregunta.add("¿Qué puedo hacer para relajarme?");
        listPregunta.add("¿Cómo puedo resolver un problema?");
        listPregunta.add("¿Cómo puedo organizarme mejor?");
        listPregunta.add("¿Cómo puedo organizar mi tiempo para realizar mis tareas y actividades?");

        mapChild.put(listPregunta.get(0), "Establece un entorno de estudio tranquilo y libre de distracciones.");
        mapChild.put(listPregunta.get(1), "Utiliza técnicas de estudio que te ayuden a retener información, como resumir o hacer esquemas.");
        mapChild.put(listPregunta.get(2), "Aprende técnicas de respiración profunda y relajación muscular para reducir el estrés y la ansiedad.");
        mapChild.put(listPregunta.get(3), "Divide el problema en pasos más pequeños y abórdalos de forma secuencial.");

        adapter = new ExpLVAdapter(listPregunta, mapChild, this);
        listaconsejos.setAdapter((adapter));
    }
}