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
    private String calificacionId;
    private int calificar;
    
    private String comentario;
    @OneToOne
    private Trabajo trabajo;
    @ManyToOne
    private Usuario usuario;
    
   


    public Calificacion() {
    }

    public Calificacion(String calificacionId, int calificar, String comentario, Trabajo trabajo, Usuario usuario) {
        this.calificacionId = calificacionId;
        this.calificar = calificar;
        this.comentario = comentario;
        this.trabajo = trabajo;
        this.usuario = usuario;
    }

    public String getCalificacionId() {
        return calificacionId;
    }

    public void setCalificacionId(String calificacionId) {
        this.calificacionId = calificacionId;
    }

    public int getCalificar() {
        return calificar;
    }

    public void setCalificar(int calificar) {
        this.calificar = calificar;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

 }