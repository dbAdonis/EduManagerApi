package edumanager.api.dao;

import edumanager.api.model.TipoEvaluacion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TipoEvaluacionDAOImpl implements TipoEvaluacionDAO {

    private final JdbcTemplate jdbcTemplate;

    public TipoEvaluacionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TipoEvaluacion tipo) {
        String sql = "INSERT INTO tipo_evaluacion (nombre) VALUES (?)";
        jdbcTemplate.update(sql, tipo.getNombre());
    }

    @Override
    public TipoEvaluacion findById(int id) {
        String sql = "SELECT * FROM tipo_evaluacion WHERE id_tipo = ?";
        return jdbcTemplate.queryForObject(sql, new TipoEvaluacionRowMapper(), id);
    }

    @Override
    public List<TipoEvaluacion> findAll() {
        String sql = "SELECT * FROM tipo_evaluacion";
        return jdbcTemplate.query(sql, new TipoEvaluacionRowMapper());
    }

    @Override
    public void update(TipoEvaluacion tipo) {
        String sql = "UPDATE tipo_evaluacion SET nombre = ? WHERE id_tipo = ?";
        jdbcTemplate.update(sql, tipo.getNombre(), tipo.getId_tipo());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tipo_evaluacion WHERE id_tipo = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class TipoEvaluacionRowMapper implements RowMapper<TipoEvaluacion> {
        @Override
        public TipoEvaluacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            TipoEvaluacion tipo = new TipoEvaluacion();
            tipo.setId_tipo(rs.getInt("id_tipo"));
            tipo.setNombre(rs.getString("nombre"));
            return tipo;
        }
    }
}
