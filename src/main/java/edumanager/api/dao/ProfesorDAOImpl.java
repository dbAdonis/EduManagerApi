package edumanager.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import edumanager.api.model.Profesor;

@Repository
public class ProfesorDAOImpl implements ProfesorDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProfesorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Profesor profesor) {
        String sql = "INSERT INTO Profesor (nombre, correo_institucional) VALUES (?, ?)";
        jdbcTemplate.update(sql, profesor.getNombre(), profesor.getCorreo_institucional());
    }

    @Override
    public Profesor findById(String id) {
        String sql = "SELECT * FROM Profesor WHERE id_profesor = ?";
        return jdbcTemplate.queryForObject(sql, new ProfesorRowMapper(), id);
    }

    @Override
    public List<Profesor> findAll() {
        String sql = "SELECT * FROM Profesor";
        return jdbcTemplate.query(sql, new ProfesorRowMapper());
    }

    @Override
    public void update(Profesor profesor) {
        String sql = "UPDATE Profesor SET nombre = ?, correo_institucional = ? WHERE id_profesor = ?";
        jdbcTemplate.update(sql, profesor.getNombre(),  profesor.getCorreo_institucional(), profesor.getId_profesor());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Profesor WHERE id_profesor = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ProfesorRowMapper implements RowMapper<Profesor> {
        @Override
        public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Profesor(
                rs.getString("nombre"),
                rs.getString("correo_institucional")
            );
        }
    }
}
