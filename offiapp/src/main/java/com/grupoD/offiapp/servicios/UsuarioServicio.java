package com.grupoD.offiapp.servicios;

import com.grupoD.offiapp.Entidades.Imagen;
import com.grupoD.offiapp.Entidades.Trabajo;
import com.grupoD.offiapp.Entidades.Usuario;
import com.grupoD.offiapp.enumeraciones.Rol;
import com.grupoD.offiapp.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import com.grupoD.offiapp.repositorios.UsuarioRepositorio;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario getOne(String id) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);

        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
    }

    @Autowired
    private ImagenServicio imagenServicio;

//asi tiene que estar en el thymelife
    @Transactional
    public void registrarUs(String nombreUser, String direccion, String email, String password, String password2, Integer telefono, String servicio) throws MiException {
        /* MultipartFile archivo */
        validar(nombreUser, direccion, email, password, password2);
        Usuario usuario = new Usuario();
        usuario.setNombreUser(nombreUser);
        usuario.setDireccion(direccion);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);

        //Imagen imagen = imagenServicio.guardar(archivo);
        // usuario.setImagen(imagen);
        usuario.setContrasenia(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER);
        if (nombreUser.equals("Admin")) {
            usuario.setRol(Rol.ADMIN);  // email.equals("proveedor" + usuario.getNombreUser() + "@gmail.com"
            // } else if (servicio.isEmpty()) {
            //   usuario.setRol(Rol.USER);
        } else {
            usuario.setRol(Rol.USER);
        }
        usuarioRepositorio.save(usuario);

    }

    public void validar(String nombreUser, String direccion, String email, String password, String password2) throws MiException {
        if (nombreUser == null || nombreUser.isEmpty()) {
            throw new MiException("El nombre no puede estar vacío");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new MiException("La dirección no puede estar vacía");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("El email no puede estar vacío");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía y debe tener al menos 6 caracteres");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas no coinciden");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);
            return new User(usuario.getEmail(), usuario.getContrasenia(), permisos);

        } else {
            return null;
        }

    }

    @Transactional
    public void registrarProv(String nombreUser, String email, String password, String password2, Integer telefono, String servicio, Integer precioHora, String descripcion) throws MiException {
        /* MultipartFile archivo, */
        Usuario usuario = new Usuario();
        usuario.setNombreUser(nombreUser);

        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setServicio(servicio);
        usuario.setPrecioHora(precioHora);
        usuario.setDescripcion(descripcion);
        /*  Imagen imagen = imagenServicio.guardar(archivo);

        usuario.setImagen(imagen); */

        usuario.setContrasenia(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER);
        if (servicio.isEmpty()) {
            usuario.setRol(Rol.USER);
        } else {
            usuario.setRol(Rol.PROVEEDOR);
        }
        usuarioRepositorio.save(usuario);

    }

    /*
    public void validar(Integer telefono, String servicio, Integer precioHora, String descripcion) throws MiException {
        if (telefono == null) {
            throw new MiException("Completar con el número de teléfono");
        }
        if (servicio == null || servicio.isEmpty()) {
            throw new MiException("El servicio no puede estar vacío");
        }
        if (precioHora == null) {
            throw new MiException("Complete con el precio de la hora a trabajar");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new MiException("La descripción no puede estar vacía");
        }
    }
     */
    @Transactional
    public void modificarUsuario(String nombreUser, String direccion, String email, String password, String password2, Integer telefono, String servicio, Integer precioHora, String descripcion) throws MiException {
        /* MultipartFile archivo,  */
        validar(nombreUser, direccion, email, password, password2, telefono, servicio, precioHora, descripcion);

        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {

            usuario.setNombreUser(nombreUser);
            usuario.setDireccion(direccion);
            usuario.setEmail(email);
            usuario.setContrasenia(nombreUser);
            usuario.setTelefono(telefono);
            usuario.setServicio(servicio);
            usuario.setPrecioHora(precioHora);
            usuario.setDescripcion(descripcion);
            /*  Imagen imagen = imagenServicio.guardar(archivo);

            usuario.setImagen(imagen); */

            usuarioRepositorio.save(usuario);

        } else {
            throw new MiException("no se encotro el usuario");

        }
    }

    public void validar(String nombreUser, String direccion, String email, String password, String password2, Integer telefono, String servicio, Integer precioHora, String descripcion) throws MiException {
        if (nombreUser == null || nombreUser.isEmpty()) {
            throw new MiException("El nombre no puede estar vacío");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new MiException("La dirección no puede estar vacía");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("El email no puede estar vacío");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía y debe tener al menos 6 caracteres");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas no coinciden");
        }
        if (telefono == null) {
            throw new MiException("Completar con el número de teléfono");
        }
        if (servicio == null || servicio.isEmpty()) {
            throw new MiException("El servicio no puede estar vacío");
        }
        if (precioHora == null) {
            throw new MiException("Complete con el precio de la hora a trabajar");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new MiException("La descripción no puede estar vacía");
        }

    }

    public Usuario obtenerUsuarioPorId(String id) {

        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public void eliminarUsuario(String id) throws MiException {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuarioRepositorio.deleteById(id);

        }
    }

    /*   public List<Usuario> listarProveedores() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<Usuario> proveedores = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getServicio() != null) {
                // El usuario tiene el atributo "servicio" cargado, lo consideramos un proveedor
                proveedores.add(usuario);
            }
        }

        return proveedores;
    }
     */
    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
       
        return usuarios;
    }
   
   

    @Transactional
    public void asignarNombresDeUsuarios(UsuarioServicio usuarioServicio, Trabajo trabajo) {
        if (trabajo.getUsuarioSolicitante() != null) {
            Usuario solicitante = usuarioServicio.obtenerUsuarioPorId(trabajo.getUsuarioSolicitante().getId()); // Corregido aquí
            if (solicitante != null) {
                trabajo.getUsuarioSolicitante().setNombreUser(solicitante.getNombreUser());
            }
        }
        if (trabajo.getProveedorAsignado() != null) {
            Usuario proveedor = usuarioServicio.obtenerUsuarioPorId(trabajo.getProveedorAsignado().getId());
            if (proveedor != null) {
                trabajo.getProveedorAsignado().setNombreUser(proveedor.getNombreUser());
            }
        }
    }

}
