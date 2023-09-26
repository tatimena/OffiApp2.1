import com.grupoD.offiapp.Entidades.Calificacion;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProveedorControlador{

    @GetMapping("/perfilProveedor")
    public String mostrarPerfilProveedor(Model model) {
        // Aquí puedes agregar lógica para obtener los datos del proveedor
        // y pasarlos al modelo para que se muestren en la vista
        model.addAttribute("nombreProveedor", "Nombre del Proveedor");
        model.addAttribute("descripcion", "Descripción del trabajo: Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        model.addAttribute("calificaciones", obtenerCalificacionesProveedor()); // Reemplaza con tu propia lógica

        return "vistaPerfil.html"; // Este es el nombre de la vista HTML (perfilProveedor.html)
    }

    // Método de ejemplo para obtener calificaciones (reemplaza con tu propia lógica)
    private List<Calificacion> obtenerCalificacionesProveedor() {
        List<Calificacion> calificaciones = new ArrayList<>();
       // calificaciones.add(new Calificacion(5, "01/01/2023", "Excelente proveedor. Muy satisfecho con su trabajo."));
        // Agrega más calificaciones
        return calificaciones;
    }
}
