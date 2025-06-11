package edumanager.api.model;

import java.util.Date;

public class Calificacion {

    private int id_calificacion;
    private Matricula matricula;
    private double nota;
    private Date fecha_calificacion;

    public Calificacion() {
    }

    public Calificacion(int id_calificacion, Matricula matricula, double nota, Date fecha_calificacion) {
        this.id_calificacion = id_calificacion;
        this.matricula = matricula;
        this.nota = nota;
        this.fecha_calificacion = fecha_calificacion;
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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Date getFecha_calificacion() {
        return fecha_calificacion;
    }

    public void setFecha_calificacion(Date fecha_calificacion) {
        this.fecha_calificacion = fecha_calificacion;
    }

}