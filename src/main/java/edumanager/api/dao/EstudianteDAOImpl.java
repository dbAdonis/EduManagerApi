package edumanager.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = "INSERT INTO estudiante (id_estudiante, nombre, correo, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, estudiante.getId_estudiante(), estudiante.getNombre(), estudiante.getCorreo(), estudiante.getPromedio());
    }

    @Override
    public Estudiante findById(String id) {
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        return jdbcTemplate.queryForObject(sql, new EstudianteRowMapper(), id);
    }

    @Override
    public List<Estudiante> findAll() {
        String sql = "SELECT * FROM estudiante";
        return jdbcTemplate.query(sql, new EstudianteRowMapper());
    }

    @Override
    public void update(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, correo = ?, carnet = ?, proemdio = ? WHERE id_estudiante = ?";
        jdbcTemplate.update(sql, estudiante.getNombre(), estudiante.getCorreo(), estudiante.getCarnet(), estudiante.getPromedio(), estudiante.getId_estudiante());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class EstudianteRowMapper implements RowMapper<Estudiante> {
        @Override
        public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Estudiante e = new Estudiante();
            e.setId_estudiante(rs.getInt("id_estudiante"));
            e.setNombre(rs.getString("nombre"));
            e.setCorreo(rs.getString("correo"));
            e.setCarnet(rs.getString("carnet"));
            e.setPromedio(rs.getInt("promedio"));
            return e;
        }
    }
}