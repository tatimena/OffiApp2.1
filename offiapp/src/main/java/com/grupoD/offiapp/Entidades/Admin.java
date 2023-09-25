
package com.grupoD.offiapp.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author hplap
 */
@Entity
@Table(name="Admin")
public class Admin {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombreAdm;
    private String contrasenia;
    private String email;

    public Admin() {
    }

    public Admin(String id, String nombreAdm, String contrasenia, String email) {
        this.id = id;
        this.nombreAdm = nombreAdm;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreAdm() {
        return nombreAdm;
    }

    public void setNombreAdm(String nombreAdm) {
        this.nombreAdm = nombreAdm;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
