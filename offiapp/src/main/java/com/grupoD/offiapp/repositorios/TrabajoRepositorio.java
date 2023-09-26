/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupoD.offiapp.repositorios;

import com.grupoD.offiapp.Entidades.Trabajo;
import com.grupoD.offiapp.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author gonza
 */
public interface TrabajoRepositorio extends JpaRepository<Trabajo, String> {
   
    @Query("SELECT t FROM Trabajo t WHERE t.id = :id")
    public Trabajo buscarPorid(@Param("id") String id);
}
