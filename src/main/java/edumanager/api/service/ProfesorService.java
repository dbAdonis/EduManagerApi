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

    public Profesor add(Profesor profesor) {
        profesorDAO.save(profesor);
        return profesor;
    }

    public Profesor actualizar(Profesor profesor) {
        profesorDAO.update(profesor);
        return profesor;
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

}
