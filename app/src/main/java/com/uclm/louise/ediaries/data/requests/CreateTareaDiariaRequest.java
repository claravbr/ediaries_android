package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.SerializedName;


public class CreateTareaDiariaRequest {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("categoria_id")
    private Integer categoriaId;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("fechaIntroduccion")
    private String fechaIntroduccion;
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
    public CreateTareaDiariaRequest() {
    }

    /**
     *
     * @param fechaIntroduccion
     * @param fechaLimite
     * @param duracion
     * @param childId
     * @param nombre
     * @param prioridad
     * @param categoriaId
     */
    public CreateTareaDiariaRequest(Integer childId, Integer categoriaId, String nombre, String fechaIntroduccion, String fechaLimite, String prioridad, Integer duracion) {
        super();
        this.childId = childId;
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.fechaIntroduccion = fechaIntroduccion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.duracion = duracion;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public CreateTareaDiariaRequest withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public CreateTareaDiariaRequest withCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CreateTareaDiariaRequest withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Object getFechaIntroduccion() {
        return fechaIntroduccion;
    }

    public void setFechaIntroduccion(String fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
    }

    public CreateTareaDiariaRequest withFechaIntroduccion(String fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
        return this;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public CreateTareaDiariaRequest withFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
        return this;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public CreateTareaDiariaRequest withPrioridad(String prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public CreateTareaDiariaRequest withDuracion(Integer duracion) {
        this.duracion = duracion;
        return this;
    }

}