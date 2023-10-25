package com.uclm.louise.ediaries.data.responses;

import com.google.gson.annotations.SerializedName;
import com.uclm.louise.ediaries.data.models.Usuario;

public class CreateUsuarioResult {

    @SerializedName("usuario")
    private Usuario usuario;
    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateUsuarioResult() {
    }

    /**
     *
     * @param usuario
     * @param childId
     */
    public CreateUsuarioResult(Usuario usuario, Integer childId) {
        super();
        this.usuario = usuario;
        this.childId = childId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CreateUsuarioResult withUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public CreateUsuarioResult withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

}