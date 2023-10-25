package com.uclm.louise.ediaries.data.responses;

import com.google.gson.annotations.SerializedName;
import com.uclm.louise.ediaries.data.models.Usuario;

public class LoginResponse {

    @SerializedName("token")
    private String token;

    @SerializedName("usuario")
    private Usuario usuario;

    public LoginResponse(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
