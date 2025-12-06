package com.example.autorizame_api.model;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;



public class Pedido {

    private Long id;

    @NotBlank
    private String descripcion;

    @PastOrPresent
    private LocalDate fecha;

    @NotNull
    private Long idCliente;

    @NotNull    
    private Long idAutorizado;

    @NotNull
    private Long idRepartidor;

    @NotNull
    private Long idEmpresa;

    private String estado; // ( CREADO, EN_REPARTO, ENTREGADO )


    public Pedido(){
        
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public LocalDate getFecha() {
        return fecha;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public Long getIdCliente() {
        return idCliente;
    }


    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


    public Long getIdAutorizado() {
        return idAutorizado;
    }


    public void setIdAutorizado(Long idAutorizado) {
        this.idAutorizado = idAutorizado;
    }


    public Long getIdRepartidor() {
        return idRepartidor;
    }


    public void setIdRepartidor(Long idRepartidor) {
        this.idRepartidor = idRepartidor;
    }


    public Long getIdEmpresa() {
        return idEmpresa;
    }


    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }

    



}

