
package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.SerializedName;

public class CreateDiarioEmocionesRequest {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("emocion")
    private String emocion;
    @SerializedName("descripcion")
    private String descripcion;

    public CreateDiarioEmocionesRequest(Integer childId, String emocion, String descripcion) {
        this.childId = childId;
        this.emocion = emocion;
        this.descripcion = descripcion;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
