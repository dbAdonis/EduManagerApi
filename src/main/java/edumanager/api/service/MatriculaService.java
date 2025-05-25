package edumanager.api.service;

import org.springframework.stereotype.Service;

import edumanager.api.dao.MatriculaDAO;
import edumanager.api.model.Matricula;
import java.util.List;

@Service
public class MatriculaService {
    
    private final MatriculaDAO matriculaDAO;


    public MatriculaService(MatriculaDAO matriculaDAO) {
        this.matriculaDAO = matriculaDAO;
    }

    public void add(Matricula matricula){
        matriculaDAO.save(matricula);
    }

    public List<Matricula> listar(){
        return matriculaDAO.findAll();
    }

    public Matricula consultar(String id){
        return matriculaDAO.findById(id);
    }

    public void eliminar(String id){
        matriculaDAO.delete(id);
    }

    public void actualizar(Matricula matricula){
        matriculaDAO.update(matricula);
    }
}
