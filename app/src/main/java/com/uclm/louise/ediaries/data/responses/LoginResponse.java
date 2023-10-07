package com.uclm.louise.ediaries.data.responses;

import com.google.gson.annotations.SerializedName;
import com.uclm.louise.ediaries.data.models.Usuario;

public class LoginResponse {
    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("auth_token")
    private String authToken;

    @SerializedName("usuario")
    private Usuario usuario;

    public LoginResponse(int statusCode, String authToken, Usuario usuario) {
        this.statusCode = statusCode;
        this.authToken = authToken;
        this.usuario = usuario;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
