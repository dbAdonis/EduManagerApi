package edumanager.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edumanager.api.model.Calificacion;
import edumanager.api.model.Curso;
import edumanager.api.model.Estudiante;
import edumanager.api.model.Matricula;
import edumanager.api.model.Profesor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CalificacionDAOImpl implements CalificacionDAO {

    private final JdbcTemplate jdbcTemplate;

    public CalificacionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Calificacion c) {
        String sql = "INSERT INTO Calificacion (id_matricula, nota, fecha_calificacion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, c.getMatricula().getId_matricula(), c.getNota(), c.getFecha_calificacion());
    }

    @Override
    public Calificacion findById(int id) {
        String sql = """
            SELECT 
                c.id_calificacion, c.nota, c.fecha_calificacion,
                m.id_matricula, m.fecha_matricula, m.estado,
                e.id_estudiante, e.nombre AS estudiante_nombre, e.correo AS estudiante_correo, e.estado AS estudiante_estado,
                cu.id_curso, cu.nombre AS curso_nombre, cu.cupo, cu.cupo_disponible,
                p.id_profesor, p.nombre AS profesor_nombre, p.correo AS profesor_correo
            FROM Calificacion c
            JOIN Matricula m ON c.id_matricula = m.id_matricula
            JOIN Estudiante e ON m.id_estudiante = e.id_estudiante
            JOIN Curso cu ON m.id_curso = cu.id_curso
            JOIN Profesor p ON cu.id_profesor = p.id_profesor
            WHERE c.id_calificacion = ?
        """;
        return jdbcTemplate.queryForObject(sql, new CalificacionRowMapper(), id);
    }

    @Override
    public List<Calificacion> findAll() {
        String sql = """
            SELECT 
                c.id_calificacion, c.nota, c.fecha_calificacion,
                m.id_matricula, m.fecha_matricula, m.estado,
                e.id_estudiante, e.nombre AS estudiante_nombre, e.correo AS estudiante_correo, e.estado AS estudiante_estado,
                cu.id_curso, cu.nombre AS curso_nombre, cu.cupo, cu.cupo_disponible,
                p.id_profesor, p.nombre AS profesor_nombre, p.correo AS profesor_correo
            FROM Calificacion c
            JOIN Matricula m ON c.id_matricula = m.id_matricula
            JOIN Estudiante e ON m.id_estudiante = e.id_estudiante
            JOIN Curso cu ON m.id_curso = cu.id_curso
            JOIN Profesor p ON cu.id_profesor = p.id_profesor
        """;
        return jdbcTemplate.query(sql, new CalificacionRowMapper());
    }

    @Override
    public void update(Calificacion c) {
        String sql = "UPDATE Calificacion SET id_matricula = ?, nota = ?, fecha_calificacion = ? WHERE id_calificacion = ?";
        jdbcTemplate.update(sql, c.getMatricula().getId_matricula(), c.getNota(), c.getFecha_calificacion(), c.getId_calificacion());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Calificacion WHERE id_calificacion = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CalificacionRowMapper implements RowMapper<Calificacion> {
        @Override
        public Calificacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Calificacion cal = new Calificacion();
            cal.setId_calificacion(rs.getInt("id_calificacion"));
            cal.setNota(rs.getDouble("nota"));
            cal.setFecha_calificacion(rs.getDate("fecha_calificacion"));

            // Estudiante
            Estudiante est = new Estudiante();
            est.setId_estudiante(rs.getInt("id_estudiante"));
            est.setNombre(rs.getString("estudiante_nombre"));
            est.setCorreo(rs.getString("estudiante_correo"));
            est.setEstado(rs.getString("estudiante_estado"));

            // Profesor
            Profesor prof = new Profesor();
            prof.setId_profesor(rs.getInt("id_profesor"));
            prof.setNombre(rs.getString("profesor_nombre"));
            prof.setCorreo(rs.getString("profesor_correo"));

            // Curso
            Curso curso = new Curso();
            curso.setId_curso(rs.getInt("id_curso"));
            curso.setNombre(rs.getString("curso_nombre"));
            curso.setCupo(rs.getInt("cupo"));
            curso.setCupo_disponible(rs.getInt("cupo_disponible"));
            curso.setProfesor(prof);

            // Matricula
            Matricula mat = new Matricula();
            mat.setId_matricula(rs.getInt("id_matricula"));
            mat.setFecha_matricula(rs.getDate("fecha_matricula").toLocalDate());
            mat.setEstado(rs.getString("estado"));
            mat.setEstudiante(est);
            mat.setCurso(curso);

            cal.setMatricula(mat);
            return cal;
        }
    }
}
