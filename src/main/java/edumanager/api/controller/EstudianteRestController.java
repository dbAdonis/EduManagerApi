package edumanager.api.controller;

import edumanager.api.model.Estudiante;
import edumanager.api.service.EstudianteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*") // ← permite llamadas desde el frontend
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    public EstudianteRestController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> listar() {
        return estudianteService.listar();
    }

    @PostMapping
    public Estudiante agregar(@RequestBody Estudiante estudiante) {
        return estudianteService.add(estudiante);
    }

    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable int id) {
        return estudianteService.consultar(id);
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable int id, @RequestBody Estudiante estudiante) {
        estudiante.setId_estudiante(id);
        return estudianteService.actualizar(estudiante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        estudianteService.eliminar(id);
    }
}
