package edumanager.api.controller;

import edumanager.api.model.Matricula;
import edumanager.api.service.MatriculaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "*") // Permite llamadas desde el frontend
public class MatriculaRestController {

    private final MatriculaService matriculaService;

    public MatriculaRestController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @GetMapping
    public List<Matricula> listar() {
        return matriculaService.listar();
    }

    @PostMapping
    public Matricula agregar(@RequestBody Matricula matricula) {
        return matriculaService.add(matricula);
    }

    @GetMapping("/{id}")
    public Matricula obtenerPorId(@PathVariable int id) {
        return matriculaService.consultar(id);
    }

    @PutMapping("/{id}")
    public Matricula actualizar(@PathVariable int id, @RequestBody Matricula matricula) {
        matricula.setId_matricula(id);
        return matriculaService.actualizar(matricula);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        matriculaService.eliminar(id);
    }
}
