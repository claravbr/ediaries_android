package com.uclm.louise.ediaries.data.responses;

import com.google.gson.annotations.SerializedName;
import com.uclm.louise.ediaries.data.models.Categoria;

public class SearchTareaDiariaResult {

    @SerializedName("id")
    private Integer id;
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
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("categoria")
    private Categoria categoria;

    /**
     * No args constructor for use in serialization
     */
    public SearchTareaDiariaResult() {
    }

    /**
     * @param createdAt
     * @param fechaIntroduccion
     * @param fechaLimite
     * @param categoria
     * @param duracion
     * @param id
     * @param childId
     * @param nombre
     * @param prioridad
     * @param categoriaId
     * @param updatedAt
     */
    public SearchTareaDiariaResult(Integer id, Integer childId, Integer categoriaId, String nombre, String fechaIntroduccion, String fechaLimite, String prioridad, Integer duracion, String createdAt, String updatedAt, Categoria categoria) {
        super();
        this.id = id;
        this.childId = childId;
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.fechaIntroduccion = fechaIntroduccion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.duracion = duracion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SearchTareaDiariaResult withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public SearchTareaDiariaResult withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public SearchTareaDiariaResult withCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SearchTareaDiariaResult withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getFechaIntroduccion() {
        return fechaIntroduccion;
    }

    public void setFechaIntroduccion(String fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
    }

    public SearchTareaDiariaResult withFechaIntroduccion(String fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
        return this;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public SearchTareaDiariaResult withFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
        return this;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public SearchTareaDiariaResult withPrioridad(String prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public SearchTareaDiariaResult withDuracion(Integer duracion) {
        this.duracion = duracion;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public SearchTareaDiariaResult withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SearchTareaDiariaResult withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SearchTareaDiariaResult withCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }
}