package com.grupoD.offiapp.controladores;

import com.grupoD.offiapp.Entidades.Calificacion;
import com.grupoD.offiapp.Entidades.Trabajo;

import com.grupoD.offiapp.Entidades.Usuario;

import com.grupoD.offiapp.enumeraciones.Rol;


import com.grupoD.offiapp.excepciones.MiException;
import com.grupoD.offiapp.repositorios.CalificacionRepositorio;
import com.grupoD.offiapp.repositorios.UsuarioRepositorio;
import com.grupoD.offiapp.servicios.CalificacionServicio;
import com.grupoD.offiapp.servicios.TrabajoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.grupoD.offiapp.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.stream.Collectors;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private TrabajoServicio trabajoServicio;
     @Autowired
    private CalificacionServicio calificacionServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private CalificacionRepositorio calificacionRepositorio;

    @GetMapping("/")
    public String index() throws MiException {

        return "index.html";
    }

    @GetMapping("/registro")
    public String RegistroUs() {

        return "registro_usuario.html";
    }


    @PostMapping("/registrarUs")
    public String registrarUs(@RequestParam MultipartFile archivo, @RequestParam String nombreUser, @RequestParam String direccion, @RequestParam String email, @RequestParam String password, String password2, Integer telefono, String servicio, ModelMap modelo) throws MiException {
      
        try {
            usuarioServicio.registrarUs(archivo, nombreUser, direccion, email, password, password2, telefono, servicio);
            
            modelo.put("exito", "Usted se ha registrado correctamente");

            return "index.html";
        } catch (MiException ex) {

            // Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());

            return "registro_usuario.html";
        }

    }


    @GetMapping("/registroProveedor")
    public String RegistroProveedor() {
        return "registro_proveedor.html";
    }

    @PostMapping("/registrarProv")
    public String RegistrarProv(@RequestParam MultipartFile archivo, @RequestParam String nombreUser, @RequestParam String email, @RequestParam String password, String password2, Integer telefono, String servicio, Integer precioHora, String descripcion, ModelMap modelo) throws MiException {

        try {

            Usuario proveedor= usuarioServicio.registrarProv(archivo, nombreUser, email, password, password2, telefono, servicio, precioHora, descripcion);
          

            modelo.put("exito", "Usted se ha registrado correctamente");
            String proveedorId=proveedor.getId();
            return "redirect:/perfilProveedor/" + proveedorId ;
        } catch (MiException ex) {

            // Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());

            return "registro_proveedor.html";
        }

    }

    @GetMapping("/proveedores")
    public String proveedores(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();

        List<Usuario> proveedores = usuarios.stream().filter(usuario -> usuario.getRol() == Rol.PROVEEDOR).collect(Collectors.toList());
   
        modelo.addAttribute("usuarios", proveedores);

        return "oficios.html";
    }

    @GetMapping("/electricistas")
public String electricistas(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        
        

        List<Usuario> Electricista = usuarios.stream().filter(usuario -> "Electricista".equalsIgnoreCase(usuario.getServicio())).collect(Collectors.toList());
       

        modelo.addAttribute("usuarios", Electricista);

        return "electricista_list.html";
    }

    @GetMapping("/carpinteros")
public String carpinteros(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();

        List<Usuario> Carpintero = usuarios.stream().filter(usuario -> "Carpintero".equalsIgnoreCase(usuario.getServicio())).collect(Collectors.toList());

        modelo.addAttribute("usuarios", Carpintero);

        return "carpintero_list.html";
    }

    @GetMapping("/gasistas")
public String gasistas(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();

        List<Usuario> Gasista = usuarios.stream().filter(usuario -> "Gasista".equalsIgnoreCase(usuario.getServicio())).collect(Collectors.toList());

        modelo.addAttribute("usuarios", Gasista);

        return "gasista_list.html";
    }

    @GetMapping("/plomeros")
