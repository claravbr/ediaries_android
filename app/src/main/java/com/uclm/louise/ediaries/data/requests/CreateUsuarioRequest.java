package com.uclm.louise.ediaries.data.requests;

import com.google.gson.annotations.SerializedName;

public class CreateUsuarioRequest {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellidos")
    private String apellidos;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("fotoPath")
    private String fotoPath;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateUsuarioRequest() {
    }

    /**
     *
     * @param apellidos
     * @param password
     * @param fotoPath
     * @param nombre
     * @param email
     */
    public CreateUsuarioRequest(String nombre, String apellidos, String email, String password, String fotoPath) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.fotoPath = fotoPath;
    }

    public CreateUsuarioRequest(String nombre, String apellidos, String email, String password) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CreateUsuarioRequest withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public CreateUsuarioRequest withApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreateUsuarioRequest withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateUsuarioRequest withPassword(String password) {
        this.password = password;
        return this;
    }

    public Object getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    public CreateUsuarioRequest withFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
        return this;
    }

}