package edumanager.api.service;

import org.springframework.stereotype.Service;
import edumanager.api.dao.ProfesorDAO;
import edumanager.api.model.Profesor;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorDAO profesorDAO;

    public ProfesorService(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    public void add(Profesor profesor) {
        profesorDAO.save(profesor);
    }

    public List<Profesor> listar() {
        return profesorDAO.findAll();
    }

    public Profesor consultar(String id) {
        return profesorDAO.findById(id);
    }

    public void eliminar(String id) {
        profesorDAO.delete(id);
    }

    public void actualizar(Profesor profesor) {
        profesorDAO.update(profesor);
    }
}
