/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupoD.offiapp.servicios;

import com.grupoD.offiapp.Entidades.Calificacion;
import com.grupoD.offiapp.Entidades.Trabajo;

import com.grupoD.offiapp.repositorios.CalificacionRepositorio;
import com.grupoD.offiapp.repositorios.TrabajoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gonza
 */
public class CalificacionServicio {
    
    @Autowired
    CalificacionRepositorio calificacionRepositorio;
    @Autowired
     //TrabajoRepositorio trabajoRepositorio;
    @Transactional
    
    public void crearCalificacion(int calificar, String comentario, String trabajoId){
        
       // Optional<Trabajo> respuesta=trabajoRepositorio.findById(trabajoId);
        //Trabajo trabajo=new Trabajo();
       // trabajo=respuesta.get();
       // trabajo.getProveedorAsignado();
        //trabajo.getUsuarioSolicitante();
        Calificacion calificacion = new Calificacion();
        
        calificacion.setCalificar(0);
        calificacion.setComentario(comentario);
        calificacion.setTrabajoId(trabajoId);
        
        calificacionRepositorio.save(calificacion);
        
        

        
        
        
      
        
        
    }
    
        public List<Calificacion> listaCalificaciones() {
        List<Calificacion> calificaciones = new ArrayList();
        calificaciones = calificacionRepositorio.findAll();
        return calificaciones;
    }
    
}
