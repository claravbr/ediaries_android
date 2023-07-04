package com.uclm.louise.ediaries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TareaspendientesActivity extends AppCompatActivity {

    MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
    FloatingActionButton extended_fab = findViewById(R.id.extended_fab);
    Button buttonCalendar = findViewById(R.id.buttonCalendar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_pendientes);

        setSupportActionBar(topAppBar);
        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TareaspendientesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}