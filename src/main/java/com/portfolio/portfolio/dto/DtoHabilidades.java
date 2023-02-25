package com.portfolio.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

public class DtoHabilidades {
    @NotBlank
    private String habilidad;
    @NotBlank
    private int porcentaje;

    public DtoHabilidades() {
    }

    public DtoHabilidades(String habilidad, int porcentaje) {
        this.habilidad = habilidad;
        this.porcentaje = porcentaje;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }    
}
