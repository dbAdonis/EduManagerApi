package edumanager.api.dao;

import java.sql.*;
import java.util.List;
import org.springframework.jdbc.core.*;
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
        String sql = "INSERT INTO Profesor (nombre, correo) VALUES (?, ?)";
        jdbcTemplate.update(sql, profesor.getNombre(), profesor.getCorreo());
    }

    @Override
    public Profesor findById(int id) {
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
        String sql = "UPDATE Profesor SET nombre = ?, correo = ? WHERE id_profesor = ?";
        jdbcTemplate.update(sql, profesor.getNombre(), profesor.getCorreo(), profesor.getId_profesor());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Profesor WHERE id_profesor = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ProfesorRowMapper implements RowMapper<Profesor> {
        @Override
        public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Profesor p = new Profesor();
            p.setId_profesor(rs.getInt("id_profesor"));
            p.setNombre(rs.getString("nombre"));
            p.setCorreo(rs.getString("correo"));
            return p;
        }
    }
}
