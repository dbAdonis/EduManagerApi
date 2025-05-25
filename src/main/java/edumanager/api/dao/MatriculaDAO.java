package edumanager.api.dao;

import edumanager.api.model.Matricula;
import java.util.List;

public interface MatriculaDAO {
    void save(Matricula matricula);
    Matricula findById(String id);
    List<Matricula> findAll();
    void update(Matricula matricula);
    void delete(String id);
}