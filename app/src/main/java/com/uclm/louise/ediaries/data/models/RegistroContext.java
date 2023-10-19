package com.uclm.louise.ediaries.data.models;

import com.uclm.louise.ediaries.data.requests.CreateDClinicosRequest;
import com.uclm.louise.ediaries.data.requests.CreateDEscolaresRequest;
import com.uclm.louise.ediaries.data.requests.CreateDPersonalesRequest;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;

import java.util.ArrayList;
import java.util.List;

public class RegistroContext {

    // SINGLETON
    private static RegistroContext instance;

    private CreateUsuarioRequest usuarioRequest;
    private CreateDPersonalesRequest dPersonalesRequest;
    private CreateDClinicosRequest dClinicosRequest;
    private CreateDEscolaresRequest dEscolaresRequest;
    private List<String> actividadFavorita;

    private RegistroContext() {
    }

    public static synchronized RegistroContext getInstance() {
        if (instance == null) {
            instance = new RegistroContext();
        }
        return instance;
    }

    public CreateUsuarioRequest getUsuarioRequest() {
        return usuarioRequest;
    }

    public void setUsuarioRequest(CreateUsuarioRequest usuarioRequest) {
        this.usuarioRequest = usuarioRequest;
    }

    public CreateDPersonalesRequest getdPersonalesRequest() {
        return dPersonalesRequest;
    }

    public void setdPersonalesRequest(CreateDPersonalesRequest dPersonalesRequest) {
        this.dPersonalesRequest = dPersonalesRequest;
    }

    public CreateDClinicosRequest getdClinicosRequest() {
        return dClinicosRequest;
    }

    public void setdClinicosRequest(CreateDClinicosRequest dClinicosRequest) {
        this.dClinicosRequest = dClinicosRequest;
    }

    public CreateDEscolaresRequest getdEscolaresRequest() {
        return dEscolaresRequest;
    }

    public void setdEscolaresRequest(CreateDEscolaresRequest dEscolaresRequest) {
        this.dEscolaresRequest = dEscolaresRequest;
    }

    public List<String> getActividadFavorita() {
        return actividadFavorita;
    }

    public void setActividadFavorita(List<String> actividadFavorita) {
        this.actividadFavorita = actividadFavorita;
    }
}
