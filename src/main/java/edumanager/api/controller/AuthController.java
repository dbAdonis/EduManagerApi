package edumanager.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edumanager.api.security.JwtUtil;
import edumanager.api.service.UserDetailsServiceImpl;

import java.util.*;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        try {
            // Intenta autenticar al usuario con las credenciales proporcionadas
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Si la autenticación es exitosa, genera el token JWT
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            // Crea la respuesta con el token
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);

            // Devuelve la respuesta con el código 200 (OK)
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            // Si las credenciales son incorrectas, devuelve un mensaje claro y un código 401 (No autorizado)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Credenciales incorrectas"));
        } catch (Exception e) {
            // Captura otros posibles errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Hubo un error en el servidor"));
        }
    }

    public static class AuthRequest {
        private String username;
        private String password;

        // Getters y setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
