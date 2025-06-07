package edumanager.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "matricula",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"id_estudiante", "id_curso"})})
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_matricula;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_matricula;

    private String estado; // "activo", "retirado", "riesgo"

    private Double promedio; // nullable

    public Matricula() {}

    public Matricula(Estudiante estudiante, Curso curso, LocalDate fecha_matricula, String estado, Double promedio) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fecha_matricula = fecha_matricula;
        this.estado = estado;
        this.promedio = promedio;
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

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
}
