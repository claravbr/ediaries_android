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
import com.uclm.louise.ediaries.utils.DatePickerFragment;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;

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

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevaTareaDiariaActivity.this, TareaDiariaActivity.class);
                startActivity(intent);
            }
        });

        // GUARDAR LA TAREA
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
                Intent intent = new Intent(NuevaTareaDiariaActivity.this, LoadingActivity.class);
                startActivity(intent);
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
            Session session = Session.getInstance();

            CreateTareaDiariaRequest tareaDiariaRequest = new CreateTareaDiariaRequest(session.getChildId(), getCategoriaId(categoria), nombreTarea, fechaActual, fechaLimite, prioridad, duracion);
            saveTarea(tareaDiariaRequest);
        }
    }

    private Integer getCategoriaId(String categoria) {
        return categoriaIdMap.get(categoria);
    }

    private void saveTarea(CreateTareaDiariaRequest tareaDiariaRequest) {
        SessionManager sessionManager = new SessionManager(this);

        ApiService service = ApiClient.getApiService(this);
        service.createTareaDiaria("Bearer " + sessionManager.fetchAuthToken(), tareaDiariaRequest).enqueue(new Callback<TareaDiaria>() {
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