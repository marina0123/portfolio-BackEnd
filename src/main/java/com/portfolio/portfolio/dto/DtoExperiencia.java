package com.portfolio.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

public class DtoExperiencia {
    @NotBlank
    private String puesto;
    @NotBlank
    private String empresa;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String periodo;
   
    public DtoExperiencia() {
    }

    public DtoExperiencia(String puesto, String empresa, String descripcion, String periodo) {
        this.puesto = puesto;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.periodo = periodo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
