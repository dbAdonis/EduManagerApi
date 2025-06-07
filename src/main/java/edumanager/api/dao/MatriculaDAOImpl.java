package edumanager.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edumanager.api.model.Curso;
import edumanager.api.model.Estudiante;
import edumanager.api.model.Matricula;

@Repository
public class MatriculaDAOImpl implements MatriculaDAO {
    private final JdbcTemplate jdbcTemplate;

    public MatriculaDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Matricula matricula) {
        String sql = "INSERT INTO matricula (id_estudiante, id_curso, fecha_matricula, estado, promedio) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, matricula.getEstudiante().getId_estudiante(),
                matricula.getCurso().getId_curso(),
                matricula.getFecha_matricula(), matricula.getEstado(), matricula.getPromedio());
    }

    @Override
    public Matricula findById(String id) {
        String sql = "SELECT * FROM matricula WHERE id_matricula = ?";
        return jdbcTemplate.queryForObject(sql, new MatriculaRowMapper(), id);
    }

    @Override
    public List<Matricula> findAll() {
        String sql = "SELECT * FROM matricula";
        return jdbcTemplate.query(sql, new MatriculaRowMapper());
    }

    @Override
    public void update(Matricula matricula) {
        String sql = "UPDATE matricula SET id_estudiante = ?, id_curso = ?, fecha_matricula = ?, estado = ?, promedio = ? WHERE id_matricula = ?";
        jdbcTemplate.update(sql, matricula.getEstudiante().getId_estudiante(), matricula.getCurso().getId_curso(),
                matricula.getFecha_matricula(),
                matricula.getEstado(), matricula.getPromedio());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM matricula WHERE id_matricula = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class MatriculaRowMapper implements RowMapper<Matricula> {
        @Override
        public Matricula mapRow(ResultSet rs, int rowNum) throws SQLException {
            Matricula m = new Matricula();

            Estudiante e = new Estudiante();
            e.setId_estudiante(rs.getInt("id_estudiante"));
            m.setEstudiante(e);

            Curso c = new Curso();
            c.setId_curso(rs.getInt("id_curso"));
            m.setCurso(c);

            m.setFecha_matricula(rs.getDate("fecha").toLocalDate());

            m.setEstado(rs.getString("estado"));

            m.setPromedio(rs.getDouble("promedio"));

            return m;
        }
    }

}