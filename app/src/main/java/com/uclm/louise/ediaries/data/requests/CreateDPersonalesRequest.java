package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.SerializedName;

public class CreateDPersonalesRequest {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("sexo")
    private String sexo;
    @SerializedName("peso")
    private Float peso;
    @SerializedName("altura")
    private Float altura;
    @SerializedName("fnacimiento")
    private String fnacimiento;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateDPersonalesRequest() {
    }

    /**
     *
     * @param peso
     * @param altura
     * @param fnacimiento
     * @param childId
     * @param sexo
     */
    public CreateDPersonalesRequest(Integer childId, String sexo, Float peso, Float altura, String fnacimiento) {
        super();
        this.childId = childId;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.fnacimiento = fnacimiento;
    }

    public CreateDPersonalesRequest(String sexo, Float peso, Float altura, String fnacimiento) {
        super();
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.fnacimiento = fnacimiento;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public CreateDPersonalesRequest withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public CreateDPersonalesRequest withSexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public CreateDPersonalesRequest withPeso(Float peso) {
        this.peso = peso;
        return this;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public CreateDPersonalesRequest withAltura(Float altura) {
        this.altura = altura;
        return this;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public CreateDPersonalesRequest withFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
        return this;
    }

}