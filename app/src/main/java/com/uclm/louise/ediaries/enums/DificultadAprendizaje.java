package com.uclm.louise.ediaries.enums;

public enum DificultadAprendizaje {
    Dislexia("Dislexia"),
    Discalculia("Discalculia"),
    Disgrafia("Disgrafía"),
    TranstornoCoordinacionMovimiento("Transtorno de Coordinación del Movimiento");

    private final String descripcion;

    DificultadAprendizaje(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
