package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.uclm.louise.ediaries.R;

public class TareaDiariaActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    Button buttonCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarea_diaria);

        buttonCalendar = findViewById(R.id.buttonCalendar);

        setSupportActionBar(topAppBar);
        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TareaDiariaActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
            }
        });
    }
}

