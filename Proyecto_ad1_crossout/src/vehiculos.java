import java.io.Serializable;

public class vehiculos implements Serializable {

    int durabilidad;
    String nombre;
    int danio;
    String descripcion;
    String tipo;


    public vehiculos(int durabilidad, String nombre, int danio, String description, String tipo) {
        this.durabilidad = durabilidad;
        this.nombre = nombre;
        this.danio = danio;
        this.tipo = tipo;
        this.descripcion = description;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public vehiculos() {

    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }
}
