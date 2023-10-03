package com.grupoD.offiapp.controladores;

import com.grupoD.offiapp.Entidades.Calificacion;
import com.grupoD.offiapp.Entidades.Usuario;

import com.grupoD.offiapp.enumeraciones.Rol;


import com.grupoD.offiapp.excepciones.MiException;
import com.grupoD.offiapp.repositorios.UsuarioRepositorio;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

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
=======
    public String conocenos() {
        return "about_us.html";
    }

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

/*
>>>>>>> a00b1738140c709e59beb9d2ef2019eb42d47a95
    
      @GetMapping("/perfilProveedor")
>>>>>>> 462a2debd6a21f5643eab97a05134d0c9bc611b9
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
}

/*
    @GetMapping("/proveedor/registro")
    public String RegistroProv() {
        return "registro_proveedor.html";
    }

    @PostMapping("/proveedor/registrar")
    public String RegistrarProv( @RequestParam Integer telefono, @RequestParam String servicio,
            @RequestParam Integer precioHora, @RequestParam(required = false) String descripcion, ModelMap modelo) {

        try {
            usuarioServicio.crearProveedor(telefono, servicio, precioHora, descripcion);

            modelo.put("exito", "Usted se registró correctamente");
            return "index.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "registro_proveedor.html";
        }

    }
<<<<<<< HEAD
     */
}

