package com.uclm.louise.ediaries.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.uclm.louise.ediaries.RegistroService;
import com.uclm.louise.ediaries.activity.LoadingActivity;
import com.uclm.louise.ediaries.activity.MenuPrincipalActivity;
import com.uclm.louise.ediaries.activity.StartAppActivity;
import com.uclm.louise.ediaries.data.clients.RegistroClient;
import com.uclm.louise.ediaries.data.models.*;
import com.uclm.louise.ediaries.data.requests.*;
import com.uclm.louise.ediaries.data.responses.CreateUsuarioResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroManager {
    private RegistroService registroService;
    private String errorServerMessage = "Error en la llamada al servidor: ";
    private String errorRegisterMessage = "Error en el registro: ";
    private RegistroContext registroContext = RegistroContext.getInstance();

    // Se realiza el registro completo, haciendo las llamadas de cada pantalla
    // Usuario -> Datos personales -> Datos escolares -> Actividades favoritas -> Datos clinicos

    public void registrarUsuario(Context context){

        registroService = RegistroClient.getRegistroService(context);
        CreateUsuarioRequest usuarioRequest = registroContext.getUsuarioRequest();

        registroService.createUsuario(usuarioRequest).enqueue(new Callback<CreateUsuarioResult>() {
            @Override
            public void onResponse(Call<CreateUsuarioResult> call, Response<CreateUsuarioResult> response) {
                if(response.code() == 200){
                    Integer childId = response.body().getChildId();
                    registrarDPersonales(childId, context);
                } else {
                    error(context);
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<CreateUsuarioResult> call, Throwable t) {
                error(context);
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarDPersonales(Integer childId, Context context) {
        CreateDPersonalesRequest dPersonalesRequest = registroContext.getdPersonalesRequest();
        dPersonalesRequest.setChildId(childId);

        registroService.createDPersonales(dPersonalesRequest).enqueue(new Callback<DPersonales>() {
            @Override
            public void onResponse(Call<DPersonales> call, Response<DPersonales> response) {
                if(response.code() == 200){
                    registrarDEscolares(childId, context);
                } else {
                    error(context);
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<DPersonales> call, Throwable t) {
                error(context);
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarDEscolares(Integer childId, Context context) {
        CreateDEscolaresRequest dEscolaresRequest = registroContext.getdEscolaresRequest();
        dEscolaresRequest.setChildId(childId);

        registroService.createDEscolares(dEscolaresRequest).enqueue(new Callback<DEscolares>() {
            @Override
            public void onResponse(Call<DEscolares> call, Response<DEscolares> response) {
                if(response.code() == 200){
                    registrarActividadFavorita(childId, context);
                } else {
                    error(context);
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<DEscolares> call, Throwable t) {
                error(context);
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarActividadFavorita(Integer childId, Context context) {

        CreateActividadesFavoritasRequest actividadesFavoritasRequest = registroContext.getActividadesFavoritasRequest();
        actividadesFavoritasRequest.setChildId(childId);

        registroService.createActividadesFavoritas(actividadesFavoritasRequest).enqueue(new Callback<Response<Void>>() {
            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if(response.code() == 204){
                    registrarDClinicos(childId, context);
                } else {
                    error(context);
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                error(context);
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarDClinicos(Integer childId, Context context){
        CreateDClinicosRequest dClinicosRequest = registroContext.getdClinicosRequest();
        dClinicosRequest.setChildId(childId);

        registroService.createDClinicos(dClinicosRequest).enqueue(new Callback<DClinicos>() {
            @Override
            public void onResponse(Call<DClinicos> call, Response<DClinicos> response) {
                if(response.code() == 200){
                    Intent menuPrincipalIntent = new Intent(context, MenuPrincipalActivity.class);
                    context.startActivity(menuPrincipalIntent);

                } else {
                    error(context);
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<DClinicos> call, Throwable t) {
                error(context);
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });

    }

    void error(Context context){
        Toast.makeText(context, "Ha ocurrido un error durante el registro", Toast.LENGTH_SHORT).show();

        Intent startAppIntent = new Intent(context, StartAppActivity.class);
        context.startActivity(startAppIntent);
    }
}

