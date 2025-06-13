package edumanager.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        crearUsuarioSiNoExiste("admin", "admin", "ADMIN");
        crearUsuarioSiNoExiste("user", "user", "USER");
    }

    private void crearUsuarioSiNoExiste(String username, String password, String role) {
        String query = "SELECT COUNT(*) FROM usuario WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{username}, Integer.class);

        if (count != null && count == 0) {
            String insert = "INSERT INTO usuario (username, password, role) VALUES (?, ?, ?)";
            jdbcTemplate.update(insert, username, passwordEncoder.encode(password), role);
            System.out.println("✅ Usuario '" + username + "' creado");
        }
    }
}
