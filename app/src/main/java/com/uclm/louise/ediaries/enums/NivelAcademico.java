package com.uclm.louise.ediaries.enums;

public enum NivelAcademico {
    CeroTres("0-3 años"),
    TresSeis("3-6 años"),
    PrimeroPrimaria("1º Primaria"),
    SegundoPrimaria("2º Primaria"),
    TerceroPrimaria("3º Primaria"),
    CuartoPrimaria("4º Primaria"),
    QuintoPrimaria("5º Primaria"),
    SextoPrimaria("6º Primaria"),
    PrimeroEso("1º ESO"),
    SegundoEso("2º ESO"),
    TerceroEso("3º ESO"),
    CuartoEso("4º ESO"),
    PrimeroBachillerato("1º Bachillerato"),
    SegundoBachillerato("2º Bachillerato");

    private String descripcion;

    NivelAcademico(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
