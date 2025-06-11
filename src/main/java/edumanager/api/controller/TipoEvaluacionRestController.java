package edumanager.api.controller;

import edumanager.api.model.TipoEvaluacion;
import edumanager.api.service.TipoEvaluacionService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipos-evaluacion")
@CrossOrigin(origins = "*")
public class TipoEvaluacionRestController {

    private final TipoEvaluacionService service;

    public TipoEvaluacionRestController(TipoEvaluacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoEvaluacion> listar() {
        return service.listar();
    }

    @PostMapping
    public TipoEvaluacion agregar(@RequestBody TipoEvaluacion tipo) {
        return service.add(tipo);
    }

    @PutMapping("/{id}")
    public TipoEvaluacion actualizar(@PathVariable int id, @RequestBody TipoEvaluacion tipo) {
        tipo.setId_tipo(id);
        return service.actualizar(tipo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }
}

