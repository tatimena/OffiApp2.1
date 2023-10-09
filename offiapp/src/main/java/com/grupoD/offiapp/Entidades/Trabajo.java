/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupoD.offiapp.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author gonza
 */

 @Entity
public class Trabajo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String trabajoId;
    private String estado;
    private String descripcion;
    private String usuarioSolicitante_id;
    private String proveedorAsignado_id;
    
    public Trabajo() {
    }

    public Trabajo(String trabajoId, String usuarioSolicitante_id, String proveedorAsignado_id, String estado, String descripcion) {
        this.trabajoId = trabajoId;
        this.usuarioSolicitante_id = usuarioSolicitante_id;
        this.proveedorAsignado_id = proveedorAsignado_id;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public String getTrabajoId() {
        return trabajoId;
    }

    public void setTrabajoId(String trabajoId) {
        this.trabajoId = trabajoId;
    }

    public String getUsuarioSolicitante_id() {
        return usuarioSolicitante_id;
    }

    public void setUsuarioSolicitante_id(String usuarioSolicitante_id) {
        this.usuarioSolicitante_id = usuarioSolicitante_id;
    }

    public String getProveedorAsignado_id() {
        return proveedorAsignado_id;
    }

    public void setProveedorAsignado_id(String proveedorAsignado_id) {
        this.proveedorAsignado_id = proveedorAsignado_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
 }

   