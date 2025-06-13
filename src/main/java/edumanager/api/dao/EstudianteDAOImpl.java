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
        jdbcTemplate.update(
            "EXEC InsertarEstudiante @nombre = ?, @correo = ?, @estado = ?",
            estudiante.getNombre(),
            estudiante.getCorreo(),
            estudiante.getEstado()
        );
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
        jdbcTemplate.update(
            "EXEC ActualizarEstudiante @id_estudiante = ?, @nombre = ?, @correo = ?, @estado = ?",
            estudiante.getId_estudiante(),
            estudiante.getNombre(),
            estudiante.getCorreo(),
            estudiante.getEstado());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("EXEC EliminarEstudiante @id_estudiante = ?", id);
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
