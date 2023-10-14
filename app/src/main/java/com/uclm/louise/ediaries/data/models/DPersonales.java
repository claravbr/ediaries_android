package com.uclm.louise.ediaries.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DPersonales {

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
    public DPersonales() {
    }

    /**
     *
     * @param createdAt
     * @param peso
     * @param altura
     * @param fnacimiento
     * @param id
     * @param childId
     * @param sexo
     * @param updatedAt
     */
    public DPersonales(Integer childId, String sexo, Float peso, Float altura, String fnacimiento, String updatedAt, String createdAt, Integer id) {
        super();
        this.childId = childId;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.fnacimiento = fnacimiento;
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

    public DPersonales withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public DPersonales withSexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public DPersonales withPeso(Float peso) {
        this.peso = peso;
        return this;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public DPersonales withAltura(Float altura) {
        this.altura = altura;
        return this;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public DPersonales withFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DPersonales withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public DPersonales withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DPersonales withId(Integer id) {
        this.id = id;
        return this;
    }

}