package edumanager.api.dao;

import java.util.List;
import edumanager.api.model.Profesor;

public interface ProfesorDAO {
    void save(Profesor profesor);
    Profesor findById(int id);
    List<Profesor> findAll();
    void update(Profesor profesor);
    void delete(int id);
}
