package com.uclm.louise.ediaries.utils;

import android.content.Context;
import android.util.Log;

import com.uclm.louise.ediaries.RegistroService;
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
                    registrarDPersonales(childId);
                } else {
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<CreateUsuarioResult> call, Throwable t) {
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarDPersonales(Integer childId) {
        CreateDPersonalesRequest dPersonalesRequest = registroContext.getdPersonalesRequest();
        dPersonalesRequest.setChildId(childId);

        registroService.createDPersonales(dPersonalesRequest).enqueue(new Callback<DPersonales>() {
            @Override
            public void onResponse(Call<DPersonales> call, Response<DPersonales> response) {
                if(response.code() == 200){
                    registrarDEscolares(childId);
                } else {
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<DPersonales> call, Throwable t) {
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarDEscolares(Integer childId) {
        CreateDEscolaresRequest dEscolaresRequest = registroContext.getdEscolaresRequest();
        dEscolaresRequest.setChildId(childId);

        registroService.createDEscolares(dEscolaresRequest).enqueue(new Callback<DEscolares>() {
            @Override
            public void onResponse(Call<DEscolares> call, Response<DEscolares> response) {
                if(response.code() == 200){
                    registrarActividadFavorita(childId);
                } else {
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<DEscolares> call, Throwable t) {
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarActividadFavorita(Integer childId) {

        CreateActividadesFavoritasRequest actividadesFavoritasRequest = registroContext.getActividadesFavoritasRequest();
        actividadesFavoritasRequest.setChildId(childId);

        registroService.createActividadesFavoritas(actividadesFavoritasRequest).enqueue(new Callback<Response<Void>>() {
            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if(response.code() == 204){
                    registrarDClinicos(childId);
                } else {
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });
    }

    private void registrarDClinicos(Integer childId){
        CreateDClinicosRequest dClinicosRequest = registroContext.getdClinicosRequest();
        dClinicosRequest.setChildId(childId);

        registroService.createDClinicos(dClinicosRequest).enqueue(new Callback<DClinicos>() {
            @Override
            public void onResponse(Call<DClinicos> call, Response<DClinicos> response) {
                if(response.code() == 200){

                } else {
                    Log.e("Error log", errorRegisterMessage + response.code());
                }
            }

            @Override
            public void onFailure(Call<DClinicos> call, Throwable t) {
                Log.e("Error log", errorServerMessage + t.getMessage());
            }
        });

    }
}

