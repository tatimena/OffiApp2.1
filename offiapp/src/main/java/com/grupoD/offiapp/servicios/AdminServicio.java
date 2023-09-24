package com.grupoD.offiapp.servicios;

import com.grupoD.offiapp.Entidades.Admin;
import com.grupoD.offiapp.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupoD.offiapp.repositorios.AdminRepositorio;

@Service
public class AdminServicio {

    @Autowired
    private AdminRepositorio adminRepositorio;

    @Transactional
    public void crearAdmin(String id, String nombreAdm, int idAdm, String email) throws MiException {

        validar(nombreAdm, email);

        Admin admin = new Admin();

        admin.setNombreAdm(nombreAdm);
        admin.setEmail(email);

        adminRepositorio.save(admin);

    }

    public List<Admin> listarAdmin() {

        List<Admin> admin = new ArrayList();

        admin = adminRepositorio.findAll();
        return admin;
    }
    @Transactional
    public void modificarAdmin(String nombreAdm, String email, String id) throws MiException {
        validar(nombreAdm, email);
        Optional<Admin> respuesta = adminRepositorio.findById(id);
        Admin admin = new Admin();
        if (respuesta.isPresent()) {

            admin = respuesta.get();
            admin.setNombreAdm(nombreAdm);

            adminRepositorio.save(admin);
        }
    }
    @Transactional
    public void eliminarAdmin(String id) throws MiException {

        Optional<Admin> respuesta = adminRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Admin admin = respuesta.get();
            adminRepositorio.deleteById(id);
        }
    }

    private void validar(String nombreAdm, String email) throws MiException {
        if (nombreAdm.isEmpty() || nombreAdm == null) {
            throw new MiException("el nombre no puede estar vacio");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("el email no puede estar vacio");
        }
    }

  
}

