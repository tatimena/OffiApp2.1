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
import java.util.List;
import java.util.stream.Collectors;
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


    @GetMapping("/")
    public String index() throws MiException {

        return "index.html";
    }

    @GetMapping("/registro")
    public String RegistroUs() {

        return "registro_usuario.html";
    }

    @PostMapping("/registrarUs")
    public String registrarUs(@RequestParam String nombreUser, @RequestParam String direccion, @RequestParam String email, @RequestParam String password, String password2, Integer telefono, String servicio, ModelMap modelo) throws MiException {
        /* @RequestParam MultipartFile archivo,  */
        try {
            usuarioServicio.registrarUs(nombreUser, direccion, email, password, password2, telefono, servicio);
            // archivo,
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

    @PostMapping("/registrarProveedor")
    public String RegistrarProveedor(@RequestParam MultipartFile archivo, @RequestParam String nombreProv,
            @RequestParam String email, @RequestParam String direccion,
            @RequestParam String password, @RequestParam String password2, @RequestParam Integer telefono, @RequestParam String servicio,
            @RequestParam Integer precioHora, @RequestParam(required = false) String descripcion, ModelMap modelo) {

        try {
            usuarioServicio.registrarProv(nombreProv, direccion, email, password, password2, telefono, servicio, precioHora, descripcion, archivo);
            modelo.put("exito", "Usted se ha registrado correctamente como Proveedor");
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
    public String Logueado(Model model) {

        Usuario plomeria = usuarioServicio.getOne("4e5c6689-4107-470c-9fac-50161cd30b15");
        Usuario electricidad = usuarioServicio.getOne("7c0693a6-12fd-451f-af66-6f2f45962b58");
        Usuario gasista = usuarioServicio.getOne("8967b2bd-2efc-41e4-af42-ec52dbfcfd52");
        Usuario carpintero = usuarioServicio.getOne("886bcfb1-16e5-4dbd-8e99-0b7a60380a54");

        model.addAttribute("plomeria", plomeria);
        model.addAttribute("electricidad", electricidad);
        model.addAttribute("gasista", gasista);
        model.addAttribute("carpintero", carpintero);

        return "inicio1_usuario.html";
    }

    @GetMapping("/conocenos")
    public String conocenos() {
        return "about_us.html";
    }

    @GetMapping("/perfilProveedor")
    public String mostrarPerfilProveedor(Model model) {
        return "peril_proveedor.html";
    }
}
