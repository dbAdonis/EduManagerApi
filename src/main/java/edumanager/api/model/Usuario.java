package edumanager.api.model;

public class Usuario {
    private int id_usuario;
    private String username;
    private String password;
    private String role;

    public Usuario() {
    }

    public Usuario(int id_usuario, String username, String password, String role) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

}
