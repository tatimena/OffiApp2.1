
package com.grupoD.offiapp.controladores;
import com.grupoD.offiapp.Entidades.Usuario;
import com.grupoD.offiapp.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {
      @Autowired
    UsuarioServicio usuarioServicio;
    
    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenCasa(@PathVariable String id){
        
        Usuario usuario = usuarioServicio.getOne(id);
                
        byte[] imagen = usuario.getImagen().getContenido();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
    
}