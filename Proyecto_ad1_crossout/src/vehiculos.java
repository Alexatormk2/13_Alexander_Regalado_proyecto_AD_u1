import java.io.Serializable;

public class vehiculos implements Serializable {

    int durabilidad;
    String nombre;
    int danio;
    String descripcion;


    public vehiculos(int durabilidad, String nombre, int danio, String description) {
        this.durabilidad = durabilidad;
        this.nombre = nombre;
        this.danio = danio;

        this.descripcion = description;
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
