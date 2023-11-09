package com.uclm.louise.ediaries.data.models;

        import com.google.gson.annotations.SerializedName;

public class TareaDiaria {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("categoria_id")
    private Integer categoriaId;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("fechaIntroduccion")
    private String fechaIntroduccion;
    @SerializedName("fechaLimite")
    private String fechaLimite;
    @SerializedName("prioridad")
    private String prioridad;
    @SerializedName("duracion")
    private Integer duracion;
    @SerializedName("terminada")
    private Boolean terminada;
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
    public TareaDiaria() {
    }

    public TareaDiaria(Integer childId, Integer categoriaId, String nombre, String fechaIntroduccion, String fechaLimite, String prioridad, Integer duracion, Boolean terminada, String updatedAt, String createdAt, Integer id) {
        this.childId = childId;
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.fechaIntroduccion = fechaIntroduccion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.duracion = duracion;
        this.terminada = terminada;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
    }

    public Boolean getTerminada() {
        return terminada;
    }

    public void setTerminada(Boolean terminada) {
        this.terminada = terminada;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public TareaDiaria withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public TareaDiaria withCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TareaDiaria withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getFechaIntroduccion() {
        return fechaIntroduccion;
    }

    public void setFechaIntroduccion(String fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
    }

    public TareaDiaria withFechaIntroduccion(String fechaIntroduccion) {
        this.fechaIntroduccion = fechaIntroduccion;
        return this;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public TareaDiaria withFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
        return this;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public TareaDiaria withPrioridad(String prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public TareaDiaria withDuracion(Integer duracion) {
        this.duracion = duracion;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TareaDiaria withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public TareaDiaria withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TareaDiaria withId(Integer id) {
        this.id = id;
        return this;
    }

}