package edumanager.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edumanager.api.model.Calificacion;
import edumanager.api.service.CalificacionService;

@Controller
@RequestMapping("/calificaciones")
public class CalificacionController {
    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("calificaciones", calificacionService.listar());
        return "calificaciones";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("calificacion", new Calificacion());
        return "formularioCalificacion";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Calificacion calificacion) {
        calificacionService.add(calificacion);
        return "redirect:/calificaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Calificacion c = calificacionService.consultar(id);
        model.addAttribute("calificacion", c);
        return "frmactualizarCalificacion";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Calificacion calificacion) {
        calificacionService.actualizar(calificacion);
        return "redirect:/calificaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        calificacionService.eliminar(id);
        return "redirect:/calificaciones";
    }
}

