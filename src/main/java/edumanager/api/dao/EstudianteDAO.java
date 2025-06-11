package edumanager.api.dao;

import java.util.List;
import edumanager.api.model.Estudiante;

public interface EstudianteDAO {
    void save(Estudiante estudiante);
    Estudiante findById(int id);
    List<Estudiante> findAll();
    void update(Estudiante estudiante);
    void delete(int id);
}
