package edumanager.api.model;

import java.time.LocalDate;

public class Matricula {

    private int id_matricula;
    private Estudiante estudiante;
    private Curso curso;
    private LocalDate fecha_matricula;
    private String estado; // "activo", "retirado", "riesgo"

    public Matricula() {}

    public Matricula(Estudiante estudiante, Curso curso, LocalDate fecha_matricula, String estado) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fecha_matricula = fecha_matricula;
        this.estado = estado;
    }

    public int getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(int id_matricula) {
        this.id_matricula = id_matricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getFecha_matricula() {
        return fecha_matricula;
    }

    public void setFecha_matricula(LocalDate fecha_matricula) {
        this.fecha_matricula = fecha_matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
