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

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import java.util.stream.Collectors;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

            usuarioServicio.registrarProv(nombreUser, descripcion, email, password, password2, telefono, servicio, precioHora, descripcion, archivo);

            Usuario proveedor = usuarioServicio.registrarProv(nombreUser, descripcion, email, password, password2, telefono, servicio, precioHora, descripcion, archivo);
            modelo.put("exito", "Usted se ha registrado correctamente");
            String proveedorId = proveedor.getId();
            return "redirect:/perfilProveedor/" + proveedorId;
        } catch (MiException ex) {

            // Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());

            return "registro_proveedor.html";
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        // Usuario usuario = usuarioServicio.getOne(id);
        // modelo.put("usuario", usuarioServicio.getOne(id));

        modelo.addAttribute("usuario", usuarioServicio.getOne(id));
        return "modificar_prov.html";
    }

    @PostMapping("/modificado/{id}")
    public String modificado(@PathVariable String id, MultipartFile archivo, String nombreUser, String direccion, String email, String password, String password2, Integer telefono, String servicio, Integer precioHora, String descripcion, ModelMap modelo) throws MiException {

        try {
            usuarioServicio.modificarUsuario(archivo, nombreUser, direccion, email, password, password2, telefono, servicio, precioHora, descripcion);

            return "redirect:/proveedores";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "oficios.html";
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id, ModelMap modelo) {
        try {
            usuarioServicio.darDeBaja(id);
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "oficios.html";
        }
        return "redirect:../proveedores";
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
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos");
        }
        return "login.html";
    }

    @GetMapping("/logueado")
    public String Logueado(HttpSession session, Authentication authentication, ModelMap modelo) {
        Usuario plomeria = usuarioServicio.getOne("cb0f7741-368d-46bd-83e2-9115b320542a");
        Usuario electricidad = usuarioServicio.getOne("31bb8eb8-1952-4c6a-bf54-f99dfb62a642");
        Usuario gasista = usuarioServicio.getOne("be0a70d9-6099-4629-a003-14ad507e604b");
        Usuario carpintero = usuarioServicio.getOne("f693e597-bdc0-4fc0-b9ff-238a792edb1e");
        modelo.addAttribute("plomeria", plomeria);
        modelo.addAttribute("electricidad", electricidad);
        modelo.addAttribute("gasista", gasista);
        modelo.addAttribute("carpintero", carpintero);
        Usuario sesionUsuario = (Usuario) session.getAttribute("usuariosession");
        if (authentication != null && authentication.isAuthenticated()) {
            modelo.addAttribute("authorization", authentication.getAuthorities());
        }
        return "inicio1_usuario.html";
    }

    @GetMapping("/conocenos")
    public String conocenos() {
        return "about_us.html";
    }

    @GetMapping("/perfilProveedor")//{id} me tiene que llegar el id del usuario(@PathVariable String id) que se loguee para mostrar la info 
    public String mostrarPerfilProveedor(ModelMap modelo, @RequestParam String id) {
        Usuario proveedor = usuarioServicio.getOne("id");
        modelo.addAttribute("proveedor", proveedor);
        return "perfil_Proveedor.html";//Este es el nombre de la vista HTML (perfilProveedor.html)
    }

    @GetMapping("/perfilUsuario")
    public String mostrarPerfilUsuario(ModelMap modelo) {
        Usuario usuario = usuarioServicio.getOne("dbaf9501-af27-4bcd-ba84-77fe97641908");
        modelo.addAttribute("usuario", usuario);
        return "perfil_Usuario.html"; // Este es el nombre de la vista HTML (perfilProveedor.html)
    }

//@GetMapping("/mostrar-calificacion")
//public String mostrarCalificacion(String nombreUser, Model model) {
//    Usuario usuario= usuarioServicio.obtenerUsuarioPorId(nombreUser);
//    Trabajo trabajo=trabajoServicio.obtenerTrabajoPorId(nombreUser);
//    trabajo.setProveedorAsignado_id(nombreUser);
//    List <Calificacion> calificacion= calificacionServicio.obtenercalificacionPorNombre(nombreUser);
//   
//    String ultimoComentario = obtenerUltimoComentario(); // Reemplaza esto con la lógica real
//    
//    // Agrega estos datos al modelo
//    model.addAttribute("calificacionProveedor", calificacionProveedor);
//    model.addAttribute("ultimoComentario", ultimoComentario);
//    
//    // Devuelve la vista que mostrará la calificación y el comentario
//    return "mostrar_calificacion.html"; // Reemplaza con la ruta de tu vista HTML
//}
    @GetMapping("/perfilProveedor/{id}")
    public String mostrarPerfilProveedor(@PathVariable String id, Model model) {
        Usuario proveedor = usuarioServicio.getOne(id);
        model.addAttribute("proveedor", proveedor);
        return "perfil_Proveedor.html";
    }

