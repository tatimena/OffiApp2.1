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
    private String id;
    private int calificacion;
    private double valorCalificacion;
    private String comentario;
    @ManyToOne
    private Usuario usuario;


    public Calificacion() {
    }

    public Calificacion(String id, int calificacion, double valorCalificacion, String comentario, Usuario usuario) {
        this.id = id;
        this.calificacion = calificacion;
        this.valorCalificacion = valorCalificacion;
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public double getValorCalificacion() {
        return valorCalificacion;
    }

    public void setValorCalificacion(double valorCalificacion) {
        this.valorCalificacion = valorCalificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

 }