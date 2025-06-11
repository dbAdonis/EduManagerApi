package edumanager.api.dao;

import java.util.List;

import edumanager.api.model.TipoEvaluacion;

public interface TipoEvaluacionDAO {
    void save(TipoEvaluacion tipo);
    TipoEvaluacion findById(int id);
    List<TipoEvaluacion> findAll();
    void update(TipoEvaluacion tipo);
    void delete(int id);
}

