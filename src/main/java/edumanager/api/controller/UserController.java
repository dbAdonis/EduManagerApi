package edumanager.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    // Ruta solo para ADMIN
    @GetMapping("/admin/data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Información confidencial para ADMIN");
    }

    // Ruta solo para USER
    @GetMapping("/user/data")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity.ok("Información exclusiva para USER");
    }

    // Ruta para cualquier usuario autenticado
    @GetMapping("/all")
    public ResponseEntity<String> getPublicData() {
        return ResponseEntity.ok("Información accesible para todos los usuarios autenticados");
    }
}
