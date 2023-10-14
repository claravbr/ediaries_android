package com.uclm.louise.ediaries.data.models;

import com.google.gson.annotations.SerializedName;

public class DEscolares {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("nivelAcademico")
    private String nivelAcademico;
    @SerializedName("centroEducativo")
    private String centroEducativo;
    @SerializedName("repetidor")
    private Boolean repetidor;
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
    public DEscolares() {
    }

    /**
     *
     * @param createdAt
     * @param id
     * @param childId
     * @param nivelAcademico
     * @param repetidor
     * @param centroEducativo
     * @param updatedAt
     */
    public DEscolares(Integer childId, String nivelAcademico, String centroEducativo, Boolean repetidor, String updatedAt, String createdAt, Integer id) {
        super();
        this.childId = childId;
        this.nivelAcademico = nivelAcademico;
        this.centroEducativo = centroEducativo;
        this.repetidor = repetidor;
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

    public DEscolares withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public DEscolares withNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
        return this;
    }

    public String getCentroEducativo() {
        return centroEducativo;
    }

    public void setCentroEducativo(String centroEducativo) {
        this.centroEducativo = centroEducativo;
    }

    public DEscolares withCentroEducativo(String centroEducativo) {
        this.centroEducativo = centroEducativo;
        return this;
    }

    public Boolean getRepetidor() {
        return repetidor;
    }

    public void setRepetidor(Boolean repetidor) {
        this.repetidor = repetidor;
    }

    public DEscolares withRepetidor(Boolean repetidor) {
        this.repetidor = repetidor;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DEscolares withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public DEscolares withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DEscolares withId(Integer id) {
        this.id = id;
        return this;
    }

}
