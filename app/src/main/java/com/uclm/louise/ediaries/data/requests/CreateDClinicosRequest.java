package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.SerializedName;

public class CreateDClinicosRequest {

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

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateDClinicosRequest() {
    }

    public CreateDClinicosRequest(String enfermedad, Boolean tdah, String tdahTipo, Integer tdahEdad, String dificultad, Boolean medicacion, String medicacionAntiguedad, String medicacionInfo, Boolean intervencion) {
        this.enfermedad = enfermedad;
        this.tdah = tdah;
        this.tdahTipo = tdahTipo;
        this.tdahEdad = tdahEdad;
        this.dificultad = dificultad;
        this.medicacion = medicacion;
        this.medicacionAntiguedad = medicacionAntiguedad;
        this.medicacionInfo = medicacionInfo;
        this.intervencion = intervencion;
    }

    /**
     *
     * @param tdah
     * @param enfermedad
     * @param tdahTipo
     * @param medicacionAntiguedad
     * @param medicacionInfo
     * @param childId
     * @param intervencion
     * @param tdahEdad
     * @param dificultad
     * @param medicacion
     */
    public CreateDClinicosRequest(Integer childId, String enfermedad, Boolean tdah, String tdahTipo, Integer tdahEdad, String dificultad, Boolean medicacion, String medicacionAntiguedad, String medicacionInfo, Boolean intervencion) {
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
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public CreateDClinicosRequest withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public CreateDClinicosRequest withEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
        return this;
    }

    public Boolean getTdah() {
        return tdah;
    }

    public void setTdah(Boolean tdah) {
        this.tdah = tdah;
    }

    public CreateDClinicosRequest withTdah(Boolean tdah) {
        this.tdah = tdah;
        return this;
    }

    public String getTdahTipo() {
        return tdahTipo;
    }

    public void setTdahTipo(String tdahTipo) {
        this.tdahTipo = tdahTipo;
    }

    public CreateDClinicosRequest withTdahTipo(String tdahTipo) {
        this.tdahTipo = tdahTipo;
        return this;
    }

    public Integer getTdahEdad() {
        return tdahEdad;
    }

    public void setTdahEdad(Integer tdahEdad) {
        this.tdahEdad = tdahEdad;
    }

    public CreateDClinicosRequest withTdahEdad(Integer tdahEdad) {
        this.tdahEdad = tdahEdad;
        return this;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public CreateDClinicosRequest withDificultad(String dificultad) {
        this.dificultad = dificultad;
        return this;
    }

    public Boolean getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(Boolean medicacion) {
        this.medicacion = medicacion;
    }

    public CreateDClinicosRequest withMedicacion(Boolean medicacion) {
        this.medicacion = medicacion;
        return this;
    }

    public String getMedicacionAntiguedad() {
        return medicacionAntiguedad;
    }

    public void setMedicacionAntiguedad(String medicacionAntiguedad) {
        this.medicacionAntiguedad = medicacionAntiguedad;
    }

    public CreateDClinicosRequest withMedicacionAntiguedad(String medicacionAntiguedad) {
        this.medicacionAntiguedad = medicacionAntiguedad;
        return this;
    }

    public String getMedicacionInfo() {
        return medicacionInfo;
    }

    public void setMedicacionInfo(String medicacionInfo) {
        this.medicacionInfo = medicacionInfo;
    }

    public CreateDClinicosRequest withMedicacionInfo(String medicacionInfo) {
        this.medicacionInfo = medicacionInfo;
        return this;
    }

    public Boolean getIntervencion() {
        return intervencion;
    }

    public void setIntervencion(Boolean intervencion) {
        this.intervencion = intervencion;
    }

    public CreateDClinicosRequest withIntervencion(Boolean intervencion) {
        this.intervencion = intervencion;
        return this;
    }

}