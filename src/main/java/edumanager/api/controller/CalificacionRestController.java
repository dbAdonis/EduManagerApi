package edumanager.api.controller;

import edumanager.api.model.Calificacion;
import edumanager.api.service.CalificacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "*") // Permite llamadas desde el frontend
public class CalificacionRestController {

    private final CalificacionService calificacionService;

    public CalificacionRestController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping
    public List<Calificacion> listar() {
        return calificacionService.listar();
    }

    @PostMapping
    public Calificacion agregar(@RequestBody Calificacion calificacion) {
        return calificacionService.add(calificacion);
    }

    @PutMapping("/{id}")
    public Calificacion actualizar(@PathVariable int id, @RequestBody Calificacion calificacion) {
        calificacion.setId_calificacion(id);
        return calificacionService.actualizar(calificacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        calificacionService.eliminar(id);
    }
}
