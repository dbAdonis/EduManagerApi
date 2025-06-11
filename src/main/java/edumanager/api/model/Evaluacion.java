package edumanager.api.model;

import java.math.BigDecimal;

public class Evaluacion {
    private int id_evaluacion;
    private Matricula matricula;
    private TipoEvaluacion tipoEvaluacion;
    private String descripcion;
    private BigDecimal nota;
    private BigDecimal porcentaje;

    // Constructor vacío
    public Evaluacion() {}

    // Constructor completo
    public Evaluacion(int id_evaluacion, Matricula matricula, TipoEvaluacion tipoEvaluacion, 
                      String descripcion, BigDecimal nota, BigDecimal porcentaje) {
        this.id_evaluacion = id_evaluacion;
        this.matricula = matricula;
        this.tipoEvaluacion = tipoEvaluacion;
        this.descripcion = descripcion;
        this.nota = nota;
        this.porcentaje = porcentaje;
    }

    // Getters y setters
    public int getId_evaluacion() {
        return id_evaluacion;
    }

    public void setId_evaluacion(int id_evaluacion) {
        this.id_evaluacion = id_evaluacion;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public TipoEvaluacion getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(TipoEvaluacion tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }
}
