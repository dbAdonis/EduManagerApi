package edumanager.api.model;

public class Profesor {

    private int id_profesor;
    private String nombre;
    private String correo;

    public Profesor() {
    }

    public Profesor(int id_profesor, String nombre, String correo) {
        this.id_profesor = id_profesor;
        this.nombre = nombre;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
