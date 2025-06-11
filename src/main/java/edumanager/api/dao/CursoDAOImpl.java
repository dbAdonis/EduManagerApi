package edumanager.api.dao;

import java.sql.*;
import java.util.List;

import edumanager.api.model.Curso;
import edumanager.api.model.Profesor;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDAOImpl implements CursoDAO {

    private final JdbcTemplate jdbcTemplate;

    public CursoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Curso curso) {
        String sql = "INSERT INTO Curso (nombre, cupo, cupo_disponible, id_profesor) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, curso.getNombre(), curso.getCupo(), curso.getCupo_disponible(), curso.getProfesor().getId_profesor());
    }

    @Override
    public Curso findById(int id) {
        String sql = """
            SELECT 
                c.id_curso, 
                c.nombre, 
                c.cupo, 
                c.cupo_disponible,
                p.id_profesor, 
                p.nombre AS profesor_nombre, 
                p.correo AS profesor_correo
            FROM Curso c
            JOIN Profesor p ON c.id_profesor = p.id_profesor
            WHERE c.id_curso = ?
        """;
        return jdbcTemplate.queryForObject(sql, new CursoRowMapper(), id);
    }

    @Override
    public List<Curso> findAll() {
        String sql = """
            SELECT 
                c.id_curso, 
                c.nombre, 
                c.cupo, 
                c.cupo_disponible,
                p.id_profesor, 
                p.nombre AS profesor_nombre, 
                p.correo AS profesor_correo
            FROM Curso c
            JOIN Profesor p ON c.id_profesor = p.id_profesor
        """;
        return jdbcTemplate.query(sql, new CursoRowMapper());
    }

    @Override
    public void update(Curso curso) {
        String sql = "UPDATE Curso SET nombre = ?, cupo = ?, cupo_disponible = ?, id_profesor = ? WHERE id_curso = ?";
        jdbcTemplate.update(sql, curso.getNombre(), curso.getCupo(), curso.getCupo_disponible(), curso.getProfesor().getId_profesor(), curso.getId_curso());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Curso WHERE id_curso = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CursoRowMapper implements RowMapper<Curso> {
        @Override
        public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Curso c = new Curso();
            c.setId_curso(rs.getInt("id_curso"));
            c.setNombre(rs.getString("nombre"));
            c.setCupo(rs.getInt("cupo"));
            c.setCupo_disponible(rs.getInt("cupo_disponible"));

            Profesor profesor = new Profesor();
            profesor.setId_profesor(rs.getInt("id_profesor"));
            profesor.setNombre(rs.getString("profesor_nombre"));
            profesor.setCorreo(rs.getString("profesor_correo"));
            c.setProfesor(profesor);

            return c;
        }
    }
}