public String plomeros(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();

        List<Usuario> Plomero = usuarios.stream().filter(usuario -> "Plomero".equalsIgnoreCase(usuario.getServicio())).collect(Collectors.toList());

        modelo.addAttribute("usuarios", Plomero);

        return "plomero_list.html";
    }


    @GetMapping("/login")
public String login(@RequestParam(required = false) String error, ModelMap modelo
    ) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos");
        }
        return "login.html";
    }

    @GetMapping("/logueado")
public String Logueado() {

        return "inicio1_usuario.html";
    }

    @GetMapping("/conocenos")

public String conocenos() {
        return "about_us.html";
    }


   



   
//     @GetMapping("/perfilProveedor")
//    public String mostrarPerfilProveedor(Model model) {
//        // Aquí puedes agregar lógica para obtener los datos del proveedor
//        // y pasarlos al modelo para que se muestren en la vista
//        model.addAttribute("nombreProveedor", "Nombre del Proveedor");
//        model.addAttribute("descripcion", "Descripción del trabajo: Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
//        model.addAttribute("calificaciones", obtenerCalificacionesProveedor()); // Reemplaza con tu propia lógica
//
//        return "vistaPerfil.html"; // Este es el nombre de la vista HTML (perfilProveedor.html)
//    }



    
      @GetMapping("/perfilProveedor/{id}")
      public String mostrarPerfilProveedor(@PathVariable String id, Model model) {
      Usuario proveedor = usuarioServicio.getOne(id);
      model.addAttribute("proveedor", proveedor);
      return "perfil_Proveedor.html"; 
    }

    // Método de ejemplo para obtener calificaciones (reemplaza con tu propia lógica)
    private List<Calificacion> obtenerCalificacionesProveedor() {
        List<Calificacion> calificaciones = new ArrayList<>();
        // calificaciones.add(new Calificacion(5, "01/01/2023", "Excelente proveedor. Muy satisfecho con su trabajo."));
        // Agrega más calificaciones
        return calificaciones;
    }
    
    @RequestMapping("/vistaTrabajoProveedor/{id}")
    public String vistaTrabajoProveedor(@RequestParam String id,ModelMap modelo) {
        
      

    modelo.put("proveedor",usuarioServicio.getOne(id));
    modelo.put("usuario",usuarioServicio.getOne(id));
    
    return "vistaTrabajoProveedor.html";
}


    @GetMapping("/aceptar/{trabajoId}")// Redirige a la página adecuada
public String aceptarTrabajo(@PathVariable String trabajoId) {
    trabajoServicio.cambiarEstado(trabajoId, "Aceptado");
    
    return "vistaPerfil.html";
}


    @GetMapping("/proveedor/registro")
    public String RegistroProv() {
        return "registro_proveedor.html";
    }


@GetMapping("/rechazar/{trabajoId}")// Redirige a la página adecuada
public String rechazarTrabajo(@PathVariable String trabajoId) {
    trabajoServicio.cambiarEstado(trabajoId, "Rechazado");
    
    return "vistaPerfil.html";
}

@GetMapping("/marcarPendiente/{trabajoId}")// Redirige a la página adecuada
public String marcarPendiente(@PathVariable String trabajoId) {
    trabajoServicio.cambiarEstado(trabajoId, "Pendiente");
    
    return "vistaPerfil.html";
}

