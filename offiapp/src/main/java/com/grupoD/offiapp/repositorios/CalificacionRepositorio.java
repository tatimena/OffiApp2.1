/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupoD.offiapp.repositorios;

import com.grupoD.offiapp.Entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gonza
 */
public interface CalificacionRepositorio extends JpaRepository<Calificacion, String>{
    
}
