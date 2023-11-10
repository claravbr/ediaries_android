package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uclm.louise.ediaries.ApiService;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.models.TareaDiaria;
import com.uclm.louise.ediaries.data.requests.CreateTareaDiariaRequest;
import com.uclm.louise.ediaries.data.requests.UpdateTareaDiariaRequest;
import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;
import com.uclm.louise.ediaries.utils.DatePickerFragment;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevaTareaDiariaActivity extends AppCompatActivity {

    private static final Map<String, Integer> categoriaIdMap = new HashMap<>();

    static {
        categoriaIdMap.put("Deberes", 1);
        categoriaIdMap.put("Casa", 2);
        categoriaIdMap.put("Amigos", 3);
        categoriaIdMap.put("Extraescolares", 4);
        categoriaIdMap.put("Deportes", 5);
    }

    private MaterialToolbar topAppBar;
    private Button buttonSave;
    private Button buttonDelete;
    TextInputEditText editTextFechaLimite;
    private final String errorServerMessage = "Error en la llamada al servidor: ";
    private final String errorRegisterMessage = "Error en el registro: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea_diaria);

        // -- VOLVER A LISTA DE TAREAS DIARIAS --
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setVisibility(View.GONE);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevaTareaDiariaActivity.this, TareaDiariaActivity.class);
                startActivity(intent);
            }
        });

        // SI ES UN UPDATE, RELLENA EL FORMULARIO CON LOS DATOS ACTUALES
        SearchTareaDiariaResult tarea = (SearchTareaDiariaResult) getIntent().getSerializableExtra("tarea");

        if(tarea != null){
           fillInfoTarea(tarea);
        }

        // BORRAR LA TAREA
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTarea(tarea.getId());
            }
        });


        // GUARDAR LA TAREA
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });

        // LISTENER QUE MUESTRA UN CALENDARIO
        editTextFechaLimite = findViewById(R.id.editTextFechaLimite);
        editTextFechaLimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment(editTextFechaLimite);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

    }

    private void deleteTarea(Integer idTarea) {
        SessionManager sessionManager = new SessionManager(this);

        ApiService service  = ApiClient.getApiService(this);
        service.deleteTareaDiaria("Bearer " + sessionManager.fetchAuthToken(), idTarea).enqueue(new Callback<Response<Void>>() {
            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(NuevaTareaDiariaActivity.this, TareaDiariaActivity.class);
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

    private void fillInfoTarea(SearchTareaDiariaResult tarea) {

        // Muestra el botón para dar la opción a borrar la tarea
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setVisibility(View.VISIBLE);

        editTextFechaLimite = findViewById(R.id.editTextFechaLimite);
        TextInputEditText editTextNombreTarea = findViewById(R.id.editTextNombreTarea);

        // Se rellena el formulario con la información actual de la tarea
        editTextNombreTarea.setText(tarea.getNombre());
        editTextFechaLimite.setText(formatDate(tarea.getFechaLimite()));

    }

    private String formatDate(String fechaLimite) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = inputFormat.parse(fechaLimite);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void nextStep() {
        editTextFechaLimite = findViewById(R.id.editTextFechaLimite);
        TextInputEditText editTextNombreTarea = findViewById(R.id.editTextNombreTarea);
        TextInputEditText editTextDuracion = findViewById(R.id.editTextDuracion);
        TextInputLayout textInputLayoutCategoriaTarea = findViewById(R.id.menuCategoria);
        TextInputLayout textInputLayoutPrioridadTarea = findViewById(R.id.menuPrioridad);

        // Obtener los datos introducidos en el formulario
        String nombreTarea = editTextNombreTarea.getText().toString();
        String fechaLimite = editTextFechaLimite.getText().toString();
        String categoria = textInputLayoutCategoriaTarea.getEditText().getText().toString();

        Integer duracion = null;
        if(!editTextDuracion.getText().toString().isEmpty()){
            duracion = Integer.parseInt(editTextDuracion.getText().toString().trim());
        }

        String prioridad = textInputLayoutPrioridadTarea.getEditText().getText().toString();

        Date fechaActualDate = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = formatoFecha.format(fechaActualDate);

        // Comprobar si se ha quedado algun campo obligatorio sin rellenar
        if(validFields(textInputLayoutCategoriaTarea, textInputLayoutPrioridadTarea, editTextNombreTarea, editTextFechaLimite, editTextDuracion)){

            Intent intent = new Intent(NuevaTareaDiariaActivity.this, LoadingActivity.class);
            startActivity(intent);

            Session session = Session.getInstance();

            // Creación o actualización
            SearchTareaDiariaResult tarea = (SearchTareaDiariaResult) getIntent().getSerializableExtra("tarea");

            if(tarea != null){
                //Actualizacion
                UpdateTareaDiariaRequest updateTareaDiariaRequest = new UpdateTareaDiariaRequest(getCategoriaId(categoria), nombreTarea, fechaLimite, prioridad, duracion);
                updateTarea(updateTareaDiariaRequest, tarea.getId());

            } else {
                // Creación
                CreateTareaDiariaRequest createTareaDiariaRequest = new CreateTareaDiariaRequest(session.getChildId(), getCategoriaId(categoria), nombreTarea, fechaActual, fechaLimite, prioridad, duracion);
                saveTarea(createTareaDiariaRequest);

            }
        }
    }

    private void updateTarea(UpdateTareaDiariaRequest updateTareaDiariaRequest, Integer tareaId) {
        SessionManager sessionManager = new SessionManager(this);

        ApiService service  = ApiClient.getApiService(this);
        service.updateTareaDiaria("Bearer " + sessionManager.fetchAuthToken(), updateTareaDiariaRequest, tareaId).enqueue(new Callback<TareaDiaria>() {
            @Override
            public void onResponse(Call<TareaDiaria> call, Response<TareaDiaria> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(NuevaTareaDiariaActivity.this, TareaDiariaActivity.class);
                    startActivity(intent);
                } else {
                    error();
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<TareaDiaria> call, Throwable t) {
                error();
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });

    }

    private Integer getCategoriaId(String categoria) {
        return categoriaIdMap.get(categoria);
    }

    private void saveTarea(CreateTareaDiariaRequest createTareaDiariaRequest) {
        SessionManager sessionManager = new SessionManager(this);

        ApiService service = ApiClient.getApiService(this);
        service.createTareaDiaria("Bearer " + sessionManager.fetchAuthToken(), createTareaDiariaRequest).enqueue(new Callback<TareaDiaria>() {
            @Override
            public void onResponse(Call<TareaDiaria> call, Response<TareaDiaria> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(NuevaTareaDiariaActivity.this, TareaDiariaActivity.class);
                    startActivity(intent);
                } else {
                    error();
                    Log.e("Error log", errorRegisterMessage + response.code());
                }

            }

            @Override
            public void onFailure(Call<TareaDiaria> call, Throwable t) {
                error();
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private boolean validFields(TextInputLayout textInputLayoutCategoriaTarea, TextInputLayout textInputLayoutPrioridadTarea, TextInputEditText... editTexts) {
        String messageError = "Este campo no puede estar vacío";

        for (TextInputEditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                editText.setError(messageError);
                editText.requestFocus();
                return false;
            }
        }

        if (textInputLayoutCategoriaTarea.getEditText().getText().toString().isEmpty()){
            textInputLayoutCategoriaTarea.requestFocus();
            return false;
        } else if(textInputLayoutPrioridadTarea.getEditText().getText().toString().isEmpty()){
            textInputLayoutPrioridadTarea.requestFocus();
            return false;
        }

        return true;
    }

    private void error(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

        Intent startAppIntent = new Intent(this, TareaDiariaActivity.class);
        startActivity(startAppIntent);
    }
}