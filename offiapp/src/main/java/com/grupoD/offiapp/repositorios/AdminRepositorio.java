package com.grupoD.offiapp.repositorios;


import com.grupoD.offiapp.Entidades.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tatim
 */
@Repository
public interface AdminRepositorio extends JpaRepository<Admin, String>{
        @Query("SELECT a FROM Admin a WHERE a.id = :id")
    public Admin buscarPorid(@Param("id") String id);
        @Query("SELECT a FROM Admin a WHERE a.email = :email")
    public Admin buscarPorEmail(@Param("email")String email);
}
