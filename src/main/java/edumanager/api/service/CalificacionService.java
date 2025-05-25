package edumanager.api.service;

import org.springframework.stereotype.Service;

import edumanager.api.dao.CalificacionDAO;
import edumanager.api.model.Calificacion;
import java.util.List;

@Service
public class CalificacionService {
    
    private final CalificacionDAO calificacionDAO;


    public CalificacionService(CalificacionDAO calificacionDAO) {
        this.calificacionDAO = calificacionDAO;
    }

    public void add(Calificacion calificacion){
        calificacionDAO.save(calificacion);
    }

    public List<Calificacion> listar(){
        return calificacionDAO.findAll();
    }

    public Calificacion consultar(String id){
        return calificacionDAO.findById(id);
    }

    public void eliminar(String id){
        calificacionDAO.delete(id);
    }

    public void actualizar(Calificacion calificacion){
        calificacionDAO.update(calificacion);
    }
}
