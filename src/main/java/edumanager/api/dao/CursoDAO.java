package edumanager.api.dao;

import java.util.List;
import edumanager.api.model.Curso;

public interface CursoDAO {
    void save(Curso curso);
    Curso findById(int id);
    List<Curso> findAll();
    void update(Curso curso);
    void delete(int id);
}
