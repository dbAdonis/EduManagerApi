package edumanager.api.dao;

import edumanager.api.model.Estudiante;
import java.util.List;

public interface EstudianteDAO {
    void save(Estudiante estudiante);
    Estudiante findById(String id);
    List<Estudiante> findAll();
    void update(Estudiante estudiante);
    void delete(String id);
}