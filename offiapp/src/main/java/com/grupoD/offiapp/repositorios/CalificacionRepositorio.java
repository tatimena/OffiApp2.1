/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupoD.offiapp.repositorios;

import com.grupoD.offiapp.Entidades.Calificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author gonza
 */
public interface CalificacionRepositorio extends JpaRepository<Calificacion, String>{
    
//        @Query("SELECT c FROM Calificacion c WHERE c.nombreUser = :nombreUser")
//    public Calificacion  buscarPorNombre(@Param("nombreUser") String nombreUser);
    
}
