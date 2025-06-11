package edumanager.api.dao;

import java.sql.*;
import java.util.List;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import edumanager.api.model.Estudiante;

@Repository
public class EstudianteDAOImpl implements EstudianteDAO {
    private final JdbcTemplate jdbcTemplate;

    public EstudianteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (nombre, correo, estado) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, estudiante.getNombre(), estudiante.getCorreo(), estudiante.getEstado());
    }

    @Override
    public Estudiante findById(int id) {
        String sql = "SELECT * FROM Estudiante WHERE id_estudiante = ?";
        return jdbcTemplate.queryForObject(sql, new EstudianteRowMapper(), id);
    }

    @Override
    public List<Estudiante> findAll() {
        String sql = "SELECT * FROM Estudiante";
        return jdbcTemplate.query(sql, new EstudianteRowMapper());
    }

    @Override
    public void update(Estudiante estudiante) {
        String sql = "UPDATE Estudiante SET nombre = ?, correo = ?, estado = ? WHERE id_estudiante = ?";
        jdbcTemplate.update(sql, estudiante.getNombre(), estudiante.getCorreo(), estudiante.getEstado(), estudiante.getId_estudiante());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Estudiante WHERE id_estudiante = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class EstudianteRowMapper implements RowMapper<Estudiante> {
        @Override
        public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Estudiante e = new Estudiante();
            e.setId_estudiante(rs.getInt("id_estudiante"));
            e.setNombre(rs.getString("nombre"));
            e.setCorreo(rs.getString("correo"));
            e.setEstado(rs.getString("estado"));
            return e;
        }
    }
}
