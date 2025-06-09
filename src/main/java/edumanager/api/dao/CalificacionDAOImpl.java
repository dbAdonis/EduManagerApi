package edumanager.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edumanager.api.model.Calificacion;
import edumanager.api.model.Matricula;

@Repository
public class CalificacionDAOImpl implements CalificacionDAO {
    private final JdbcTemplate jdbcTemplate;

    public CalificacionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Calificacion calificacion) {
        String sql = "INSERT INTO calificacion (id_matricula, tipo_evaluacion, nota, fecha_registro) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                calificacion.getMatricula().getId_matricula(),
                calificacion.getTipo_evaluacion(),
                calificacion.getNota(),
                new java.sql.Date(calificacion.getFecha_registro().getTime()));
    }

    @Override
    public List<Calificacion> findAll() {
        String sql = """
                    SELECT cal.id_calificacion, cal.tipo_evaluacion, cal.nota, cal.fecha_registro,
                           m.id_matricula,
                           e.id_estudiante, e.nombre, e.correo, e.carnet,
                           c.id_curso, c.nombre AS curso_nombre, c.codigo,
                           p.id_profesor, p.nombre AS profesor_nombre, p.correo_institucional AS profesor_correo
                    FROM calificacion cal
                    INNER JOIN matricula m ON cal.id_matricula = m.id_matricula
                    INNER JOIN estudiante e ON m.id_estudiante = e.id_estudiante
                    INNER JOIN curso c ON m.id_curso = c.id_curso
                    LEFT JOIN profesor p ON c.id_profesor = p.id_profesor
                """;
        return jdbcTemplate.query(sql, new CalificacionCompletoRowMapper());
    }

    @Override
    public Calificacion findById(String id) {
        String sql = """
                    SELECT cal.id_calificacion, cal.tipo_evaluacion, cal.nota, cal.fecha_registro,
                           m.id_matricula,
                           e.id_estudiante, e.nombre, e.correo, e.carnet,
                           c.id_curso, c.nombre AS curso_nombre, c.codigo,
                           p.id_profesor, p.nombre AS profesor_nombre, p.correo_institucional AS profesor_correo
                    FROM calificacion cal
                    INNER JOIN matricula m ON cal.id_matricula = m.id_matricula
                    INNER JOIN estudiante e ON m.id_estudiante = e.id_estudiante
                    INNER JOIN curso c ON m.id_curso = c.id_curso
                    LEFT JOIN profesor p ON c.id_profesor = p.id_profesor
                    WHERE cal.id_calificacion = ?
                """;
        return jdbcTemplate.queryForObject(sql, new CalificacionCompletoRowMapper(), id);
    }

    @Override
    public void update(Calificacion calificacion) {
        String sql = "UPDATE calificacion SET id_matricula = ?, tipo_evaluacion = ?, nota = ?, fecha_registro = ? WHERE id_calificacion = ?";
        jdbcTemplate.update(sql,
                calificacion.getMatricula().getId_matricula(),
                calificacion.getTipo_evaluacion(),
                calificacion.getNota(),
                new java.sql.Date(calificacion.getFecha_registro().getTime()),
                calificacion.getId_calificacion());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM calificacion WHERE id_calificacion = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CalificacionCompletoRowMapper implements RowMapper<Calificacion> {
        @Override
        public Calificacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Calificacion cal = new Calificacion();

            cal.setId_calificacion(rs.getInt("id_calificacion"));
            cal.setTipo_evaluacion(rs.getString("tipo_evaluacion"));
            cal.setNota(rs.getDouble("nota"));
            cal.setFecha_registro(rs.getDate("fecha_registro"));

            // Matricula
            Matricula m = new Matricula();
            m.setId_matricula(rs.getInt("id_matricula"));

            // Estudiante
            edumanager.api.model.Estudiante e = new edumanager.api.model.Estudiante();
            e.setId_estudiante(rs.getInt("id_estudiante"));
            e.setNombre(rs.getString("nombre"));
            e.setCorreo(rs.getString("correo"));
            e.setCarnet(rs.getString("carnet"));
            m.setEstudiante(e);

            // Curso
            edumanager.api.model.Curso c = new edumanager.api.model.Curso();
            c.setId_curso(rs.getInt("id_curso"));
            c.setNombre(rs.getString("curso_nombre"));
            c.setCodigo(rs.getString("codigo"));

            // Profesor (puede ser null)
            Integer idProfesor = rs.getInt("id_profesor");
            if (!rs.wasNull()) {
                edumanager.api.model.Profesor p = new edumanager.api.model.Profesor();
                p.setId_profesor(idProfesor);
                p.setNombre(rs.getString("profesor_nombre"));
                p.setCorreo_institucional(rs.getString("profesor_correo"));
                c.setProfesor(p);
            }
            m.setCurso(c);

            cal.setMatricula(m);

            return cal;
        }
    }

}
