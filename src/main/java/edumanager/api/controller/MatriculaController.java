package edumanager.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edumanager.api.model.Matricula;
import edumanager.api.service.MatriculaService;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("matriculas", matriculaService.listar());
        return "matriculas";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("matricula", new Matricula());
        return "formularioMatricula";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Matricula matricula) {
        matriculaService.add(matricula);
        return "redirect:/matriculas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Matricula m = matriculaService.consultar(id);
        model.addAttribute("matricula", m);
        return "frmactualizarMatricula";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Matricula matricula) {
        matriculaService.actualizar(matricula);
        return "redirect:/matriculas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        matriculaService.eliminar(id);
        return "redirect:/matriculas";
    }
}