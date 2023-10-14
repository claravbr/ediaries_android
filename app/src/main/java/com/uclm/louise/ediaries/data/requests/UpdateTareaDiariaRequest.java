package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.SerializedName;

public class UpdateTareaDiariaRequest {

    @SerializedName("categoria_id")
    private Integer categoriaId;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("fechaLimite")
    private String fechaLimite;
    @SerializedName("prioridad")
    private String prioridad;
    @SerializedName("duracion")
    private Integer duracion;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateTareaDiariaRequest() {
    }

    /**
     *
     * @param fechaLimite
     * @param duracion
     * @param nombre
     * @param prioridad
     * @param categoriaId
     */
    public UpdateTareaDiariaRequest(Integer categoriaId, String nombre, String fechaLimite, String prioridad, Integer duracion) {
        super();
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.duracion = duracion;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public UpdateTareaDiariaRequest withCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UpdateTareaDiariaRequest withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public UpdateTareaDiariaRequest withFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
        return this;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public UpdateTareaDiariaRequest withPrioridad(String prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public UpdateTareaDiariaRequest withDuracion(Integer duracion) {
        this.duracion = duracion;
        return this;
    }

}