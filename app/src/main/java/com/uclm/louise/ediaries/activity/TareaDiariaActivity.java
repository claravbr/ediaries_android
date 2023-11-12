package com.uclm.louise.ediaries.activity;

import static com.uclm.louise.ediaries.enums.ActivityActions.Completar;
import static com.uclm.louise.ediaries.enums.ActivityActions.Editar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.uclm.louise.ediaries.ApiService;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;
import com.uclm.louise.ediaries.enums.ActivityActions;
import com.uclm.louise.ediaries.utils.OnListItemClick;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;
import com.uclm.louise.ediaries.utils.TareaComparator;
import com.uclm.louise.ediaries.utils.TareasAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TareaDiariaActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;

    private final String errorServerMessage = "Error en la llamada al servidor: ";
    private final String errorRegisterMessage = "Error en el registro: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarea_diaria);

        // Cargar las tareas pendientes del usuario, si tiene
        getTareasChild();

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

        // NUEVA TAREA
        ExtendedFloatingActionButton fab = findViewById(R.id.extended_fab);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(TareaDiariaActivity.this, NuevaTareaDiariaActivity.class);
            startActivity(intent);
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
                if(response.isSuccessful()){

                    RecyclerView recyclerView = findViewById(R.id.recyclerViewTareas);
                    TextView textSinTareas = findViewById(R.id.textSinTareas);

                    if(!response.body().isEmpty()){

                        List<SearchTareaDiariaResult> tareasTerminadas = new ArrayList<>();

                        for(SearchTareaDiariaResult tarea : response.body()){
                            if(tarea.getTerminada() == 0){
                                tareasTerminadas.add(tarea);
                            }
                        }

                        // Se ordena la lista para enseñar las tareas de mayor prioridad arriba
                        Collections.sort(tareasTerminadas, new TareaComparator());

                        TareasAdapter adapter = new TareasAdapter(tareasTerminadas);
                        LinearLayoutManager linearLayout = new LinearLayoutManager(TareaDiariaActivity.this);

                        OnListItemClick onListItemClick = new OnListItemClick() {
                            @Override
                            public void onClick(View view, int position, SearchTareaDiariaResult tarea, ActivityActions action) {

                                Log.i("Info log", "Acción " + action + " seleccionada para la tarea: " + tarea.getNombre());

                                if(action == Completar){

                                    setTareaAsDone(tarea.getId());
                                    Intent intentLoading = new Intent(TareaDiariaActivity.this, LoadingActivity.class);
                                    startActivity(intentLoading);
                                } else if (action == Editar){

                                    Intent intent = new Intent(TareaDiariaActivity.this, NuevaTareaDiariaActivity.class);
                                    intent.putExtra("tarea", tarea);
                                    startActivity(intent);

                                }

                            }
                        };

                        adapter.setClickListener(onListItemClick);

                        recyclerView.setLayoutManager(linearLayout);
                        recyclerView.setAdapter(adapter);

                        recyclerView.setVisibility(View.VISIBLE);
                        textSinTareas.setVisibility(View.GONE);

                    } else {
                        recyclerView.setVisibility(View.GONE);
                        textSinTareas.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(TareaDiariaActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

                    Intent startAppIntent = new Intent(TareaDiariaActivity.this, MenuPrincipalActivity.class);
                    startActivity(startAppIntent);

                    Log.e("Error log", errorRegisterMessage + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<SearchTareaDiariaResult>> call, Throwable t) {

                Toast.makeText(TareaDiariaActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

                Intent startAppIntent = new Intent(TareaDiariaActivity.this, MenuPrincipalActivity.class);
                startActivity(startAppIntent);

                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void setTareaAsDone(int idTarea) {
        SessionManager sessionManager = new SessionManager(this);

        ApiService service = ApiClient.getApiService(this);

        service.setTareaDiariaTerminada("Bearer " + sessionManager.fetchAuthToken(), idTarea).enqueue(new Callback<Response<Void>>() {
            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if(response.isSuccessful()){
                    //Refresh la pagina
                    Intent intent = new Intent(TareaDiariaActivity.this, TareaDiariaActivity.class);
                    startActivity(intent);
                } else {
                    error();
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                error();
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void error(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

        Intent startAppIntent = new Intent(this, TareaDiariaActivity.class);
        startActivity(startAppIntent);
    }
}

