package com.grupoD.offiapp.servicios;

import com.grupoD.offiapp.Entidades.Usuario;
import com.grupoD.offiapp.Entidades.Trabajo;
import com.grupoD.offiapp.excepciones.MiException;
import com.grupoD.offiapp.repositorios.TrabajoRepositorio;
import com.grupoD.offiapp.repositorios.UsuarioRepositorio;
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
 


@Transactional
public Trabajo crearTrabajo(String id, String usuarioSolicitanteId, String proveedorAsignadoId, String descripcion, String estado) throws MiException {
    
    Usuario usuarioSolicitante = usuarioRepositorio.findById(usuarioSolicitanteId).orElse(null);
    Usuario proveedorAsignado = usuarioRepositorio.findById(proveedorAsignadoId).orElse(null);

    
    if (usuarioSolicitante == null || proveedorAsignado == null) {
        throw new MiException("Usuario solicitante o proveedor asignado no encontrado.");
    }

    
    Trabajo trabajo = new Trabajo();
    trabajo.setUsuarioSolicitante(usuarioSolicitante);
    trabajo.setProveedorAsignado(proveedorAsignado);
    trabajo.setDescripcion(descripcion);
    trabajo.setEstado(estado);
    
    
    return trabajoRepositorio.save(trabajo);
}


public Trabajo obtenerTrabajoPorId(String id) {
    return trabajoRepositorio.findById(id).orElse(null);
}
@Transactional
public Trabajo actualizarTrabajo(String id, String estado) throws MiException {
    Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);
    
    if (respuesta.isPresent()) {
        Trabajo trabajo = respuesta.get();
    trabajo.setEstado(estado);

       
        return trabajoRepositorio.save(trabajo);
    } else {

        throw new MiException("El trabajo con ID " + id + " no fue encontrado.");
    }
}


  
  @Transactional
public void eliminarTrabajo(String id) throws MiException {
    Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);
    if (respuesta.isPresent()) {
        trabajoRepositorio.deleteById(id);
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


}



