/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupoD.offiapp.Entidades;

import java.util.stream.DoubleStream;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author gonza
 */
 @Entity
public class Calificacion {
    
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;
    private int calificar;
    private String proveedorAsignado_id;
    private String usuarioSolicitante_id;
    private String comentario;
    @OneToOne
    private Trabajo trabajo;
   // @ManyToOne
   // private Usuario usuario;
    
   


    public Calificacion() {
    }

    public Calificacion(String Id, int calificar, String proveedorAsignado_id, String usuarioSolicitante_id, String comentario, Trabajo trabajo) {
        this.Id = Id;
        this.calificar = calificar;
        this.proveedorAsignado_id = proveedorAsignado_id;
        this.usuarioSolicitante_id = usuarioSolicitante_id;
        this.comentario = comentario;
        this.trabajo = trabajo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getCalificar() {
        return calificar;
    }

    public void setCalificar(int calificar) {
        this.calificar = calificar;
    }

    public String getProveedorAsignado_id() {
        return proveedorAsignado_id;
    }

    public void setProveedorAsignado_id(String proveedorAsignado_id) {
        this.proveedorAsignado_id = proveedorAsignado_id;
    }

    public String getUsuarioSolicitante_id() {
        return usuarioSolicitante_id;
    }

    public void setUsuarioSolicitante_id(String usuarioSolicitante_id) {
        this.usuarioSolicitante_id = usuarioSolicitante_id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
    

 }