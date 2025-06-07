package edumanager.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_profesor;

    private String nombre;

    @Column(unique = true)
    private String correo_institucional;

    public Profesor() {}

    public Profesor(String nombre, String correo_institucional) {
        this.nombre = nombre;
        this.correo_institucional = correo_institucional;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo_institucional() {
        return correo_institucional;
    }

    public void setCorreo_institucional(String correo_institucional) {
        this.correo_institucional = correo_institucional;
    }
}
