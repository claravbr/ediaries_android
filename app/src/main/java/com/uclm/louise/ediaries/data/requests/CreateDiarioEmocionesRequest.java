
package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDiarioEmocionesRequest {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("emocion")
    private String emocion;
    @SerializedName("descripcion")
    private String descripcion;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateDiarioEmocionesRequest() {
    }

    /**
     *
     * @param descripcion
     * @param fecha
     * @param childId
     * @param emocion
     */
    public CreateDiarioEmocionesRequest(Integer childId, String fecha, String emocion, String descripcion) {
        super();
        this.childId = childId;
        this.fecha = fecha;
        this.emocion = emocion;
        this.descripcion = descripcion;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public CreateDiarioEmocionesRequest withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public CreateDiarioEmocionesRequest withFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public CreateDiarioEmocionesRequest withEmocion(String emocion) {
        this.emocion = emocion;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CreateDiarioEmocionesRequest withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

}
