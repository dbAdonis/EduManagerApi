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
        String sql = "INSERT INTO calificacion (id_matricula, tipo_evaluacion, nota, fecha) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, calificacion.getMatricula().getId_matricula(),
                calificacion.getTipo_evaluacion(), calificacion.getNota(), calificacion.getFecha());
    }

    @Override
    public Calificacion findById(String id) {
        String sql = "SELECT * FROM calificacion WHERE id_calificacion = ?";
        return jdbcTemplate.queryForObject(sql, new CalificacionRowMapper(), id);
    }

    @Override
    public List<Calificacion> findAll() {
        String sql = "SELECT * FROM calificacion";
        return jdbcTemplate.query(sql, new CalificacionRowMapper());
    }

    @Override
    public void update(Calificacion calificacion) {
        String sql = "UPDATE calificacion SET id_matricula = ?, tipo_evaluacion = ?, nota = ?, fecha = ? WHERE id_calificacion = ?";
        jdbcTemplate.update(sql, calificacion.getMatricula().getId_matricula(), calificacion.getTipo_evaluacion(),
                calificacion.getNota(), calificacion.getFecha(), calificacion.getId_calificacion());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM calificacion WHERE id_calificacion = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CalificacionRowMapper implements RowMapper<Calificacion> {
        @Override
        public Calificacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Calificacion c = new Calificacion();

            Matricula m = new Matricula();
            m.setId_matricula(rs.getInt("id_matricula"));
            c.setMatricula(m);

            String tipo = rs.getString("tipo_evaluacion");
            c.setTipo_evaluacion(Calificacion.TipoEvaluacion.valueOf(tipo));

            c.setNota(rs.getDouble("nota"));
            c.setFecha(rs.getDate("fecha").toLocalDate());

            return c;
        }
    }

}
