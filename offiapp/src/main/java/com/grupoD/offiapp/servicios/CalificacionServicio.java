    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupoD.offiapp.servicios;

import com.grupoD.offiapp.Entidades.Calificacion;
import com.grupoD.offiapp.Entidades.Trabajo;
import com.grupoD.offiapp.Entidades.Usuario;

import com.grupoD.offiapp.repositorios.CalificacionRepositorio;
import com.grupoD.offiapp.repositorios.TrabajoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gonza
 */
@Service
public class CalificacionServicio {
    
    @Autowired
    CalificacionRepositorio calificacionRepositorio;
    @Autowired
     TrabajoRepositorio trabajoRepositorio;
    
    
    
    @Transactional
    public void crearCalificacion(int calificar, String comentario, Trabajo trabajo){
        
      
        Calificacion calificacion = new Calificacion();
        
        calificacion.setCalificar(0);
        calificacion.setComentario(comentario);
        calificacion.setTrabajo(trabajo);
        
        calificacionRepositorio.save(calificacion);
        }
//   
//


    
    
    
    @Transactional
    public void guardarCalificacion(String trabajoId, int calificar, String proveedorAsignado_id,String usuarioSolicitante_id, String comentario) {
      
        Optional<Trabajo> trabajoExistente = trabajoRepositorio.findById(trabajoId);
        
        
        if (trabajoExistente.isPresent()) {
            
            Calificacion nuevaCalificacion = new Calificacion();
            nuevaCalificacion.setCalificar(calificar);
            nuevaCalificacion.setComentario(comentario);
            nuevaCalificacion.setProveedorAsignado_id(proveedorAsignado_id);
            nuevaCalificacion.setUsuarioSolicitante_id(usuarioSolicitante_id);
            
                     calificacionRepositorio.save(nuevaCalificacion);
        } else {
             //throw new TrabajoNoEncontradoException("El trabajo no se encontró en la base de datos");
        }
    }


    
        public List<Calificacion> listaCalificaciones() {
        List<Calificacion> calificaciones = new ArrayList();
        calificaciones = calificacionRepositorio.findAll();
        return calificaciones;
    }
        
        
    
}
