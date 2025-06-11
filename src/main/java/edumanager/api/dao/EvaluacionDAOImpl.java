package edumanager.api.dao;

import edumanager.api.model.Curso;
import edumanager.api.model.Estudiante;
import edumanager.api.model.Evaluacion;
import edumanager.api.model.Matricula;
import edumanager.api.model.TipoEvaluacion;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EvaluacionDAOImpl implements EvaluacionDAO {

    private final JdbcTemplate jdbcTemplate;

    public EvaluacionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Evaluacion e) {
        String sql = "INSERT INTO evaluacion (id_matricula, id_tipo, descripcion, nota, porcentaje) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, e.getMatricula().getId_matricula(), e.getTipoEvaluacion().getId_tipo(),
                e.getDescripcion(), e.getNota(), e.getPorcentaje());
    }

    @Override
    public Evaluacion findById(int id) {
        String sql = "SELECT e.*, t.nombre AS tipo_nombre, " +
                "m.id_matricula, m.fecha_matricula, m.estado, " +
                "est.id_estudiante, est.nombre AS estudiante_nombre, est.correo AS estudiante_correo, est.estado AS estudiante_estado, "
                +
                "c.id_curso, c.nombre AS curso_nombre, c.cupo, c.cupo_disponible " +
                "FROM evaluacion e " +
                "JOIN tipo_evaluacion t ON e.id_tipo = t.id_tipo " +
                "JOIN matricula m ON e.id_matricula = m.id_matricula " +
                "JOIN estudiante est ON m.id_estudiante = est.id_estudiante " +
                "JOIN curso c ON m.id_curso = c.id_curso " +
                "WHERE e.id_evaluacion = ?";
        return jdbcTemplate.queryForObject(sql, new EvaluacionRowMapper(), id);
    }

    @Override
    public List<Evaluacion> findAll() {
        String sql = "SELECT e.*, t.nombre AS tipo_nombre, " +
                "m.id_matricula, m.fecha_matricula, m.estado, " +
                "est.id_estudiante, est.nombre AS estudiante_nombre, est.correo AS estudiante_correo, est.estado AS estudiante_estado, "
                +
                "c.id_curso, c.nombre AS curso_nombre, c.cupo, c.cupo_disponible " +
                "FROM evaluacion e " +
                "JOIN tipo_evaluacion t ON e.id_tipo = t.id_tipo " +
                "JOIN matricula m ON e.id_matricula = m.id_matricula " +
                "JOIN estudiante est ON m.id_estudiante = est.id_estudiante " +
                "JOIN curso c ON m.id_curso = c.id_curso";
        return jdbcTemplate.query(sql, new EvaluacionRowMapper());
    }

    @Override
    public void update(Evaluacion e) {
        String sql = "UPDATE evaluacion SET id_matricula = ?, id_tipo = ?, descripcion = ?, nota = ?, porcentaje = ? " +
                "WHERE id_evaluacion = ?";
        jdbcTemplate.update(sql, e.getMatricula().getId_matricula(), e.getTipoEvaluacion().getId_tipo(),
                e.getDescripcion(), e.getNota(), e.getPorcentaje(), e.getId_evaluacion());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM evaluacion WHERE id_evaluacion = ?", id);
    }

    private static class EvaluacionRowMapper implements RowMapper<Evaluacion> {
        @Override
        public Evaluacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Evaluacion e = new Evaluacion();
            e.setId_evaluacion(rs.getInt("id_evaluacion"));

            Matricula m = new Matricula();
            m.setId_matricula(rs.getInt("id_matricula"));
            m.setFecha_matricula(
                    rs.getDate("fecha_matricula") != null ? rs.getDate("fecha_matricula").toLocalDate() : null);
            m.setEstado(rs.getString("estado"));

            Estudiante est = new Estudiante();
            est.setId_estudiante(rs.getInt("id_estudiante"));
            est.setNombre(rs.getString("estudiante_nombre"));
            est.setCorreo(rs.getString("estudiante_correo"));
            est.setEstado(rs.getString("estudiante_estado"));

            Curso c = new Curso();
            c.setId_curso(rs.getInt("id_curso"));
            c.setNombre(rs.getString("curso_nombre"));
            c.setCupo(rs.getInt("cupo"));
            c.setCupo_disponible(rs.getInt("cupo_disponible"));

            m.setEstudiante(est);
            m.setCurso(c);
            e.setMatricula(m);

            TipoEvaluacion tipo = new TipoEvaluacion();
            tipo.setId_tipo(rs.getInt("id_tipo"));
            tipo.setNombre(rs.getString("tipo_nombre"));
            e.setTipoEvaluacion(tipo);

            e.setDescripcion(rs.getString("descripcion"));
            e.setNota(rs.getBigDecimal("nota"));
            e.setPorcentaje(rs.getBigDecimal("porcentaje"));

            return e;
        }
    }

}
