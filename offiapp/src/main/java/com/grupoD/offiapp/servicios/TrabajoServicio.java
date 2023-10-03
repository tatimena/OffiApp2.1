package com.grupoD.offiapp.servicios;

import com.grupoD.offiapp.Entidades.Calificacion;
import com.grupoD.offiapp.Entidades.Usuario;
import com.grupoD.offiapp.Entidades.Trabajo;
import com.grupoD.offiapp.excepciones.MiException;
import com.grupoD.offiapp.repositorios.CalificacionRepositorio;
import com.grupoD.offiapp.repositorios.TrabajoRepositorio;
import com.grupoD.offiapp.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajoServicio {
    @Autowired
    private TrabajoRepositorio trabajoRepositorio;
 @Autowired
    private UsuarioRepositorio usuarioRepositorio;
 @Autowired 
  private CalificacionRepositorio calificacionRepositorio;
 


/*@Transactional
public Trabajo crearTrabajo(String trabajoId, String usuarioSolicitanteId, String proveedorAsignadoId, String descripcion, String estado) throws MiException {
    
    //Usuario usuarioSolicitante = usuarioRepositorio.findById(usuarioSolicitanteId).get();
   // Usuario usuarioSolicitante = usuarioRepositorio.findById(usuarioSolicitanteId).get();
    //Usuario proveedorAsignado = usuarioRepositorio.findById(proveedorAsignadoId).get();

    
   

    
    Trabajo trabajo = new Trabajo();
    trabajo.setUsuarioSolicitante_id(usuarioSolicitanteId);
    trabajo.setProveedorAsignado_id(proveedorAsignadoId);
    trabajo.setDescripcion(descripcion);
    trabajo.setEstado(estado);
    
    
    return trabajoRepositorio.save(trabajo);
}*/
 @Transactional
public Trabajo crearTrabajo(Trabajo trabajo) throws MiException {
 
    return trabajoRepositorio.save(trabajo);
}

    public List<Trabajo> listaTrabajos() {
        List<Trabajo> trabajos = new ArrayList();
        trabajos = trabajoRepositorio.findAll();
        return trabajos;
    }

public Trabajo obtenerTrabajoPorId(String trabajoId) {
    return trabajoRepositorio.findById(trabajoId).orElse(null);
}
@Transactional
public Trabajo actualizarTrabajo(String trabajoId, String estado) throws MiException {
    Optional<Trabajo> respuesta = trabajoRepositorio.findById(trabajoId);
    
    if (respuesta.isPresent()) {
        Trabajo trabajo = respuesta.get();
    trabajo.setEstado(estado);

       
        return trabajoRepositorio.save(trabajo);
    } else {

        throw new MiException("El trabajo con ID " + trabajoId + " no fue encontrado.");
    }
}

   public void cambiarEstado(String trabajoId, String nuevoEstado) {
       
        Trabajo trabajo = trabajoRepositorio.buscarPorid(trabajoId);
            if (trabajo != null) {
          
            if (nuevoEstado.equals("Aceptado")) {
                trabajo.setEstado("Aceptado");
            }
            
            else if (nuevoEstado.equals("Rechazado")) {
                trabajo.setEstado("Rechazado");
            }
           
            else if (nuevoEstado.equals("Pendiente")) {
                trabajo.setEstado("Pendiente");
            }
            
            else if (nuevoEstado.equals("Finalizado")) {
                trabajo.setEstado("Finalizado");
            }
            
            trabajoRepositorio.save(trabajo);
        }
    }


  
  @Transactional
public void eliminarTrabajo(String trabajoId) throws MiException {
    Optional<Trabajo> respuesta = trabajoRepositorio.findById(trabajoId);
    if (respuesta.isPresent()) {
        trabajoRepositorio.deleteById(trabajoId);
    }
}

 public void validar(String usuarioSolicitante, String proveedorAsignado, String descripcion, String estado) throws MiException {
        if (usuarioSolicitante == null || usuarioSolicitante.isEmpty()) {
            throw new MiException("El usuarioSolicitante no puede estar vacío");
        }
        if (proveedorAsignado == null || proveedorAsignado.isEmpty()) {
            throw new MiException("El proveedorAsignado no puede estar vacía");
        }
        if (estado == null || estado.isEmpty()) {
            throw new MiException("El estado no puede estar vacío");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new MiException("La descripcion no puede estar vacía ");
        }
        
    }
   @Transactional
public void guardarCalificacion(String trabajoId, int calificar, String comentario) {
    Trabajo trabajo = trabajoRepositorio.findById(trabajoId).orElse(null);
    if (trabajo != null) {
       
        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setCalificar(calificar);
        nuevaCalificacion.setComentario(comentario);
        
        // Asociar la nueva calificación con el trabajo estableciendo su ID
        nuevaCalificacion.setTrabajoId(trabajoId);
        
        // Guardar la calificación en la base de datos
        calificacionRepositorio.save(nuevaCalificacion);

        // Opcionalmente, puedes agregar la calificación al trabajo si tienes una relación bidireccional
       // trabajo.getCalificacion().add(nuevaCalificacion); ser si es necesario
        //trabajoRepositorio.save(trabajo); // Actualizar el trabajo con la nueva calificación
    }
}



}



