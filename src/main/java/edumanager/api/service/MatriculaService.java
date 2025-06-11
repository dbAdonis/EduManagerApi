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

    public Matricula add(Matricula matricula){
        matriculaDAO.save(matricula);
        return matricula;
    }

    
    public Matricula actualizar(Matricula matricula){
        matriculaDAO.update(matricula);
        return matricula;
    }

    public List<Matricula> listar(){
        return matriculaDAO.findAll();
    }

    public Matricula consultar(int id){
        return matriculaDAO.findById(id);
    }

    public void eliminar(int id){
        matriculaDAO.delete(id);
    }

}
