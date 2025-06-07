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
                new java.sql.Date(calificacion.getFecha_registro().getTime())
        );
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
        String sql = "UPDATE calificacion SET id_matricula = ?, tipo_evaluacion = ?, nota = ?, fecha_registro = ? WHERE id_calificacion = ?";
        jdbcTemplate.update(sql,
                calificacion.getMatricula().getId_matricula(),
                calificacion.getTipo_evaluacion(),
                calificacion.getNota(),
                new java.sql.Date(calificacion.getFecha_registro().getTime()),
                calificacion.getId_calificacion()
        );
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

            c.setId_calificacion(rs.getInt("id_calificacion"));

            Matricula m = new Matricula();
            m.setId_matricula(rs.getInt("id_matricula"));
            c.setMatricula(m);

            c.setTipo_evaluacion(rs.getString("tipo_evaluacion"));
            c.setNota(rs.getDouble("nota"));
            c.setFecha_registro(rs.getDate("fecha_registro"));

            return c;
        }
    }
}
