package com.uclm.louise.ediaries.utils;

import com.uclm.louise.ediaries.data.models.Usuario;

public class Session {

    // SINGLETON CON LA INFO DE LA SESION

    private static Session instance;

    private Usuario usuario;
    private Integer childId;

    public static synchronized Session getInstance(){
        if (instance == null){
            instance = new Session();
        }
        return instance;
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
