package edumanager.api.controller;

import edumanager.api.model.Evaluacion;
import edumanager.api.service.EvaluacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionRestController {

    private final EvaluacionService evaluacionService;

    public EvaluacionRestController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    // Crear evaluación
    @PostMapping
    public Evaluacion crear(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.add(evaluacion);
    }

    // Listar todas las evaluaciones
    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    // Obtener evaluación por ID
    @GetMapping("/{id}")
    public Evaluacion consultar(@PathVariable int id) {
        return evaluacionService.consultar(id);
    }

    // Actualizar evaluación
    @PutMapping("/{id}")
    public Evaluacion actualizar(@PathVariable int id, @RequestBody Evaluacion evaluacion) {
        evaluacion.setId_evaluacion(id);
        return evaluacionService.actualizar(evaluacion);
    }

    // Eliminar evaluación
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        evaluacionService.eliminar(id);
    }

    // Si implementas la búsqueda por matrícula, puedes activar esto:
    /*
    @GetMapping("/matricula/{id}")
    public List<Evaluacion> obtenerPorMatricula(@PathVariable int id) {
        return evaluacionService.obtenerPorMatricula(id);
    }
    */
}