//    @GetMapping("/mostrar-calificacion")
//public String mostrarCalificacion(@RequestParam String nombreUser, Model model) {
//    
//    Usuario proveedor = usuarioServicio.obtenerUsuarioPorNombre(nombreUser);
//    
//  
//    if (proveedor == null) {
//        return "error.html"; 
//    }
//    
//  
//    Calificacion calificaciones = calificacionServicio.obtenercalificacionPorProveedor(proveedor);
//    
//    // Verificamos si hay calificaciones
//    if (calificaciones.isEmpty()) {
//        // No hay calificaciones disponibles, puedes manejarlo en la vista
//        model.addAttribute("sinCalificaciones", true);
//    } else {
//        // Obtenemos la última calificación (asumiendo que las calificaciones están ordenadas)
//        Calificacion ultimaCalificacion = calificaciones.get(calificaciones.size() - 1);
//        // Obtenemos el comentario de la última calificación
//        String ultimoComentario = ultimaCalificacion.getComentario();
//        // Agregamos la calificación y el comentario al modelo
//        model.addAttribute("calificacionProveedor", ultimaCalificacion.getPuntuacion());
//        model.addAttribute("ultimoComentario", ultimoComentario);
//    }
//    
//    // Agregamos el proveedor al modelo por si quieres mostrar más información sobre él
//    model.addAttribute("proveedor", proveedor);
//    
//    // Devuelve la vista que mostrará la calificación y el comentario (ajusta la ruta)
//    return "mostrar_calificacion.html"; // Reemplaza con la ruta de tu vista HTML
//}
    // Método de ejemplo para obtener calificaciones (reemplaza con tu propia lógica)
//    private List<Calificacion> obtenerCalificacionesProveedor() {
//        List<Calificacion> calificaciones = new ArrayList<>();
//        // calificaciones.add(new Calificacion(5, "01/01/2023", "Excelente proveedor. Muy satisfecho con su trabajo."));
//        // Agrega más calificaciones
//        return calificaciones;
//    }
    @RequestMapping("/vistaTrabajoProveedor/{id}")
    public String vistaTrabajoProveedor(@RequestParam String id, ModelMap modelo) {

        modelo.put("proveedor", usuarioServicio.getOne(id));
        modelo.put("usuario", usuarioServicio.getOne(id));

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
        String usuarioId = trabajo.getUsuarioSolicitante_id();
        Usuario nombreUser = usuarioServicio.obtenerUsuarioPorId(usuarioId);
        Usuario nombreProv = usuarioServicio.obtenerUsuarioPorId(proveedorId);
        String nombreProve = nombreProv.getNombreUser();
        String nombreUsuarie = nombreUser.getNombreUser();

        System.out.println("trabajoid" + trabajoId);
        System.out.println("usuarioId" + usuarioId);
        System.out.println("proveedorId" + proveedorId);
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(proveedorId);

        if (usuario != null) {
            model.addAttribute("usuarioSolicitanteId", nombreProve);
            model.addAttribute("usuarioSolicitante_id", nombreUsuarie);
            model.addAttribute("proveedorAsignado_id", nombreProve);

            model.addAttribute("trabajo", trabajo);
            model.addAttribute("nombreUsuario", nombreUsuarie);

            return "vistaCalificaciondeUsuario";
        } else {

            return "error";
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
            ModelMap modelo) {
        if (calificar < 1 || calificar > 5) {
            // Aquí puedes agregar manejo de error o redirigir a una página de error
            return "vistaCalificaciondeUsuario";
        }
        if (comentario.isEmpty()) {
            // Manejo de error si el comentario está vacío
            return "vistaCalificaciondeUsuario";
        }

        calificacionServicio.guardarCalificacion(trabajoId, calificar, proveedorAsignado_id, usuarioSolicitante_id, comentario);
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
        Usuario usuario = usuarioRepositorio.buscarPorEmail(usuarioEmail);
        String usuarioId = usuario.getId();

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
    public String volverAlListado(@RequestParam("servicio") String servicio) {
        String urlRedireccion = "/" + servicio.toLowerCase() + "s"; // Transformamos el tipo de servicio a minúsculas
        return "redirect:" + urlRedireccion;
    }

}
