/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupoD.offiapp.repositorios;

import com.grupoD.offiapp.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email")String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    public Usuario buscarPorid(@Param("id") String id);
    
    @Query("SELECT u FROM Usuario u WHERE u.servicio = :servicio")
    public Usuario buscarPorServicio(@Param("servicio") String servicio);
}
