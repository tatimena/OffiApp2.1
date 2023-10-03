package com.grupoD.offiapp.controladores;

import com.grupoD.offiapp.Entidades.Calificacion;

import com.grupoD.offiapp.Entidades.Usuario;

import com.grupoD.offiapp.enumeraciones.Rol;


import com.grupoD.offiapp.excepciones.MiException;
import com.grupoD.offiapp.repositorios.UsuarioRepositorio;
import com.grupoD.offiapp.servicios.TrabajoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.grupoD.offiapp.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import static jdk.nashorn.internal.runtime.Debug.id;

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
    private UsuarioRepositorio usuarioRepositorio;

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

            usuarioServicio.registrarProv(archivo, nombreUser, email, password, password2, telefono, servicio, precioHora, descripcion);
          

            modelo.put("exito", "Usted se ha registrado correctamente");
            return "index.html";
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

        List<Usuario> Electricista = usuarios.stream().filter(usuario -> "Electricista".equals(usuario.getServicio())).collect(Collectors.toList());

        modelo.addAttribute("usuarios", Electricista);

        return "electricista_list.html";
    }

    @GetMapping("/carpinteros")
public String carpinteros(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();

        List<Usuario> Carpintero = usuarios.stream().filter(usuario -> "Carpintero".equals(usuario.getServicio())).collect(Collectors.toList());

        modelo.addAttribute("usuarios", Carpintero);

        return "carpintero_list.html";
    }

    @GetMapping("/gasistas")
public String gasistas(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();

        List<Usuario> Gasista = usuarios.stream().filter(usuario -> "Gasista".equals(usuario.getServicio())).collect(Collectors.toList());

        modelo.addAttribute("usuarios", Gasista);

        return "gasista_list.html";
    }

    @GetMapping("/plomeros")
public String plomeros(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();

        List<Usuario> Plomero = usuarios.stream().filter(usuario -> "Plomero".equals(usuario.getServicio())).collect(Collectors.toList());

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


    /*



    @GetMapping("/registroProveedor")
    public String RegistroProveedor() {
        return "registro_proveedor.html";
    }

    @PostMapping("/registrarProveedor")
    public String RegistrarProveedor(@RequestParam MultipartFile archivo, @RequestParam String nombreProv,
            @RequestParam String email, @RequestParam String direccion,
            @RequestParam String password, @RequestParam String password2, @RequestParam Integer telefono, @RequestParam String servicio,
            @RequestParam Integer precioHora, @RequestParam(required = false) String descripcion, ModelMap modelo) {

        try {
            usuarioServicio.registrarProv(archivo, nombreProv, email, password, password2, telefono, servicio, precioHora, descripcion);
            modelo.put("exito", "Usted se ha registrado correctamente como Proveedor");
            return "index.html";
        } catch (MiException ex) {

            // Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());

            return "registro_proveedor.html";
        }
    }
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



    
      @GetMapping("/perfilProveedor")

    public String mostrarPerfilProveedor(Model model) {
        // Aquí puedes agregar lógica para obtener los datos del proveedor
        // y pasarlos al modelo para que se muestren en la vista
        Usuario proveedor = usuarioServicio.getOne("4e5c6689-4107-470c-9fac-50161cd30b15"); // Reemplaza "ID_DEL_PROVEEDOR" por el ID del proveedor que deseas mostrar
        model.addAttribute("proveedor", proveedor);
        

        return "perfil_Proveedor.html"; // Nombre de la vista HTML que mostrará el perfil del proveedor
    }

    // Método de ejemplo para obtener calificaciones (reemplaza con tu propia lógica)
    private List<Calificacion> obtenerCalificacionesProveedor() {
        List<Calificacion> calificaciones = new ArrayList<>();
        // calificaciones.add(new Calificacion(5, "01/01/2023", "Excelente proveedor. Muy satisfecho con su trabajo."));
        // Agrega más calificaciones
        return calificaciones;
    }
    
    @RequestMapping("/vistaTrabajoProveedor/{id}")
    public String vistaTrabajoProveedor(String id,ModelMap modelo) {
        
      

    modelo.put("proveedor",usuarioServicio.getOne("2757dd17-f2d9-4d63-9990-c3720d754f49"));
    modelo.put("usuario",usuarioServicio.getOne("75f63906-5de0-4229-b362-a49cd2d78c6e"));
    
    return "vistaTrabajoProveedor.html";
}


    @GetMapping("/aceptar/{trabajoId}")// Redirige a la página adecuada
public String aceptarTrabajo(@PathVariable String trabajoId) {
    trabajoServicio.cambiarEstado(trabajoId, "Aceptado");
    
    return "vistaPerfil.html";
}

/*
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
        Trabajo trabajo = trabajoServicio.obtenerTrabajoPorId("ded7c570-c217-4ac1-9d61-ed587e9054d8");
        String proveedorId = trabajo.getProveedorAsignado_id(); 
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
            @RequestParam String comentario) {
        if (calificar < 1 || calificar > 5) {
            // Aquí puedes agregar manejo de error o redirigir a una página de error
            return "vistaCalificaciondeUsuario";
        }
        if (comentario.isEmpty()) {
            // Manejo de error si el comentario está vacío
            return "vistaCalificaciondeUsuario";
        }

        // Guardar la calificación y el comentario en la base de datos o sistema de almacenamiento
        trabajoServicio.guardarCalificacion(trabajoId, calificar, comentario);

        // Redirigir al usuario a la página de confirmación o a la lista de trabajos
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
public String crearTrabajo(@ModelAttribute Trabajo trabajo, ModelMap modelo) {
    try {
        trabajoServicio.crearTrabajo(trabajo);
    } catch (MiException ex) {
        Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
    }

    return "index.html";
}

    
    
    
    
}

    



