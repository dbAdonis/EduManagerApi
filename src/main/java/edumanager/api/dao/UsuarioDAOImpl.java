package edumanager.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import edumanager.api.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT * FROM usuario WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UsuarioRowMapper());
        } catch (Exception e) {
            return null; // o lanza una excepción personalizada
        }
    }

    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario u = new Usuario();
            u.setId_usuario(rs.getInt("id_usuario"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setRole(rs.getString("role"));
            return u;
        }
    }
}
