package edumanager.api.service;

import org.springframework.stereotype.Service;

import edumanager.api.dao.CalificacionDAO;
import edumanager.api.model.Calificacion;
import java.util.List;

@Service
public class CalificacionService {

    private final CalificacionDAO calificacionDAO;

    public CalificacionService(CalificacionDAO calificacionDAO) {
        this.calificacionDAO = calificacionDAO;
    }

    public Calificacion add(Calificacion calificacion) {
        calificacionDAO.save(calificacion);
        return calificacion;
    }

    public Calificacion actualizar(Calificacion calificacion) {
        calificacionDAO.update(calificacion);
        return calificacion;
    }

    public List<Calificacion> listar() {
        return calificacionDAO.findAll();
    }

    public Calificacion consultar(String id) {
        return calificacionDAO.findById(id);
    }

    public void eliminar(String id) {
        calificacionDAO.delete(id);
    }

}
