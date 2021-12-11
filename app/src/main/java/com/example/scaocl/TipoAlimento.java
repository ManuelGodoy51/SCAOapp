package com.example.scaocl;

public class TipoAlimento {
    private Integer id;
    private String nombre;
    private String TipoCustom;
    public TipoAlimento(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        this.TipoCustom = this.TipoCustom = nombre;
        return TipoCustom;
    }
}
