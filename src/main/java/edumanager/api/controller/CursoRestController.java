package edumanager.api.controller;

import edumanager.api.model.Curso;
import edumanager.api.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoRestController {

    private final CursoService cursoService;

    public CursoRestController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listar();
    }

    @PostMapping
    public Curso agregar(@RequestBody Curso curso) {
        return cursoService.add(curso);
    }

    @GetMapping("/{id}")
    public Curso obtenerPorId(@PathVariable int id) {
        return cursoService.consultar(id);
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable int id, @RequestBody Curso curso) {
        curso.setId_curso(id);  // Asumo que Curso tiene método setId(String id)
        return cursoService.actualizar(curso);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        cursoService.eliminar(id);
    }
}
