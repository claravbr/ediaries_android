package com.uclm.louise.ediaries.data.models;

import com.google.gson.annotations.SerializedName;

public class Categoria {

    @SerializedName("id")
    private Integer id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Categoria() {
    }

    /**
     *
     * @param descripcion
     * @param createdAt
     * @param id
     * @param nombre
     * @param updatedAt
     */
    public Categoria(Integer id, String nombre, String descripcion, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categoria withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Categoria withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Categoria withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
