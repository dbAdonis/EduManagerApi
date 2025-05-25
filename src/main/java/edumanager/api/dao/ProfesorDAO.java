package edumanager.api.dao;

import edumanager.api.model.Profesor;
import java.util.List;

public interface ProfesorDAO {
    void save(Profesor profesor);
    Profesor findById(String id);
    List<Profesor> findAll();
    void update(Profesor profesor);
    void delete(String id);
}