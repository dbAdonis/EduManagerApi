package edumanager.api.model;

public class TipoEvaluacion {
    private int id_tipo;
    private String nombre;

    public TipoEvaluacion() {}

    public TipoEvaluacion(int id_tipo, String nombre) {
        this.id_tipo = id_tipo;
        this.nombre = nombre;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

