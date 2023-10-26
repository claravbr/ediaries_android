package com.uclm.louise.ediaries.data.responses;

import com.google.gson.annotations.SerializedName;
import com.uclm.louise.ediaries.data.models.Usuario;

public class LoginResponse {

    @SerializedName("token")
    private String token;

    @SerializedName("usuario")
    private Usuario usuario;

    @SerializedName("child_id")
    private Integer childId;

    public LoginResponse(String token, Usuario usuario, Integer child_id) {
        this.token = token;
        this.usuario = usuario;
        this.childId = child_id;
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

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }
}
