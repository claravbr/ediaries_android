package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.SerializedName;

public class CreateDEscolaresRequest {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("nivelAcademico")
    private String nivelAcademico;
    @SerializedName("centroEducativo")
    private String centroEducativo;
    @SerializedName("repetidor")
    private Boolean repetidor;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateDEscolaresRequest() {
    }

    /**
     *
     * @param childId
     * @param nivelAcademico
     * @param repetidor
     * @param centroEducativo
     */
    public CreateDEscolaresRequest(Integer childId, String nivelAcademico, String centroEducativo, Boolean repetidor) {
        super();
        this.childId = childId;
        this.nivelAcademico = nivelAcademico;
        this.centroEducativo = centroEducativo;
        this.repetidor = repetidor;
    }

    public CreateDEscolaresRequest(String nivelAcademico, String centroEducativo, Boolean repetidor) {
        this.nivelAcademico = nivelAcademico;
        this.centroEducativo = centroEducativo;
        this.repetidor = repetidor;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public CreateDEscolaresRequest withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public CreateDEscolaresRequest withNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
        return this;
    }

    public String getCentroEducativo() {
        return centroEducativo;
    }

    public void setCentroEducativo(String centroEducativo) {
        this.centroEducativo = centroEducativo;
    }

    public CreateDEscolaresRequest withCentroEducativo(String centroEducativo) {
        this.centroEducativo = centroEducativo;
        return this;
    }

    public Boolean getRepetidor() {
        return repetidor;
    }

    public void setRepetidor(Boolean repetidor) {
        this.repetidor = repetidor;
    }

    public CreateDEscolaresRequest withRepetidor(Boolean repetidor) {
        this.repetidor = repetidor;
        return this;
    }

}