package edumanager.api.dao;

import edumanager.api.model.Calificacion;
import java.util.List;

public interface CalificacionDAO {
    void save(Calificacion calificacion);
    Calificacion findById(String id);
    List<Calificacion> findAll();
    void update(Calificacion calificacion);
    void delete(String id);
}