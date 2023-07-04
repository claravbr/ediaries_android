package com.uclm.louise.ediaries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;

public class MiProgresoActivity extends AppCompatActivity {

    MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
    Button masInfoButton = findViewById(R.id.masInfoButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_progreso);

        setSupportActionBar(topAppBar);
        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiProgresoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}