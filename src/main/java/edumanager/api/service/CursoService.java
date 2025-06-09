package edumanager.api.service;

import org.springframework.stereotype.Service;

import edumanager.api.dao.CursoDAO;
import edumanager.api.model.Curso;
import java.util.List;

@Service
public class CursoService {

    private final CursoDAO cursoDAO;

    public CursoService(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public Curso add(Curso curso) {
        cursoDAO.save(curso);
        return curso;
    }

    public Curso actualizar(Curso curso) {
        cursoDAO.update(curso);
        return curso;
    }

    public List<Curso> listar() {
        return cursoDAO.findAll();
    }

    public Curso consultar(String id) {
        return cursoDAO.findById(id);
    }

    public void eliminar(String id) {
        cursoDAO.delete(id);
    }

}
