package com.uclm.louise.ediaries.data.models;

import com.google.gson.annotations.SerializedName;

public class DiarioEmociones {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("emocion")
    private String emocion;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public DiarioEmociones() {
    }

    /**
     *
     * @param descripcion
     * @param fecha
     * @param createdAt
     * @param id
     * @param childId
     * @param emocion
     * @param updatedAt
     */
    public DiarioEmociones(Integer childId, String fecha, String emocion, String descripcion, String updatedAt, String createdAt, Integer id) {
        super();
        this.childId = childId;
        this.fecha = fecha;
        this.emocion = emocion;
        this.descripcion = descripcion;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public DiarioEmociones withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public DiarioEmociones withFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public DiarioEmociones withEmocion(String emocion) {
        this.emocion = emocion;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DiarioEmociones withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DiarioEmociones withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public DiarioEmociones withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DiarioEmociones withId(Integer id) {
        this.id = id;
        return this;
    }

}