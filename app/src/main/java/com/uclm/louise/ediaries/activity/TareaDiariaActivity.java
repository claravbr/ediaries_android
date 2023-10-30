package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.uclm.louise.ediaries.ApiService;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TareaDiariaActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    Button buttonCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarea_diaria);

        getTareasChild();

        buttonCalendar = findViewById(R.id.buttonCalendar);

        // <-- VOLVER AL MENU PRINCIPAL -->
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

    private void getTareasChild() {
        SessionManager sessionManager = new SessionManager(this);
        Session session = Session.getInstance();

        ApiService service = ApiClient.getApiService(this);

        Call<List<SearchTareaDiariaResult>> tareasCall = service.fetchTareasDiarias("Bearer " + sessionManager.fetchAuthToken(), session.getChildId());
        tareasCallEnqueue(tareasCall);
    }

    private void tareasCallEnqueue(Call<List<SearchTareaDiariaResult>> tareasCall) {
        tareasCall.enqueue(new Callback<List<SearchTareaDiariaResult>>() {
            @Override
            public void onResponse(Call<List<SearchTareaDiariaResult>> call, Response<List<SearchTareaDiariaResult>> response) {

            }

            @Override
            public void onFailure(Call<List<SearchTareaDiariaResult>> call, Throwable t) {

            }
        });

    }
}

