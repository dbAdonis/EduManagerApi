package edumanager.api.controller;

import edumanager.api.model.Profesor;
import edumanager.api.service.ProfesorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = "*") // permite llamadas desde el frontend
public class ProfesorRestController {

    private final ProfesorService profesorService;

    public ProfesorRestController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> listar() {
        return profesorService.listar();
    }

    @PostMapping
    public Profesor agregar(@RequestBody Profesor profesor) {
        return profesorService.add(profesor);
    }

    @PutMapping("/{id}")
    public Profesor actualizar(@PathVariable int id, @RequestBody Profesor profesor) {
        profesor.setId_profesor(id);
        return profesorService.actualizar(profesor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        profesorService.eliminar(id);
    }
}
