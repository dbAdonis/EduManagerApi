package edumanager.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_calificacion;

    @ManyToOne
    @JoinColumn(name = "id_matricula")
    private Matricula matricula;

    @Enumerated(EnumType.STRING)
    private TipoEvaluacion tipo_evaluacion;

    public enum TipoEvaluacion {
        Parcial, Final, Proyecto
    }

    private double nota;
    private LocalDate fecha;

    public Calificacion() {
    }

    public Calificacion(Matricula matricula, TipoEvaluacion tipo_evaluacion, double nota, LocalDate fecha) {
        this.matricula = matricula;
        this.tipo_evaluacion = tipo_evaluacion;
        this.nota = nota;
        this.fecha = fecha;
    }

    public int getId_calificacion() {
        return id_calificacion;
    }

    public void setId_calificacion(int id_calificacion) {
        this.id_calificacion = id_calificacion;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public TipoEvaluacion getTipo_evaluacion() {
        return tipo_evaluacion;
    }

    public void setTipo_evaluacion(TipoEvaluacion tipo_evaluacion) {
        this.tipo_evaluacion = tipo_evaluacion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
