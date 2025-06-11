package edumanager.api.model;

public class Curso {

    private int id_curso;
    private String nombre;
    private int cupo;
    private int cupo_disponible;
    private Profesor profesor;

    public Curso() {
    }

    public Curso(int id_curso, String nombre, int cupo, int cupo_disponible, Profesor profesor) {
        this.id_curso = id_curso;
        this.nombre = nombre;
        this.cupo = cupo;
        this.cupo_disponible = cupo_disponible;
        this.profesor = profesor;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getCupo_disponible() {
        return cupo_disponible;
    }

    public void setCupo_disponible(int cupo_disponible) {
        this.cupo_disponible = cupo_disponible;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}
