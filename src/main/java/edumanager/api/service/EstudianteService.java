package edumanager.api.service;

import org.springframework.stereotype.Service;

import edumanager.api.dao.EstudianteDAO;
import edumanager.api.model.Estudiante;
import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteDAO estudianteDAO;

    public EstudianteService(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    public Estudiante add(Estudiante estudiante) {
        estudianteDAO.save(estudiante);
        return estudiante;
    }

    public Estudiante actualizar(Estudiante estudiante) {
        estudianteDAO.update(estudiante);
        return estudiante;
    }

    public List<Estudiante> listar() {
        return estudianteDAO.findAll();
    }

    public Estudiante consultar(String id) {
        return estudianteDAO.findById(id);
    }

    public void eliminar(String id) {
        estudianteDAO.delete(id);
    }

}
