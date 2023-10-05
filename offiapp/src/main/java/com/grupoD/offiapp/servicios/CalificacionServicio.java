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
        
       // Optional<Trabajo> respuesta=trabajoRepositorio.findById(trabajoId);
        //Trabajo trabajo=new Trabajo();
       // trabajo=respuesta.get();
       // trabajo.getProveedorAsignado();
        //trabajo.getUsuarioSolicitante();
        Calificacion calificacion = new Calificacion();
        
        calificacion.setCalificar(0);
        calificacion.setComentario(comentario);
        calificacion.setTrabajo(trabajo);
        
        calificacionRepositorio.save(calificacion);
        }
//     @Transactional
//public void guardarCalificacion(Trabajo trabajo, int calificar, String comentario, Usuario usuario) {
//    Trabajo trabajo= 
//    Optional<Trabajo> trabajo = trabajoRepositorio.findById(trabajo.getTrabajoId());
//    if (trabajo != null) {
//       
//        Calificacion nuevaCalificacion = new Calificacion();
//        nuevaCalificacion.setCalificar(calificar);
//        nuevaCalificacion.setComentario(comentario);
//        
// 
//        calificacionRepositorio.save(nuevaCalificacion);
//
//       
//    }
//}


    
    
    
    @Transactional
    public void guardarCalificacion(Trabajo trabajo, int calificar, String comentario, Usuario usuario) {
        // Verificar si el trabajo existe en la base de datos
        Optional<Trabajo> trabajoExistente = trabajoRepositorio.findById(trabajo.getTrabajoId());
        
        if (trabajoExistente.isPresent()) {
            // Crear una nueva instancia de Calificacion
            Calificacion nuevaCalificacion = new Calificacion();
            nuevaCalificacion.setCalificar(calificar);
            nuevaCalificacion.setComentario(comentario);
          //  nuevaCalificacion.setTrabajo(trabajo.getProveedorAsignado_id());
           // nuevaCalificacion.setUsuario(trabajo.getUsuarioSolicitante_id());
            
            // Guardar la calificación en la base de datos
            calificacionRepositorio.save(nuevaCalificacion);
        } else {
            // El trabajo no existe en la base de datos, puedes manejar este caso según tus necesidades
            // Por ejemplo, lanzar una excepción o registrar un mensaje de error
            // throw new TrabajoNoEncontradoException("El trabajo no se encontró en la base de datos");
        }
    }


    
        public List<Calificacion> listaCalificaciones() {
        List<Calificacion> calificaciones = new ArrayList();
        calificaciones = calificacionRepositorio.findAll();
        return calificaciones;
    }
    
}
