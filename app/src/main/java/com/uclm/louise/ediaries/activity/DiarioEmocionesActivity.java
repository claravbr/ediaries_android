package com.uclm.louise.ediaries.activity;

import static com.uclm.louise.ediaries.enums.Emocion.Cansado;
import static com.uclm.louise.ediaries.enums.Emocion.Contento;
import static com.uclm.louise.ediaries.enums.Emocion.Emocionado;
import static com.uclm.louise.ediaries.enums.Emocion.Enfadado;
import static com.uclm.louise.ediaries.enums.Emocion.Triste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.uclm.louise.ediaries.ApiService;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.clients.ApiClient;
import com.uclm.louise.ediaries.data.models.DiarioEmociones;
import com.uclm.louise.ediaries.data.requests.CreateDiarioEmocionesRequest;
import com.uclm.louise.ediaries.utils.Session;
import com.uclm.louise.ediaries.utils.SessionManager;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiarioEmocionesActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    Button buttonSave;

    private String emocion = "";
    private final String errorServerMessage = "Error en la llamada al servidor: ";
    private final String errorRegisterMessage = "Error en el registro: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diario_emociones);

        setDate();

        final View frameLayoutEnfadado = findViewById(R.id.frameLayoutEnfadado);
        final View frameLayoutTriste = findViewById(R.id.frameLayoutTriste);
        final View frameLayoutEmocionado = findViewById(R.id.frameLayoutEmocionado);
        final View frameLayoutContento = findViewById(R.id.frameLayoutContento);
        final View frameLayoutCansado = findViewById(R.id.frameLayoutCansado);

        frameLayoutEnfadado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emocion = String.valueOf(Enfadado);
                frameLayoutEnfadado.setBackgroundResource(R.drawable.circular_selector_red);

                frameLayoutTriste.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutEmocionado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutContento.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutCansado.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        frameLayoutTriste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emocion = String.valueOf(Triste);
                frameLayoutTriste.setBackgroundResource(R.drawable.circular_selector_blue);

                frameLayoutEnfadado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutEmocionado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutContento.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutCansado.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        frameLayoutEmocionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emocion = String.valueOf(Emocionado);
                frameLayoutEmocionado.setBackgroundResource(R.drawable.circular_selector_yellow);

                frameLayoutEnfadado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutTriste.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutContento.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutCansado.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        frameLayoutContento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emocion = String.valueOf(Contento);
                frameLayoutContento.setBackgroundResource(R.drawable.circular_selector_green);

                frameLayoutEnfadado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutEmocionado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutTriste.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutCansado.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        frameLayoutCansado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emocion = String.valueOf(Cansado);
                frameLayoutCansado.setBackgroundResource(R.drawable.circular_selector_orange);

                frameLayoutEnfadado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutEmocionado.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutContento.setBackgroundColor(Color.TRANSPARENT);
                frameLayoutTriste.setBackgroundColor(Color.TRANSPARENT);
            }
        });



        // <-- GUARDAR -->
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });


        // <-- VOLVER AL MENU PRINCIPAL -->
        setSupportActionBar(topAppBar);
        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiarioEmocionesActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                Log.i("Info log", "Vuelta al menu principal");
            }
        });
    }

    private void nextStep() {
        TextInputEditText editTextMotivo = findViewById(R.id.editTextMotivo);

        // Obtener los datos introducidos
        String motivo = editTextMotivo.getText().toString();

        if(validFields(editTextMotivo)){

            Intent intent = new Intent(DiarioEmocionesActivity.this, LoadingActivity.class);
            startActivity(intent);

            Session session = Session.getInstance();

            CreateDiarioEmocionesRequest diarioEmocionesRequest = new CreateDiarioEmocionesRequest(session.getChildId(), emocion, motivo);
            saveEntradaDiario(diarioEmocionesRequest);

        }
    }

    private void saveEntradaDiario(CreateDiarioEmocionesRequest diarioEmocionesRequest) {
        SessionManager sessionManager = new SessionManager(this);

        ApiService service = ApiClient.getApiService(this);
        service.createDiarioEmociones("Bearer " + sessionManager.fetchAuthToken(), diarioEmocionesRequest).enqueue(new Callback<DiarioEmociones>() {
            @Override
            public void onResponse(Call<DiarioEmociones> call, Response<DiarioEmociones> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(DiarioEmocionesActivity.this, MenuPrincipalActivity.class);
                    startActivity(intent);
                } else {
                    error();
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<DiarioEmociones> call, Throwable t) {
                error();
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });

    }

    private boolean validFields(TextInputEditText editTextMotivo) {
        if (editTextMotivo.getText().toString().isEmpty()) {
            editTextMotivo.setError("Describe brevemente por qué te has sentido así");
            editTextMotivo.requestFocus();
            return false;
        } else if (emocion.isEmpty()){
            editTextMotivo.setError("Selecciona una emoción");
            editTextMotivo.requestFocus();
            return false;
        }

        return true;
    }

    private void setDate() {
        Month mes = LocalDate.now().getMonth();
        String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        TextView textViewDate = findViewById(R.id.textViewDate);

        textViewDate.setText("Fecha: " + LocalDate.now().getDayOfMonth() + " de " + nombreMes + " de " + LocalDate.now().getYear());
    }

    private void error(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

        Intent startAppIntent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(startAppIntent);
    }
}