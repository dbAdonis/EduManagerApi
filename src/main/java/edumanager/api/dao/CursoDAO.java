package edumanager.api.dao;

import edumanager.api.model.Curso;
import java.util.List;

public interface CursoDAO {
    void save(Curso curso);
    Curso findById(String id);
    List<Curso> findAll();
    void update(Curso curso);
    void delete(String id);
}