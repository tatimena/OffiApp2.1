/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoD.offiapp.Entidades;

import com.grupoD.offiapp.enumeraciones.Rol;      // va en minuscula px la carpeta esta en minuscula 
///import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
///import javax.persistence.Temporal;
///import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
//import javax.persistence.OneToMany;

/**
 *
 * @author pulaf
 */
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;
    protected String nombreUser;
    protected String direccion;
    protected String email;
    protected String contrasenia;
    private Integer telefono;
    private String servicio;
    private Integer precioHora;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    protected Rol rol;

    public Usuario() {
    }

    public Usuario(String id, String nombreUser, String direccion, String email, String contrasenia, Integer telefono, String servicio, Integer precioHora, String descripcion, Rol rol) {
        this.id = id;
        this.nombreUser = nombreUser;
        this.direccion = direccion;
        this.email = email;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.servicio = servicio;
        this.precioHora = precioHora;
        this.descripcion = descripcion;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Integer getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Integer precioHora) {
        this.precioHora = precioHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isPresent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Usuario get() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}