@GetMapping("/marcarFinalizado/{trabajoId}") // Redirige a la página adecuada
public String marcarFinalizado(@PathVariable String trabajoId) {
    trabajoServicio.cambiarEstado(trabajoId, "Finalizado");
   
    return "vistaPerfil.html";
}
@GetMapping("/calificar/{trabajoId}")
    public String mostrarPaginaCalificacion(@PathVariable String trabajoId, Model model) {
        Trabajo trabajo = trabajoServicio.obtenerTrabajoPorId(trabajoId);
        String proveedorId = trabajo.getProveedorAsignado_id(); 
        
        System.out.println("trabajoid"+trabajoId);
        System.out.println("proveedorId"+proveedorId);
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(proveedorId);
         if (usuario != null) {
           
            model.addAttribute("trabajo", trabajo);
            model.addAttribute("nombreProveedor", usuario.getNombreUser());
            
            return "vistaCalificaciondeUsuario";
        } else {
            // Manejo de error si el proveedor no se encuentra
            return "error"; // Debes definir una página de error adecuada
        }

      //  model.addAttribute("trabajo", trabajo);
        //return "vistaCalificaciondeUsuario.html";
    }


    @PostMapping("/calificar/{trabajoId}")
    public String calificarTrabajo(
            @PathVariable String trabajoId,
            @RequestParam int calificar,
            @RequestParam String comentario, 
            @RequestParam String proveedorAsignado_id,
            @RequestParam String usuarioSolicitante_id, 
            ModelMap modelo       ) {
        if (calificar < 1 || calificar > 5) {
            // Aquí puedes agregar manejo de error o redirigir a una página de error
            return "vistaCalificaciondeUsuario";
        }
        if (comentario.isEmpty()) {
            // Manejo de error si el comentario está vacío
            return "vistaCalificaciondeUsuario";
        }

       
        //calificacionServicio.guardarCalificacion(trabajo, calificar, comentario, usuario);

        
        return "index.html";
    }
    
    /* @PostMapping("/contratar-servicio")
    public String crearTrabajo(  @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("precioHora") Integer precioHora,
        @RequestParam("proveedorId") String proveedorId,
        @RequestParam("usuarioId") String usuarioId, ModelMap modelo) {
        try {
            trabajoServicio.crearTrabajo(usuarioId, usuarioId, proveedorId, descripcion, usuarioId);
            
        } catch (MiException ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return "index.html";
    }  */
     @PostMapping("/contratar-servicio")
    public String crearTrabajo(
        @RequestParam("proveedorAsignado_id") String proveedorId,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("estado") String estado,
        ModelMap modelo) {
        
        // Obtener el usuario logueado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuarioEmail = auth.getName();
        Usuario usuario= usuarioRepositorio.buscarPorEmail(usuarioEmail);
        String usuarioId=usuario.getId();
        
        
        // Crear un objeto Trabajo con los datos
        Trabajo trabajo = new Trabajo();
        trabajo.setUsuarioSolicitante_id(usuarioId);
        trabajo.setProveedorAsignado_id(proveedorId);
        trabajo.setDescripcion(descripcion);
        trabajo.setEstado(estado);
        
        try {
            trabajoServicio.crearTrabajo(trabajo);
        } catch (MiException ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "index.html";
    } 
//    @PostMapping("'/contratar-servicio")
//public String crearTrabajo(@ModelAttribute Trabajo trabajo, ModelMap modelo) {
//    try {
//        trabajoServicio.crearTrabajo(trabajo);
//    } catch (MiException ex) {
//        Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//    }
//
//    return "index.html";
    
@GetMapping("/trabajos-contratados")
public String obtenerTrabajosContratadosPorUsuario(
    @RequestParam("usuarioSolicitante_id") String usuarioId,
    ModelMap modelo) {

    // Utiliza el ID del usuario para obtener los trabajos contratados
    List<Trabajo> trabajos = trabajoServicio.obtenerTrabajosPorUsuario(usuarioId);
    
    modelo.addAttribute("trabajos", trabajos);

    return "listaTrabajosSolicitados"; // Reemplaza "listaTrabajosSolicitados" con el nombre de tu vista
}
@PostMapping("/volver")
      public String volverAlListado(@RequestParam ("servicio")String servicio){
             String urlRedireccion ="/"+ servicio.toLowerCase()+"s"; // Transformamos el tipo de servicio a minúsculas
    return "redirect:" + urlRedireccion;
      }

}

    
    
    
    


    



