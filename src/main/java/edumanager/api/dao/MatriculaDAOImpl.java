package edumanager.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edumanager.api.model.Curso;
import edumanager.api.model.Estudiante;
import edumanager.api.model.Matricula;
import edumanager.api.model.Profesor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MatriculaDAOImpl implements MatriculaDAO {

    private final JdbcTemplate jdbcTemplate;

    public MatriculaDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Matricula m) {
        String sql = "INSERT INTO Matricula (id_estudiante, id_curso, fecha_matricula, estado) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, m.getEstudiante().getId_estudiante(), m.getCurso().getId_curso(), m.getFecha_matricula(), m.getEstado());
    }

    @Override
    public Matricula findById(int id) {
        String sql = """
            SELECT 
                m.id_matricula, m.fecha_matricula, m.estado,
                e.id_estudiante, e.nombre AS estudiante_nombre, e.correo AS estudiante_correo, e.estado AS estudiante_estado,
                c.id_curso, c.nombre AS curso_nombre, c.cupo, c.cupo_disponible,
                p.id_profesor, p.nombre AS profesor_nombre, p.correo AS profesor_correo
            FROM Matricula m
            JOIN Estudiante e ON m.id_estudiante = e.id_estudiante
            JOIN Curso c ON m.id_curso = c.id_curso
            JOIN Profesor p ON c.id_profesor = p.id_profesor
            WHERE m.id_matricula = ?
        """;
        return jdbcTemplate.queryForObject(sql, new MatriculaRowMapper(), id);
    }

    @Override
    public List<Matricula> findAll() {
        String sql = """
            SELECT 
                m.id_matricula, m.fecha_matricula, m.estado,
                e.id_estudiante, e.nombre AS estudiante_nombre, e.correo AS estudiante_correo, e.estado AS estudiante_estado,
                c.id_curso, c.nombre AS curso_nombre, c.cupo, c.cupo_disponible,
                p.id_profesor, p.nombre AS profesor_nombre, p.correo AS profesor_correo
            FROM Matricula m
            JOIN Estudiante e ON m.id_estudiante = e.id_estudiante
            JOIN Curso c ON m.id_curso = c.id_curso
            JOIN Profesor p ON c.id_profesor = p.id_profesor
        """;
        return jdbcTemplate.query(sql, new MatriculaRowMapper());
    }

    @Override
    public void update(Matricula m) {
        String sql = "UPDATE Matricula SET id_estudiante = ?, id_curso = ?, fecha_matricula = ?, estado = ? WHERE id_matricula = ?";
        jdbcTemplate.update(sql, m.getEstudiante().getId_estudiante(), m.getCurso().getId_curso(), m.getFecha_matricula(), m.getEstado(), m.getId_matricula());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Matricula WHERE id_matricula = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class MatriculaRowMapper implements RowMapper<Matricula> {
        @Override
        public Matricula mapRow(ResultSet rs, int rowNum) throws SQLException {
            Matricula m = new Matricula();
            m.setId_matricula(rs.getInt("id_matricula"));
            m.setFecha_matricula(rs.getDate("fecha_matricula").toLocalDate());
            m.setEstado(rs.getString("estado"));

            Estudiante e = new Estudiante();
            e.setId_estudiante(rs.getInt("id_estudiante"));
            e.setNombre(rs.getString("estudiante_nombre"));
            e.setCorreo(rs.getString("estudiante_correo"));
            e.setEstado(rs.getString("estudiante_estado"));

            Profesor p = new Profesor();
            p.setId_profesor(rs.getInt("id_profesor"));
            p.setNombre(rs.getString("profesor_nombre"));
            p.setCorreo(rs.getString("profesor_correo"));

            Curso c = new Curso();
            c.setId_curso(rs.getInt("id_curso"));
            c.setNombre(rs.getString("curso_nombre"));
            c.setCupo(rs.getInt("cupo"));
            c.setCupo_disponible(rs.getInt("cupo_disponible"));
            c.setProfesor(p);

            m.setEstudiante(e);
            m.setCurso(c);

            return m;
        }
    }
}
