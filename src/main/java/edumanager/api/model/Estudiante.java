package edumanager.api.model;

public class Estudiante {

    private int id_estudiante;
    private String nombre;
    private String correo;
    private String estado;

    public Estudiante() {
    }

    public Estudiante(int id_estudiante, String nombre, String correo, String estado) {
        this.id_estudiante = id_estudiante;
        this.nombre = nombre;
        this.correo = correo;
        this.estado = estado;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}