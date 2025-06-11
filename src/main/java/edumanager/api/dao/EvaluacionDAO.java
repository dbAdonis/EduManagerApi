
package edumanager.api.dao;

import edumanager.api.model.Evaluacion;

import java.util.List;

public interface EvaluacionDAO {
    void save(Evaluacion evaluacion);
    Evaluacion findById(int id);
    List<Evaluacion> findAll();
    void update(Evaluacion evaluacion);
    void delete(int id);
}
