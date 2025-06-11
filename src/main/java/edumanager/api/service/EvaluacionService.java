package edumanager.api.service;

import org.springframework.stereotype.Service;
import edumanager.api.dao.EvaluacionDAO;
import edumanager.api.model.Evaluacion;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionDAO evaluacionDAO;

    public EvaluacionService(EvaluacionDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }

    public Evaluacion add(Evaluacion evaluacion) {
        evaluacionDAO.save(evaluacion);
        return evaluacion;
    }

    public Evaluacion actualizar(Evaluacion evaluacion) {
        evaluacionDAO.update(evaluacion);
        return evaluacion;
    }

    public List<Evaluacion> listar() {
        return evaluacionDAO.findAll();
    }

    public Evaluacion consultar(int id) {
        return evaluacionDAO.findById(id);
    }

    public void eliminar(int id) {
        evaluacionDAO.delete(id);
    }
}
