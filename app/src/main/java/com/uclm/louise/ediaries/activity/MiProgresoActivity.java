package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.uclm.louise.ediaries.ApiService;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;
import com.uclm.louise.ediaries.utils.ProgresoAdapter;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiProgresoActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    private final String errorServerMessage = "Error en la llamada al servidor: ";
    private final String errorRegisterMessage = "Error en el registro: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_progreso);

        // <-- VOLVER AL MENU PRINCIPAL -->
        setSupportActionBar(topAppBar);
        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiProgresoActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                Log.i("Info log", "Vuelta al menu principal");
            }
        });

        // Cargar información sobre las tareas del usuario, si tiene
        getTareasChild();
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
                if(response.isSuccessful()){

                    RecyclerView recyclerView = findViewById(R.id.recyclerViewTareas);
                    TextView textSinTareas = findViewById(R.id.textSinTareas);

                    if(!response.body().isEmpty()){
                        List<SearchTareaDiariaResult> listaTareas = response.body();

                        recyclerView.setVisibility(View.VISIBLE);
                        textSinTareas.setVisibility(View.GONE);

                        // Actualiza el texto de la vista con las tareas que se han completado en el dia actual
                        tareasCompletadasHoy(listaTareas);

                        ProgresoAdapter adapter = new ProgresoAdapter(listaTareas, getCategorias(listaTareas));
                        LinearLayoutManager linearLayout = new LinearLayoutManager(MiProgresoActivity.this);

                        recyclerView.setLayoutManager(linearLayout);
                        recyclerView.setAdapter(adapter);

                    } else {
                        recyclerView.setVisibility(View.GONE);
                        textSinTareas.setVisibility(View.VISIBLE);
                    }

                } else {

                    Toast.makeText(MiProgresoActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

                    Intent startAppIntent = new Intent(MiProgresoActivity.this, MenuPrincipalActivity.class);
                    startActivity(startAppIntent);

                    Log.e("Error log", errorRegisterMessage + response.code());

                }
            }

            @Override
            public void onFailure(Call<List<SearchTareaDiariaResult>> call, Throwable t) {

                Toast.makeText(MiProgresoActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

                Intent startAppIntent = new Intent(MiProgresoActivity.this, MenuPrincipalActivity.class);
                startActivity(startAppIntent);

                Log.e("Error log", errorServerMessage + t.getMessage());

            }
        });
    }

    private List<String> getCategorias(List<SearchTareaDiariaResult> listaTareas) {

        Set<String> categoriasSet = new HashSet<>();

        for (SearchTareaDiariaResult tarea : listaTareas) {
                categoriasSet.add(tarea.getCategoria().getNombre());
        }

        return new ArrayList<>(categoriasSet);
    }

    private void tareasCompletadasHoy(List<SearchTareaDiariaResult> listaTareas) {

        List<SearchTareaDiariaResult> tareasCompletadasHoy = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String fechaActual = sdf.format(new Date());

        for (SearchTareaDiariaResult tarea : listaTareas) {
            if (tarea.getTerminada() == 1 && tarea.getUpdatedAt().startsWith(fechaActual)) {
                tareasCompletadasHoy.add(tarea);
            }
        }

        TextView textViewProgress = findViewById(R.id.textViewProgress);
        textViewProgress.setText("¡Hoy has completado " + tareasCompletadasHoy.size() + " tareas!");

    }

    private void error(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

        Intent startAppIntent = new Intent(this, MiProgresoActivity.class);
        startActivity(startAppIntent);
    }
}