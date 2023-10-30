package com.uclm.louise.ediaries.enums;

public enum Prioridad {
    ALTA("Alta"),
    MEDIA("Media"),
    BAJA("Baja");

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String descripcion;

    Prioridad(String descripcion) {
        this.descripcion = descripcion;
    }
}
