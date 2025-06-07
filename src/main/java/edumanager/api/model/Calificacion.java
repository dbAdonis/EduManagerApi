package edumanager.api.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_calificacion;

    @ManyToOne
    @JoinColumn(name = "id_matricula", nullable = false)
    private Matricula matricula;

    private String tipo_evaluacion;

    private double nota;

    @Temporal(TemporalType.DATE)
    private Date fecha_registro;

    public Calificacion() {}

    public Calificacion(Matricula matricula, String tipo_evaluacion, double nota, Date fecha_registro) {
        this.matricula = matricula;
        this.tipo_evaluacion = tipo_evaluacion;
        this.nota = nota;
        this.fecha_registro = fecha_registro;
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

    public String getTipo_evaluacion() {
        return tipo_evaluacion;
    }

    public void setTipo_evaluacion(String tipo_evaluacion) {
        this.tipo_evaluacion = tipo_evaluacion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
