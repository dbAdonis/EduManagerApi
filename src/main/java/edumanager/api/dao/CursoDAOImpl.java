package edumanager.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edumanager.api.model.Curso;
import edumanager.api.model.Profesor;

@Repository
public class CursoDAOImpl implements CursoDAO {
    private final JdbcTemplate jdbcTemplate;

    public CursoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Curso curso) {
        String sql = "INSERT INTO curso (nombre, codigo, id_profesor) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, curso.getId_curso(), curso.getNombre(), curso.getCodigo(),
                curso.getProfesor().getId_profesor());
    }

    @Override
    public Curso findById(String id) {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        return jdbcTemplate.queryForObject(sql, new CursoRowMapper(), id);
    }

    @Override
    public List<Curso> findAll() {
        String sql = "SELECT * FROM curso";
        return jdbcTemplate.query(sql, new CursoRowMapper());
    }

    @Override
    public void update(Curso curso) {
        String sql = "UPDATE curso SET nombre = ?, descripcion = ?, id_profesor = ? WHERE id_curso = ?";
        jdbcTemplate.update(sql, curso.getNombre(), curso.getCodigo(), curso.getProfesor().getId_profesor(),
                curso.getId_curso());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM curso WHERE id_curso = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CursoRowMapper implements RowMapper<Curso> {
        @Override
        public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Curso c = new Curso();
            c.setNombre(rs.getString("nombre"));
            c.setCodigo(rs.getString("codigo"));

            Profesor p = new Profesor();
            p.setId_profesor(rs.getInt("id_profesor"));
            c.setProfesor(p);
            return c;
        }
    }
}
