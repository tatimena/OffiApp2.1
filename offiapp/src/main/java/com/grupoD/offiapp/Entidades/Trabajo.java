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
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "usuarioSolicitante_id")
    private Usuario usuarioSolicitante;
    
    @ManyToOne
    @JoinColumn(name = "proveedorAsignado_id")
    private Usuario proveedorAsignado;
    
    private String estado;
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
  
    public Trabajo() {
    }

    public Trabajo(String id, Usuario usuarioSolicitante, Usuario proveedorAsignado, String estado, String descripcion, Usuario usuario) {
        this.id = id;
        this.usuarioSolicitante = usuarioSolicitante;
        this.proveedorAsignado = proveedorAsignado;
        this.estado = estado;
        this.descripcion = descripcion;
        this.usuario = usuario;
      
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public Usuario getProveedorAsignado() {
        return proveedorAsignado;
    }

    public void setProveedorAsignado(Usuario proveedorAsignado) {
        this.proveedorAsignado = proveedorAsignado;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    }
    

