package com.portfolio.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

public class DtoEducacion {
    @NotBlank
    private String titulo;
    @NotBlank
    private String instituto;
    @NotBlank
    private String periodo;

    public DtoEducacion() {
    }

    public DtoEducacion(String titulo, String instituto, String periodo) {
        this.titulo = titulo;
        this.instituto = instituto;
        this.periodo = periodo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }    
}
