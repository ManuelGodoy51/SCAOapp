package com.example.scaocl;

public class AgregarAccion {
        private Integer idAccion;
        private String nombreAccion;
        private String TipoCustomAccion;

    public AgregarAccion(Integer idAccion, String nombreAccion) {
        this.idAccion = idAccion;
        this.nombreAccion = nombreAccion;
    }

    public Integer getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    @Override
    public String toString() {
        this.TipoCustomAccion = this.TipoCustomAccion = nombreAccion;
        return TipoCustomAccion;
    }
}
