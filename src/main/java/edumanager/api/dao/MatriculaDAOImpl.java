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
        String sql = "INSERT INTO matricula (id_matricula, id_estudiante, id_curso, fecha, estado) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, matricula.getId_matricula(), matricula.getEstudiante().getId_estudiante(),
                matricula.getCurso().getId_curso(),
                matricula.getFecha(), matricula.getEstado());
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
        String sql = "UPDATE matricula SET id_estudiante = ?, id_curso = ?, fecha = ?, estado = ? WHERE id_matricula = ?";
        jdbcTemplate.update(sql, matricula.getEstudiante().getId_estudiante(), matricula.getCurso().getId_curso(),
                matricula.getFecha(),
                matricula.getEstado(), matricula.getId_matricula());
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
            m.setId_matricula(rs.getInt("id_matricula"));

            Estudiante e = new Estudiante();
            e.setId_estudiante(rs.getInt("id_estudiante"));
            m.setEstudiante(e);

            Curso c = new Curso();
            c.setId_curso(rs.getInt("id_curso"));
            m.setCurso(c);

            m.setFecha(rs.getDate("fecha").toLocalDate());

            String estadoDb = rs.getString("estado");
            m.setEstado(Matricula.Estado.valueOf(estadoDb.toLowerCase()));
            return m;
        }
    }

}