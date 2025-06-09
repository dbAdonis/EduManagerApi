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
        jdbcTemplate.update(sql, curso.getNombre(), curso.getCodigo(),
                curso.getProfesor().getId_profesor());
    }

    @Override
    public List<Curso> findAll() {
        String sql = "SELECT c.id_curso, c.nombre, c.codigo, c.id_profesor, " +
                "p.nombre AS profesor_nombre, p.correo_institucional " +
                "FROM curso c " +
                "LEFT JOIN profesor p ON c.id_profesor = p.id_profesor";
        return jdbcTemplate.query(sql, new CursoRowMapper());
    }

    @Override
    public Curso findById(String id) {
        String sql = "SELECT c.id_curso, c.nombre, c.codigo, c.id_profesor, " +
                "p.nombre AS profesor_nombre, p.correo_institucional " +
                "FROM curso c " +
                "LEFT JOIN profesor p ON c.id_profesor = p.id_profesor " +
                "WHERE c.id_curso = ?";
        return jdbcTemplate.queryForObject(sql, new CursoRowMapper(), id);
    }

    @Override
    public void update(Curso curso) {
        String sql = "UPDATE curso SET nombre = ?, codigo = ?, id_profesor = ? WHERE id_curso = ?";
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
            c.setId_curso(rs.getInt("id_curso"));
            c.setNombre(rs.getString("nombre"));
            c.setCodigo(rs.getString("codigo"));

            Profesor p = new Profesor();
            p.setId_profesor(rs.getInt("id_profesor"));
            p.setNombre(rs.getString("profesor_nombre"));
            p.setCorreo_institucional(rs.getString("correo_institucional"));
            c.setProfesor(p);
            return c;
        }
    }
}
