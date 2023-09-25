package com.grupoD.offiapp.controladores;


import com.grupoD.offiapp.excepciones.MiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.grupoD.offiapp.servicios.UsuarioServicio;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String Registro() {

        return "registro_usuario.html";
    }

    @PostMapping("/registrar")
    public String Registrar(@RequestParam String nombreUser, @RequestParam String direccion, @RequestParam String email, @RequestParam String password, String password2, Integer telefono, String servicio, Integer precioHora, String descripcion, ModelMap modelo) throws MiException {

        try {
            usuarioServicio.registrar(nombreUser, direccion, email, password, password2, telefono, servicio, precioHora, descripcion);
            modelo.put("exito", "Usted se ha registrado correctamente");
            return "index.html";
        } catch (MiException ex) {

            // Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());

            return "registro_usuario.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos");
        }
        return "login.html";
    }

    @GetMapping("/logueado")
    public String Logueado() {

        return "usuariolog.html";
    }
    
    @GetMapping("/conocenos")
    public String conocenos(){
        return "about_us.html";
    }
    
    @GetMapping("/registroProveedor")
    public String RegistroProveedor() {
        return "registro_proveedor.html";
    }

    @PostMapping("/registrarProveedor")

    public String RegistrarProveedor(@RequestParam String nombreProv, 
            @RequestParam String email,@RequestParam String direccion,
            @RequestParam String password, @RequestParam String password2, @RequestParam Integer telefono, @RequestParam String servicio,
            @RequestParam Integer precioHora, @RequestParam(required = false) String descripcion, ModelMap modelo) {

        try {
            usuarioServicio.registrar(nombreProv, direccion, email, password, password2, telefono, servicio, precioHora, descripcion);
            modelo.put("exito", "Usted se ha registrado correctamente como Proveedor");
            return "index.html";
        } catch (MiException ex) {

            // Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());

            return "registro_proveedor.html";
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
*/
}
