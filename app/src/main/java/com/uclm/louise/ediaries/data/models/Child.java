package com.uclm.louise.ediaries.data.models;

import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("id")
    private Integer id;
    @SerializedName("usuario_id")
    private Integer usuarioId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Child() {
    }

    /**
     *
     * @param createdAt
     * @param id
     * @param usuarioId
     * @param updatedAt
     */
    public Child(Integer id, Integer usuarioId, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.usuarioId = usuarioId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Child withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Child withUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Child withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Child withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}