package edumanager.api.service;

import edumanager.api.dao.TipoEvaluacionDAO;
import edumanager.api.model.TipoEvaluacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEvaluacionService {

    private final TipoEvaluacionDAO tipoEvaluacionDAO;

    public TipoEvaluacionService(TipoEvaluacionDAO tipoEvaluacionDAO) {
        this.tipoEvaluacionDAO = tipoEvaluacionDAO;
    }

    public TipoEvaluacion add(TipoEvaluacion tipo) {
        tipoEvaluacionDAO.save(tipo);
        return tipo;
    }

    public TipoEvaluacion actualizar(TipoEvaluacion tipo) {
        tipoEvaluacionDAO.update(tipo);
        return tipo;
    }

    public List<TipoEvaluacion> listar() {
        return tipoEvaluacionDAO.findAll();
    }

    public TipoEvaluacion consultar(int id) {
        return tipoEvaluacionDAO.findById(id);
    }

    public void eliminar(int id) {
        tipoEvaluacionDAO.delete(id);
    }
}
