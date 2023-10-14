package com.uclm.louise.ediaries.data.models;

import com.google.gson.annotations.SerializedName;

public class DClinicos {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("enfermedad")
    private String enfermedad;
    @SerializedName("tdah")
    private Boolean tdah;
    @SerializedName("tdahTipo")
    private String tdahTipo;
    @SerializedName("tdahEdad")
    private Integer tdahEdad;
    @SerializedName("dificultad")
    private String dificultad;
    @SerializedName("medicacion")
    private Boolean medicacion;
    @SerializedName("medicacionAntiguedad")
    private String medicacionAntiguedad;
    @SerializedName("medicacionInfo")
    private String medicacionInfo;
    @SerializedName("intervencion")
    private Boolean intervencion;
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
    public DClinicos() {
    }

    /**
     *
     * @param tdah
     * @param enfermedad
     * @param medicacionInfo
     * @param childId
     * @param intervencion
     * @param tdahEdad
     * @param createdAt
     * @param tdahTipo
     * @param medicacionAntiguedad
     * @param id
     * @param dificultad
     * @param medicacion
     * @param updatedAt
     */
    public DClinicos(Integer childId, String enfermedad, Boolean tdah, String tdahTipo, Integer tdahEdad, String dificultad, Boolean medicacion, String medicacionAntiguedad, String medicacionInfo, Boolean intervencion, String updatedAt, String createdAt, Integer id) {
        super();
        this.childId = childId;
        this.enfermedad = enfermedad;
        this.tdah = tdah;
        this.tdahTipo = tdahTipo;
        this.tdahEdad = tdahEdad;
        this.dificultad = dificultad;
        this.medicacion = medicacion;
        this.medicacionAntiguedad = medicacionAntiguedad;
        this.medicacionInfo = medicacionInfo;
        this.intervencion = intervencion;
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

    public DClinicos withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public DClinicos withEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
        return this;
    }

    public Boolean getTdah() {
        return tdah;
    }

    public void setTdah(Boolean tdah) {
        this.tdah = tdah;
    }

    public DClinicos withTdah(Boolean tdah) {
        this.tdah = tdah;
        return this;
    }

    public String getTdahTipo() {
        return tdahTipo;
    }

    public void setTdahTipo(String tdahTipo) {
        this.tdahTipo = tdahTipo;
    }

    public DClinicos withTdahTipo(String tdahTipo) {
        this.tdahTipo = tdahTipo;
        return this;
    }

    public Integer getTdahEdad() {
        return tdahEdad;
    }

    public void setTdahEdad(Integer tdahEdad) {
        this.tdahEdad = tdahEdad;
    }

    public DClinicos withTdahEdad(Integer tdahEdad) {
        this.tdahEdad = tdahEdad;
        return this;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public DClinicos withDificultad(String dificultad) {
        this.dificultad = dificultad;
        return this;
    }

    public Boolean getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(Boolean medicacion) {
        this.medicacion = medicacion;
    }

    public DClinicos withMedicacion(Boolean medicacion) {
        this.medicacion = medicacion;
        return this;
    }

    public String getMedicacionAntiguedad() {
        return medicacionAntiguedad;
    }

    public void setMedicacionAntiguedad(String medicacionAntiguedad) {
        this.medicacionAntiguedad = medicacionAntiguedad;
    }

    public DClinicos withMedicacionAntiguedad(String medicacionAntiguedad) {
        this.medicacionAntiguedad = medicacionAntiguedad;
        return this;
    }

    public String getMedicacionInfo() {
        return medicacionInfo;
    }

    public void setMedicacionInfo(String medicacionInfo) {
        this.medicacionInfo = medicacionInfo;
    }

    public DClinicos withMedicacionInfo(String medicacionInfo) {
        this.medicacionInfo = medicacionInfo;
        return this;
    }

    public Boolean getIntervencion() {
        return intervencion;
    }

    public void setIntervencion(Boolean intervencion) {
        this.intervencion = intervencion;
    }

    public DClinicos withIntervencion(Boolean intervencion) {
        this.intervencion = intervencion;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DClinicos withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public DClinicos withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DClinicos withId(Integer id) {
        this.id = id;
        return this;
    }

}
